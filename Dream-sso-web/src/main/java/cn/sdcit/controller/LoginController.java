package cn.sdcit.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.sdcit.entity.DreamUser;
import cn.sdcit.service.LoginService;
import cn.sdcit.service.RegisterService;
import cn.sdcit.utils.CookieUtils;
import cn.sdcit.utils.DreamResult;
@Controller
public class LoginController {
@Autowired
private LoginService loginService;


@Value("${COOKIE_TOKEN_KEY}")
private String COOKIE_TOKEN_KEY;
@RequestMapping("/page/login")
public String login(String redirect,Model model){
	model.addAttribute("redirect", redirect);
	return "login";
}
@RequestMapping("/user/login")
@ResponseBody()
public DreamResult userLogin(DreamUser dreamUser,HttpServletRequest request,HttpServletResponse response
		){
	DreamResult dreamResult = loginService.login(dreamUser);
	String token = (String) dreamResult.getData();
	CookieUtils.setCookie(request, response, COOKIE_TOKEN_KEY, token);
	return dreamResult;
	
}

}


