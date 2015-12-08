package org.shen.mapper;

import java.util.Map;

import org.shen.model.User;
import org.shen.util.MyMapper;
public interface UserMapper extends MyMapper<User> {
	public User queryById(Map<String,Object> map);
}