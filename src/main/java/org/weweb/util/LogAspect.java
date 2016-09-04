/*
package org.shen.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import Logger;
import LogService;

import java.lang.reflect.Method;

*/
/**
 * Created by shen on 2016/5/2.
 *//*


@Aspect
public class LogAspect {
    // @Autowired
    private LogService logService;

    */
/**
     * 添加业务逻辑方法切入点
     *//*

    @Pointcut("execution(* org.shen.service.*.insert(..))")
    public void insertCell() {
    }

    */
/**
     * 更新业务逻辑方法切入点
     *//*



    @Pointcut("execution(* org.shen.service.*.update(..))")

    public void updateCell() {
    }

   */
/*
    *删除业务逻辑方法切入点
    *//*


    @Pointcut("execution(* org.shen.service.*.delete(..))")
    public void deleteCell() {
    }

    @AfterReturning(value = "insertCell()", returning = "rtv")
    public void insertLog(JoinPoint joinPoint, Object rtv) {
        Long userId = 1L;
        if (userId == null) {//没有管理员登录
            return;
        }
        if (joinPoint.getArgs() == null) {//没有参数
            return;
        }
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        String opContent = optionContent(joinPoint.getArgs(), methodName);
        Logger log = new Logger();
        log.setUserid(userId);
        log.setContent(opContent);
        log.setOperation(ConstantUtil.insertLog);
        logService.add(log, true);
    }

    @AfterReturning(value = "updateCell()", returning = "rtv")
    public void updateLog(JoinPoint joinPoint, Object rtv) {
        Long userId = 1L;
        if (userId == null) {//没有管理员登录
            return;
        }
        if (joinPoint.getArgs() == null) {//没有参数
            return;
        }
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        String opContent = optionContent(joinPoint.getArgs(), methodName);
        Logger log = new Logger();
        log.setUserid(userId);
        log.setContent(opContent);
        log.setOperation(ConstantUtil.updateLog);
        logService.update(log, true);
    }

    @AfterReturning(value = "deleteCell()", returning = "rtv")
    public void deleteLog(JoinPoint joinPoint, Object rtv) {
        Long userId = 1L;
        if (userId == null) {//没有管理员登录
            return;
        }
        if (joinPoint.getArgs() == null) {//没有参数
            return;
        }
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        StringBuffer sb = new StringBuffer();
        sb.append(methodName);
        String className = null;
        for (Object info : joinPoint.getArgs()) {
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            sb.append("[参数1，类型:" + className + "，值:(id:" + joinPoint.getArgs()[0] + ")");
        }
        Logger log = new Logger();
        log.setUserid(userId);
        log.setContent(sb.toString());
        log.setOperation(ConstantUtil.deleteLog);
        logService.delete(log);
    }
    */
/*
     * 使用java反射来获取方法(insert,update)的参数值
     * 将参数拼接为操作内容
     * @param objs
     * @param mName
     * @return
    *//*

    public String optionContent(Object[] objs, String mName) {
        if (objs == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(mName);
        String className = null;
        int index = 1;
        //遍历参数对象
        for (Object obj : objs) {
            //获取对象类型
            className = obj.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            sb.append("[参数]" + index + "，类型：" + className + "，值：");
            //获取对象的所有的方法
            Method[] methods = obj.getClass().getDeclaredMethods();
            //遍历对象的所有的方法
            for (Method method : methods) {
                String methodName = method.getName();
                //判断是不是get方法
                if (methodName.indexOf("get") == -1) {
                    continue;
                }
                Object rsValue = null;

                try {
                    rsValue = method.invoke(obj);
                } catch (Exception e) {
                    continue;
                }
                sb.append("(" + methodName + ":" + rsValue + ")");
            }
            sb.append("]");
            index++;
        }
        return sb.toString();
    }
}
*/
