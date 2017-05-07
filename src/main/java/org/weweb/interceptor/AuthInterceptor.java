package org.weweb.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.weweb.entity.model.Log;
import org.weweb.entity.model.UserToken;
import org.weweb.entity.result.ResultFactory;
import org.weweb.service.LogService;
import org.weweb.service.UserTokenService;
import org.weweb.util.ConstantUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by shen on 2016/5/28.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = Logger.getLogger(AuthInterceptor.class);
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private LogService logService;
    private static final Executor executor= Executors.newSingleThreadExecutor();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.error("======error=====preHandle=============");
        logger.info("======info=====preHandle=============");
        logger.debug("======debug=====preHandle=============");

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
           /* AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            //没有声明需要权限，或者声明不验证权限
            //  if (authPassport == null || !authPassport.validate())
            //    return false;
            String appToken = request.getHeader("app_token");
            if (appToken != null) {
                System.err.println("app_token:" + appToken);
                request.setAttribute("userId", 21);
            }

            WeUser weUser = WeUtils.getWeUser(request);
            WeUtils.checkUserLogin(weUser);//核对用户登陆情况
            if (weUser == null) {
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().println("====请先登录=====");
                return false;
            }*/
            executor.execute(()->{
                try {
                    addLog(request, response, handler);
                } catch (IOException e) {
                    logger.error("addLog---->",e);
                }
            });
            return validateUserToken(request, response, handler);
        }
        return true;
    }

    private boolean validateUserToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        TokenValidate tokenValidate = ((HandlerMethod) handler).getMethodAnnotation(TokenValidate.class);
        //not add annotation to method,or add annotation is false
        if (tokenValidate == null)
            return true;
        if (tokenValidate.guestToken()){
            //handle guest token content
        }
        if (tokenValidate.userToken()){
            //handle user token content
        }
        //or tokenValue is true
        String wewebToken = request.getHeader(ConstantUtil.WEWEB_TOKEN);
        if (StringUtils.isNotEmpty(wewebToken)) {
            UserToken userToken = userTokenService.getUserTokenByToken(wewebToken);
            request.setAttribute("userId",userToken.getUserId());
            if (userToken == null) {
                String result = JSON.toJSONString(ResultFactory.generateResult(ConstantUtil.AUTH_FAIL_CODE, ConstantUtil.AUTH_FAIL_MSG));
                response.getWriter().println(result);
                return false;
            }

        } else {//token is null
            String result = JSON.toJSONString(ResultFactory.generateResult(ConstantUtil.AUTH_FAIL_CODE, ConstantUtil.AUTH_FAIL_MSG));
            response.getWriter().println(result);
            return false;
        }
        return true;

    }

    private void addLog(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        LogDescription logDescription = ((HandlerMethod) handler).getMethodAnnotation(LogDescription.class);
        //not add annotation to method
        logger.error("request.getLocalAddr()---->"+request.getLocalAddr());
        logger.error("addLog--------->"+logDescription);
        if (logDescription == null)
            return;
        int type = logDescription.type().getType();
        String wewebToken=request.getHeader(ConstantUtil.WEWEB_TOKEN);
        long userId=0L;
        if(!StringUtils.isEmpty(wewebToken)){
             UserToken userToken = userTokenService.getUserTokenByToken(wewebToken);
            userId=userToken.getUserId();
        }else {
            userId=0;
        }
        Log log = new Log();
        log.setUserId(userId);
        String ip = request.getLocalAddr();
        log.setIp(ip);
        log.setCreateTime(new Date());
        String device = request.getParameter("device");
        log.setDevice(device);
        log.setType(type);
        logService.add(log, true);

       /* //or tokenValue is true
        String wewebToken = request.getHeader(ConstantUtil.WEWEB_TOKEN);
        if (StringUtils.isNotEmpty(wewebToken)) {
            UserToken userToken = userTokenService.getUserTokenByToken(wewebToken);
            if (userToken == null) {
                String result = JSON.toJSONString(ResultFactory.generateResult(ConstantUtil.AUTH_FAIL_CODE, ConstantUtil.AUTH_FAIL_MSG));
                response.getWriter().println(result);
                return false;
            }

        } else {//token is null
            String result = JSON.toJSONString(ResultFactory.generateResult(ConstantUtil.AUTH_FAIL_CODE, ConstantUtil.AUTH_FAIL_MSG));
            response.getWriter().println(result);
            return false;
        }*/

    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
       // addLog(request, response, handler);


        logger.error("====error========postHandle============");
        logger.info("====info========postHandle============");
        logger.debug("====debug========postHandle============");

    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        logger.debug("debugger============afterCompletion========");
    }
}
