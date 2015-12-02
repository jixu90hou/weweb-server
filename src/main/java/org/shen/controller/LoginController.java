package org.shen.controller;

import javax.servlet.http.HttpServletRequest;

import org.shen.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	 @RequestMapping("/login")  
	public @ResponseBody String login(HttpServletRequest request){
		String name=request.getParameter("name");//添加注释
		String password=request.getParameter("password");
		return JSON.toJSONString(loginService.login(name, password));
	 }
}
