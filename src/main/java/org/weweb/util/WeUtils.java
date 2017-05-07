package org.weweb.util;

import org.apache.commons.lang3.StringUtils;
import org.weweb.common.UserNotLoginException;
import org.weweb.common.WeUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by shen on 2016/5/29.
 * 工具类
 */
public class WeUtils {
    /**
     * 核对用户登录情况用户为空直接抛出异常
     *
     * @param weUser
     */
    private WeUser weUser;

    public static void checkUserLogin(WeUser weUser) {
        if (weUser == null)
            throw new UserNotLoginException();
    }


    //取得用户信息
    public static WeUser getWeUser(HttpServletRequest request) {
        Objects.requireNonNull(request);
        WeUser weUser = (WeUser) request.getSession().getAttribute("weUser");
        return weUser;
    }

    //添加用户信息
    public static void addWeUser(HttpServletRequest request, WeUser weUser) {
        Objects.requireNonNull(request);
        request.getSession().setAttribute("weUser", weUser);
    }

    //移除用户信息
    public static void removeWeUser(HttpServletRequest request) {
        Objects.requireNonNull(request);
        request.getSession().setAttribute("weUser", null);
    }

    public static boolean isNotNull(Object obj) {
        if (obj == null) return false;
        String str = String.valueOf(obj);
        if (StringUtils.isNotBlank(str) && !StringUtils.equalsIgnoreCase("null", str) && !StringUtils.equalsIgnoreCase("undefined", str))
            return true;
        return false;
    }
    public static String generateToken(String username,String password){
        return UUID.randomUUID().toString();
    }
}
