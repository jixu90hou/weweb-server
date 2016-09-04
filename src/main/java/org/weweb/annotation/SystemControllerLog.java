package org.weweb.annotation;

import java.lang.annotation.*;

/**
 * Created by shen on 2016/5/3.
 * 自定义注解，拦截Controller
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
    String description() default "";
}
