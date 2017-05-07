package org.weweb.service;

import org.springframework.stereotype.Service;
import org.weweb.mapper.UserTokenMapper;
import org.weweb.entity.model.UserToken;

@Service
public class UserTokenService extends BaseService<UserToken, UserTokenMapper> {
    public UserToken getUserTokenByUserId(Long userId) {
        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        return dao.selectOne(userToken);
    }
    public UserToken getUserTokenByToken(String token){
        UserToken userToken=new UserToken();
        userToken.setToken(token);
        return dao.selectOne(userToken);
    }
}
