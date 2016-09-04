package org.weweb.annotation;

import java.lang.annotation.*;

/**
 * Created by shen on 2016/5/3.
 * 自定义注解拦截service
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    String description() default "";
}
