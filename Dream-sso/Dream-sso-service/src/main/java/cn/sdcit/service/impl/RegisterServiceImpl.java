package cn.sdcit.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.sdcit.entity.DreamBrand;
import cn.sdcit.entity.DreamUser;
import cn.sdcit.entity.DreamUserExample;
import cn.sdcit.entity.DreamUserExample.Criteria;
import cn.sdcit.mapper.DreamBrandMapper;
import cn.sdcit.mapper.DreamUserMapper;
import cn.sdcit.reids.JedisClientPool;
import cn.sdcit.service.RegisterService;
import cn.sdcit.utils.DreamResult;
import cn.sdcit.utils.IDUtils;
@Service
public class RegisterServiceImpl implements RegisterService{
	@Autowired
	private DreamUserMapper dreamUserMapper;
	@Autowired
	private DreamBrandMapper dreamBrandMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	
	public DreamResult check(String username,String type){
		DreamUserExample example = new DreamUserExample();
		Criteria criteria = example.createCriteria();
		if ("1".equals(type)) {
			criteria.andUsernameEqualTo(username);
		}
		else if ("2".equals(type)) {
				criteria.andPhoneEqualTo(username);	
		}
		else if ("3".equals(type)) {
			criteria.andEmailEqualTo(username);
		}else {
			return DreamResult.build(400, "请将信息填写完整!");
		}
		List<DreamUser> list = dreamUserMapper.selectByExample(example);
		if(0==list.size()||null==list){
			//true 为信息为空，同意该用户注册
			return DreamResult.ok(true);
		}	
		return DreamResult.ok(false);
	}

	
	public DreamResult save(DreamUser dreamUser,DreamBrand dreamBrand) {
	
		if(!StringUtils.isNotBlank(dreamUser.getPassword()) || 
				dreamUser.getRole()==null||
				!StringUtils.isNotBlank(dreamUser.getUsername())){
			return DreamResult.build(400, "信息填写不完整，请重新填写完整信息");
		}
	
		DreamResult check = this.check(dreamUser.getUsername(),"1");
		if(!(boolean) check.getData()){
			return DreamResult.build(400, "用户名已被占用！");
		}
		check = this.check(dreamUser.getUsername(),"2");
		if(!(boolean) check.getData()){
			return DreamResult.build(400, "手机号已被占用！");
		}
		IDUtils idutils=new IDUtils();
		long id=idutils.genItemId();
		dreamUser.setId(id);
		dreamUser.setPassword(DigestUtils.md5DigestAsHex(dreamUser.getPassword().getBytes()));
		dreamUser.setCreated(new Date());
		dreamUser.setUpdated(new Date());
		if(dreamUser.getRole()==0){ //判定是否为品牌商 是则审核状态为未审核并存入品牌商数据
			dreamUser.setState(0);//   0 品牌商未审核
			dreamBrand.setUserId(id);
			dreamBrandMapper.insert(dreamBrand);			
			
		}
		else{
			dreamUser.setState(1);
		}
		
		dreamUserMapper.insert(dreamUser);
		return DreamResult.ok();
	}


	}

