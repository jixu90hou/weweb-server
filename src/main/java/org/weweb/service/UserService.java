package org.weweb.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.weweb.mapper.TUserMapper;
import org.weweb.model.TUser;
import org.weweb.model.User;

@Service
public class UserService extends BaseService<TUser,TUserMapper> {

	public TUser login(String account, String password) {
		TUser user=new TUser();
		user.setAccount(account);
		user.setPassword(password);
		return dao.selectOne(user);
	}
	@Cacheable(value = "getUser",key ="#account")
	public TUser getUser(String account){
		TUser user=new TUser();
		user.setAccount(account);
		return dao.selectOne(user);
	}
	@CacheEvict(value = "getUser" , key = "#account")
	public Integer updateUser(TUser user){
		return super.update(user,true);
	}
	@CachePut(value = {"getUser" }, key = "#id")
	public Integer deleteUser(long id){
		return dao.deleteByPrimaryKey(id);
	}

}
