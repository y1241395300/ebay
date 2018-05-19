package cn.sdcit.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sdcit.service.TokenService;
import cn.sdcit.utils.DreamResult;
import cn.sdcit.utils.JsonUtils;


@Controller
public class TokenController {
	@Autowired
	private TokenService tokenService;
	@RequestMapping(value = "/user/token/{token}",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String token(@PathVariable String token,String callback){
		DreamResult result = tokenService.getuserByToken(token);
		if(StringUtils.isNotBlank(callback)){
			String jsonp = callback+"("+JsonUtils.objectToJson(result)+");";
			return jsonp;
		}
		return JsonUtils.objectToJson(result);
	}
}
