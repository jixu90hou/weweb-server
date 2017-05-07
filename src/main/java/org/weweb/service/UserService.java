package org.weweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weweb.entity.po.UserPo;
import org.weweb.mapper.UserMapper;
import org.weweb.entity.model.User;
import org.weweb.entity.model.UserToken;
import org.weweb.util.WeUtils;

import java.util.Date;

@Service
public class UserService extends BaseService<User, UserMapper> {
    @Autowired
    private UserTokenService userTokenService;

    @Transactional(readOnly = false)
    public UserPo login(String account, String password) {
        User user = getUserByAccountAndPassword(account, password);
        UserPo userPo = new UserPo();
        if (user != null) {
            Long userId = user.getId();
            UserToken userToken = userTokenService.getUserTokenByUserId(userId);
            Date date = new Date();
            String token = WeUtils.generateToken(account, password);
            userPo.setUser(user);
            userPo.setUserToken(token);
            //userToken is not exist will add new user token
            if (userToken == null) {
                userToken = new UserToken();
                userToken.setCreateTime(date);
                userToken.setUserId(user.getId());
                userToken.setUpdateTime(date);
                userToken.setToken(token);
                userTokenService.add(userToken, true);
            } else {
                //update user token
                userToken.setUpdateTime(date);
                userToken.setToken(token);
                userTokenService.update(userToken, true);
            }

        }
        return userPo;
    }

    private User getUserByAccountAndPassword(String account, String password) {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        return dao.selectOne(user);
    }

    @Cacheable(value = "geUser", key = "#account")
    public User geUser(String account) {
        User user = new User();
        user.setAccount(account);
        return dao.selectOne(user);
    }

    @CacheEvict(value = "geUser", key = "#account")
    public Integer updateUser(User user) {
        return super.update(user, true);
    }

    @CachePut(value = {"geUser"}, key = "#id")
    public Integer deleteUser(long id) {
        return dao.deleteByPrimaryKey(id);
    }

}
