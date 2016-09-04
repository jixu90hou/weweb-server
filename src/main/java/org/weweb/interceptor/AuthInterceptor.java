package org.weweb.interceptor;

import org.apache.log4j.Logger;
import org.weweb.common.WeUser;
import org.weweb.controller.UserController;
import org.weweb.util.WeUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shen on 2016/5/28.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.error("===========preHandle=============");
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            //没有声明需要权限，或者声明不验证权限
            if (authPassport == null || !authPassport.validate())
                return true;
            WeUser weUser = WeUtils.getWeUser(request);
            WeUtils.checkUserLogin(weUser);//核对用户登陆情况
            if (weUser == null) {
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().println("====请先登录=====");
                return false;
            }

        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        logger.error("============postHandle============");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.debug("debugger============afterCompletion========");
    }
}
