package cn.sdcit.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sdcit.entity.DreamUser;
import cn.sdcit.entity.DreamWallet;
import cn.sdcit.entity.DreamWalletRecord;
import cn.sdcit.service.TokenService;
import cn.sdcit.service.WalletService;
import cn.sdcit.utils.DreamResult;
import cn.sdcit.utils.EasyUIResult;
import cn.sdcit.utils.JsonUtils;

@Controller
public class WalletController {
	@Autowired
	private TokenService tokenService;
	@Autowired
	private WalletService walletService;
	
	
	/**
	 * 开通钱包
	 * @param dreamWallet
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/openWallet/{token}")
	@ResponseBody
	public DreamResult openWallet(DreamWallet dreamWallet,@PathVariable String token){
		
		DreamResult result = tokenService.getuserByToken(token);
		//如果用户未登录，返回
		if (result.getStatus() == 400) {
			return result;
		}
		DreamUser dreamUser = (DreamUser)result.getData();
		dreamWallet.setUid(dreamUser.getId());
		//
		result = walletService.saveWallet(dreamWallet);
		return result;
		
	}
	/**
	 * 
	 * @param money 扣款金额
	 * @param token 用户token
	 * @param fromUid 扣款方id
	 * @param toUid 收款方id
	 * @return
	 */
	@RequestMapping(value = "/payment")
	@ResponseBody
	public DreamResult payment(Double money ,Long fromUid,Long toUid,Long oId) {
		
		//验证 扣款方 钱包是否开通
		DreamResult findWallet = walletService.findWallet(fromUid);
		DreamWallet dreamWallet =  (DreamWallet) findWallet.getData();
		if(dreamWallet==null){
			return DreamResult.build(400, "扣款方未开通钱包");
		}
		//验证 收款方 钱包是否开通
		findWallet = walletService.findWallet(toUid);
		dreamWallet = (DreamWallet) findWallet.getData();
		if(dreamWallet==null){
			return DreamResult.build(400, "收款方未开通钱包");
		}
		
		//设置付款用户id
		DreamWalletRecord dreamWalletRecord = new DreamWalletRecord();
		dreamWalletRecord.setRemark("订单id："+oId);
		dreamWalletRecord.setMoney(money);
		dreamWalletRecord.setFromUid(fromUid);
		dreamWalletRecord.setToUid(toUid);
		dreamWalletRecord.setType((byte) 3);//设置交易方式为 交易   '交易类型 1充值 2提现 3交易',
		DreamResult payment = walletService.payment(dreamWalletRecord);
		return payment;
	}
	
	/**
	 * 查看余额
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/queryMoney/{token}")
	@ResponseBody
	public DreamResult queryMoney(@PathVariable String token) {
		//根据token取出用户信息
		DreamResult result = tokenService.getuserByToken(token);
		if (result.getStatus() == 400) {
			return result;
		}
		DreamUser dreamUser = (DreamUser)result.getData();
		
		//验证用户钱包是否开通
		DreamResult findWallet = walletService.findWallet(dreamUser.getId());
		DreamWallet dreamWallet =  (DreamWallet) findWallet.getData();
		if(dreamWallet==null){
			return DreamResult.build(400, "用户未开通钱包");
		}
		return findWallet;
	}
	/**
	 * 充值金额
	 * @param uid 充值用户id
	 * @param money 充值金额
	 * @return
	 */
	@RequestMapping(value = "/recharge")
	@ResponseBody
	public DreamResult recharge(Long uid,Double money) {
		DreamResult findWallet = walletService.findWallet(uid);
		DreamWallet dreamWallet =  (DreamWallet) findWallet.getData();
		if(dreamWallet==null){
			return DreamResult.build(400, "用户未开通钱包");
		}
		//设置付款用户id
		DreamWalletRecord dreamWalletRecord = new DreamWalletRecord();
		dreamWalletRecord.setRemark("充值用户id："+uid);
		dreamWalletRecord.setMoney(money);
		dreamWalletRecord.setFromUid((long) 0); //支付方UID，0系统
		dreamWalletRecord.setToUid(uid);
		dreamWalletRecord.setType((byte) 1);//设置交易方式为 交易   '交易类型 1充值 2提现 3交易',
		DreamResult payment = walletService.payment(dreamWalletRecord);
		return DreamResult.ok();
		
	}
	/**
	 * 提现
	 * @param token 用户token
	 * @param money 金额
	 * @return
	 */
	@RequestMapping(value = "/withdraw/{token}")
	@ResponseBody
	public DreamResult withdraw(@PathVariable String token,Double money) {
		//根据token取出用户信息
		DreamResult result = tokenService.getuserByToken(token);
		if (result.getStatus() == 400) {
			return result;
		}
		DreamUser dreamUser = (DreamUser)result.getData();
		
		//验证用户钱包是否开通
		DreamResult findWallet = walletService.findWallet(dreamUser.getId());
		DreamWallet dreamWallet =  (DreamWallet) findWallet.getData();
		if(dreamWallet==null){
			return DreamResult.build(400, "用户未开通钱包");
		}
		
		//设置付款用户id
		DreamWalletRecord dreamWalletRecord = new DreamWalletRecord();
		dreamWalletRecord.setRemark("提现用户id："+dreamUser.getId());
		dreamWalletRecord.setMoney(money);
		dreamWalletRecord.setFromUid(dreamUser.getId()); //支付方UID，0系统
		dreamWalletRecord.setToUid((long) 0);
		dreamWalletRecord.setType((byte) 2);//设置交易方式为 交易   '交易类型 1充值 2提现 3交易',
		DreamResult payment = walletService.payment(dreamWalletRecord);
		
		return payment;
	}

