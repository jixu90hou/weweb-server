/*
package org.weweb.interceptor;

import org.weweb.common.WeUser;
import org.weweb.util.WeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

*/
/**
 * Created by shen on 2016/5/28.
 *//*

public class LogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
   private LogService logService;

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            LogDescription logDescription = ((HandlerMethod) handler).getMethodAnnotation(LogDescription.class);
            WeUser weUser = WeUtils.getWeUser(request);//取得用户对象
            WeUtils.checkUserLogin(weUser);//核对用户登陆情况
            Logger logger = new Logger();
            logger.setCreatedate(new Date());
            logger.setUserid(weUser.getUserid());//添加用户ID
            logger.setOperation(logDescription.description());
            logService.add(logger, true);
        }
    }
}
*/
