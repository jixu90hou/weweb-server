package org.weweb.common;

/**
 * Created by shen on 2016/5/28.
 * 用户未登录异常
 */
public class UserNotLoginException extends WeException {
    public UserNotLoginException() {
        super("用户未登录异常");
    }
}
