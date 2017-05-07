package org.weweb.interceptor;

import java.lang.annotation.*;

/**
 * Created by shen on 2016/5/28.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogDescription {
    LogType type();

}