	/**
	 * 分页查询未通过审核提现请求
	 * @param page 页数
	 * @param rows 行数
	 * @return
	 */
	@RequestMapping(value = "/checkWithdraw")
	@ResponseBody
	public EasyUIResult checkWithdraw(@RequestParam(defaultValue= "1") Integer page , @RequestParam(defaultValue= "10")Integer rows) {
		EasyUIResult checkWithdraw = walletService.checkWithdraw(page, rows);
		return checkWithdraw;
	}
	
	/**
	 * 通过审核
	 * @param recordId
	 * @return
	 */
	@RequestMapping(value = "/checkWithdraw/confirm")
	@ResponseBody
	public DreamResult checkWithdrawConfirm(Long recordId) {
		DreamResult checkWithdrawConfirm = walletService.checkWithdrawConfirm(recordId);
		return checkWithdrawConfirm;
	}

	/**
	 * 驳回提现审核请求
	 * @param recordId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/checkWithdraw/cancel")
	@ResponseBody
	public DreamResult checkWithdrawCancel(Long recordId,String remark) throws UnsupportedEncodingException {
		//解决乱码问题
		remark = new String(remark.getBytes("iso8859-1"),"utf-8");
		
		DreamResult checkWithdrawCancel = walletService.checkWithdrawCancel(recordId,remark);
		return checkWithdrawCancel;
	}
	
	@RequestMapping(value = "/queryLog/{token}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryLog(@PathVariable String token,@RequestParam(defaultValue= "1")Integer page,@RequestParam(defaultValue="10") Integer rows) {
		String json = null;
		
		//根据token取出用户信息
		DreamResult result = tokenService.getuserByToken(token);
		if (result.getStatus() == 400) {
			return JsonUtils.objectToJson(result);
		}
		DreamUser dreamUser = (DreamUser)result.getData();
		
		//验证用户钱包是否开通
		DreamResult findWallet = walletService.findWallet(dreamUser.getId());
		DreamWallet dreamWallet =  (DreamWallet) findWallet.getData();
		if(dreamWallet==null){
			
			return JsonUtils.objectToJson(DreamResult.build(400, "用户未开通钱包"));
		}
		
		EasyUIResult queryLog = walletService.queryLog(dreamUser.getId(),page,rows);
		
		return JsonUtils.objectToJson(queryLog);
	}
		
	
	
	/**
	 * 扣除借卖方金额到官方账号。
	 * @param remark 备注信息
	 * @param money 交易金额
	 * @param token 用户token
	 * @param payPassword 支付密码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	/*
	@RequestMapping(value = "/payment/{token}")
	@ResponseBody
	public DreamResult payment(String remark,Double money ,@PathVariable String token,String payPassword) throws UnsupportedEncodingException{
		//解决备注乱码
		remark = new String(remark.getBytes("iso8859-1"),"utf-8");
		//根据token取出用户信息
		DreamResult result = tokenService.getuserByToken(token);
		if (result.getStatus() == 400) {
			return result;
		}
		DreamUser dreamUser = (DreamUser)result.getData();
		
		//验证用户钱包是否开通
		DreamResult findWallet = walletService.findWallet(dreamUser.getId());
		DreamWallet dreamWallet =  (DreamWallet) findWallet.getData();
		if(dreamWallet==null){
			return DreamResult.build(400, "用户未开通钱包");
		}
		
		//设置付款用户id
		DreamWalletRecord dreamWalletRecord = new DreamWalletRecord();
		dreamWalletRecord.setRemark(remark);
		dreamWalletRecord.setMoney(money);
		dreamWalletRecord.setFromUid(dreamUser.getId());
		result = walletService.payment(dreamWalletRecord);
		
		return result;
	}*/


}
