package org.weweb.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by shen on 2016/5/29.
 */
@Aspect
@Component
public class LogAspect {
    public LogAspect(){

    }
    @Around("execution(* org.weweb.controller.UserController.*(..))")
    public Object around(ProceedingJoinPoint pjp){
        before();
        Object object= null;
        try {
            object = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        after();
        return object;
    }
    private void before() {
        System.out.println("******LogAspect********Before*************");
    }

    private void after() {
        System.out.println("*******LogAspect*******After**************");
    }
}
