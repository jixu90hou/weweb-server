package org.shen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.shen.model.User;
import org.shen.service.UserService;
import org.shen.util.DozerBeanUtil;
import org.shen.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	public @ResponseBody String login(HttpServletRequest request) {
		String username = request.getParameter("username");// 添加注释
		String password = request.getParameter("password");
		return JSON.toJSONString(userService.login(username, password));
	}
	@RequestMapping("/update")
	public @ResponseBody String add(HttpServletRequest request) {
		User user=new User();
		user.setId(2L);
		user.setAge(100);
		return JSON.toJSONString(userService.update(user, true));
	}
	@RequestMapping("/query")
	public @ResponseBody String query(HttpServletRequest request) {
		//PageHelper.startPage(0, 10);
		List<User> users=userService.selectAll();
		List<UserVo> userVos=DozerBeanUtil.mapList(users, UserVo.class);
		return JSON.toJSONString(userVos);
	}
	@RequestMapping("/queryById")
	public @ResponseBody String queryById(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", 2);
		User user=userService.queryById(map);
		UserVo userVo=DozerBeanUtil.map(user, UserVo.class);
		return JSON.toJSONString(userVo);
	}
}
