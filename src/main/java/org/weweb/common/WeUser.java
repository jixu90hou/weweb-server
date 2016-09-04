package org.weweb.common;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shen on 2016/5/28.
 */
public class WeUser {
    private Long userid;
    private String username;
    private String account;
    private String mobile;
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
