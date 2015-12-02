package org.shen.service;

import org.shen.mapper.UserMapper;
import org.shen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
@Service
public class LoginService{
	@Autowired
	private UserMapper userMapper;
	public Boolean login(String name, String password) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("name", name)
				.andEqualTo("password", password);
		if (userMapper.selectByExample(example).size() > 0)//登录成功
			return true;
		return false;//登录失败
	}
}
