package org.shen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.shen.model.User;
import org.shen.result.Result;
import org.shen.result.ResultFactory;
import org.shen.service.UserService;
import org.shen.util.ConstantUtil;
import org.shen.util.DozerBeanUtil;
import org.shen.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger=Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public @ResponseBody String login(HttpServletRequest request) {
		String username = request.getParameter("username");// 添加注释
		String password = request.getParameter("password");
		return JSON.toJSONString(userService.login(username, password));
	}

	@RequestMapping("/update")
	public @ResponseBody String update(HttpServletRequest request) {
		User user = new User();
		user.setId(2L);
		user.setAge(100);
		return JSON.toJSONString(userService.update(user, true));
	}

	/**
	 * 批量更新操作
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/batchInsert")
	public @ResponseBody String batchInsert(HttpServletRequest request) {
		try {
			for (int i = 0; i < 10; i++) {
				User user = new User();
				user.setAge(100);
				user.setAccount("feng" + i);
				user.setPassword("feng" + i);
				user.setAge(10 + i);
				user.setMobile("1804680111" + i);
				user.setUsername("feng" + i);
				userService.add(user, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultFactory.generateResult(
					ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG));
		}
		return JSON.toJSONString(ResultFactory.generateResult(
				ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG));

	}

	@RequestMapping("/query")
	public @ResponseBody String query(HttpServletRequest request) {
		String start=request.getParameter("start");
		String fetch=request.getParameter("fetch");
		PageHelper.startPage(Integer.valueOf(start), Integer.valueOf(fetch));
		Page<User> usersPage = userService.selectPage();
		List<User> users=usersPage.getResult();
		Long count=usersPage.getTotal();
		List<UserVo> userVos = DozerBeanUtil.mapList(users, UserVo.class);
		Result result = ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE,
				String.valueOf(count), userVos);
		logger.debug(JSON.toJSONString(result));
		return JSON.toJSONString(result);
	}

	@RequestMapping("/queryById")
	public @ResponseBody String queryById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 2);
		User user = userService.queryById(map);
		UserVo userVo = DozerBeanUtil.map(user, UserVo.class);
		Result result = ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE,
				ConstantUtil.SUCCESS_MSG, userVo);
		return JSON.toJSONString(result);
	}
}
