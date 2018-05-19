package cn.sdcit.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.sdcit.entity.DreamUser;
import cn.sdcit.reids.JedisClient;
import cn.sdcit.service.TokenService;
import cn.sdcit.utils.DreamResult;
import cn.sdcit.utils.JsonUtils;
@Service
public class TokenServiceImpl implements TokenService{
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_TOKEN}")
	private String SESSION_TOKEN;
	@Value("${SESSION_SECONDS}")
	private int SESSION_SECONDS;
	@Override
	public DreamResult getuserByToken(String token) {
		String json = jedisClient.get(SESSION_TOKEN+":"+token);
		if(StringUtils.isNotBlank(json)){
			DreamUser dreamUser = JsonUtils.jsonToPojo(json, DreamUser.class);
			jedisClient.expire(SESSION_TOKEN+":"+token, SESSION_SECONDS);
			return DreamResult.ok(dreamUser);
		}
		return DreamResult.build(400, "用户已过期！");
	}


}
