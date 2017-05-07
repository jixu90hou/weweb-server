package org.weweb.entity.po;

import org.weweb.entity.model.User;

/**
 * Created by jackshen on 2016/12/31.
 */
public class UserPo{
    private User user;
    private String userToken;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

