package cn.sdcit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sdcit.entity.DreamBrand;
import cn.sdcit.entity.DreamUser;
import cn.sdcit.service.RegisterService;
import cn.sdcit.utils.DreamResult;


@Controller
public class RegisterController {
	@Autowired
	private RegisterService registerService;
	
	
	@RequestMapping("/save")
	@ResponseBody
	public DreamResult save(DreamUser dreamUser,DreamBrand dreamBrand){
		DreamResult dreamResult = registerService.save(dreamUser,dreamBrand);
		return dreamResult;
	}
	
	
	


}
