package org.shen.service;

import java.util.List;
import java.util.Map;

import org.shen.mapper.UserMapper;
import org.shen.model.User;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserService extends BaseService<User,UserMapper> {
	public Boolean login(String username, String password) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("username", username)
				.andEqualTo("password", password);
		List<User> users =dao.selectByExample(example);
		if (users.size()> 0)//登录成功
			return true;
		return false;//登录失败
	}
	public User queryById(Map<String,Object> map){
		return dao.queryById(map);
	}
}
