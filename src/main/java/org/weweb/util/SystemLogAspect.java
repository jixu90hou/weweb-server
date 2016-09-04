/*
package org.weweb.util;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.weweb.annotation.SystemControllerLog;
import org.weweb.annotation.SystemServiceLog;
import org.weweb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

*/
/**
 * http://blog.csdn.net/czmchen/article/details/42392985
 * Created by shen on 2016/5/3.
 *//*

@Aspect
@Component
public class SystemLogAspect {
    @Autowired
    private LogService logService;
    //Service层切入点
    @Pointcut("@annotation(org.weweb.annotation.SystemServiceLog)")
    public void serviceAspect(){}
    //Controller层切入点
    @Pointcut("@annotation(org.weweb.annotation.SystemControllerLog)")
    public void controllerAspect(){}

    */
/**
     * 前置通知 用于拦截Controller层记录用户的记录
     * @param joinPoint
     *//*

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request= (HttpServletRequest) RequestContextHolder.getRequestAttributes();
        HttpSession session=request.getSession();
        //读取session的用户
        User user= (User) session.getAttribute("user");
        String ip=request.getRemoteAddr();
        String params="";
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JSON.toJSON(joinPoint.getArgs()[i]) + ";";
            }
        }
        System.out.println("=======前置通知开始======");
        System.out.println("请求方法："+(joinPoint.getTarget().getClass().getName()+"."
        +joinPoint.getSignature().getName()+"()"));
        System.out.println("方法描述："+getServiceMthodDescription(joinPoint));
        System.out.println("请求人:" + user.getUsername());
        System.out.println("请求IP:" + ip);
        System.out.println("请求参数:" + params);
    }
    */
/**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     *//*

    public  static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemServiceLog. class).description();
                    break;
                }
            }
        }
        return description;
    }
    */
/**
     * 获取注解中方法的描述信息用于Controller层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws ClassNotFoundException
     *//*

    public static String getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName=joinPoint.getTarget().getClass().getName();
        String methodName=joinPoint.getSignature().getName();
        Object[] arguments=joinPoint.getArgs();
        Class targetClass=Class.forName(targetName);
        Method[] methods=targetClass.getMethods();
        String description="";
        for (Method method:methods){
            if(method.getName().equals(methodName)){
                Class[] clazzs=method.getParameterTypes();
                if(clazzs.length==arguments.length){
                    description=method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
*/
