package cn.sdcit.service.impl;

import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.sdcit.entity.DreamUser;
import cn.sdcit.entity.DreamUserExample;
import cn.sdcit.mapper.DreamUserMapper;
import cn.sdcit.reids.JedisClientPool;
import cn.sdcit.service.LoginService;
import cn.sdcit.utils.DreamResult;
import cn.sdcit.utils.JsonUtils;
@Service				
public class LoginServiceImpl implements  LoginService{
	@Autowired
	private DreamUserMapper dreamUserMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	@Value("${SESSION_TOKEN}")
	private String SESSION_TOKEN;
	@Value("${SESSION_SECONDS}")
	private int SESSION_SECONDS;
	@Override
	public DreamResult login(DreamUser dreamUser) {
		DreamUserExample example =new DreamUserExample();
		DreamUserExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(dreamUser.getUsername()) || StringUtils.isNotBlank(dreamUser.getPassword())){
			criteria.andUsernameEqualTo(dreamUser.getUsername());
		}
		else{
			return DreamResult.build(400, "请输入用户名和密码");
		} 
		List<DreamUser> list =dreamUserMapper.selectByExample(example);
	if(0==list.size() || null==list){
		return DreamResult.build(400, "用户名或密码错误");
	}
	DreamUser user=list.get(0);
	String passowrd = DigestUtils.md5DigestAsHex(dreamUser.getPassword().getBytes());
	if(!passowrd.equals(user.getPassword())){
		return DreamResult.build(400, "用户名或密码错误!");
	}
	String token=UUID.randomUUID().toString();
	jedisClientPool.set(SESSION_TOKEN+":"+token,JsonUtils.objectToJson(user));
	jedisClientPool.expire(SESSION_TOKEN+":"+token, SESSION_SECONDS);
		return DreamResult.ok(token);
		
		
		
	}
	
	

}
