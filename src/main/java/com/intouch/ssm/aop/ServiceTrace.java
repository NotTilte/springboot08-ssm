package com.intouch.ssm.aop;

import com.intouch.ssm.util.PropertiesUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
* 定义环绕通知
* */

@Component
@Aspect
public class ServiceTrace {
    //定义时间格式常量
        private static final DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");

   //定义切入点
    @Pointcut("within(com.intouch.ssm.service.*)")
    private void servicePointcut(){}

    //定义环绕通知
    @Around(value = "servicePointcut()")
    public Object traceLog(ProceedingJoinPoint pjpint) throws Throwable{
        //获得目标对象执行方法说明信息
        String methodInfo=getTargetMethodInfo(pjpint);
        //记录业务方法执行的起始时间点1111111111
        long start=System.currentTimeMillis();
        System.out.println("AOP环绕通知："+dateFormat.format(new Date(start))+"...方法开始执行..."+methodInfo);

        //通过proceedingJoinPoint 对象将当前执行权移交给下一个链式对象（连接点
        Object obj=pjpint.proceed();

        //记录业务方法执行结束的时间点
        long end=System.currentTimeMillis();
        System.out.println("AOP环绕通知"+dateFormat.format(new Date(end))+"...方法执行结束..."+"耗时"+(end-start)+"毫秒");
        return obj;
    }

    //定义获得目标对象执行方法的信息
    private  String getTargetMethodInfo(ProceedingJoinPoint pjpint){
        //获得目标对象类名
        String className=pjpint.getTarget().getClass().getName();
        //获得目标对象执行方法的名称
        String methodName=pjpint.getSignature().getName();
        //拼接properties文件属性名称
        String key=className+"."+methodName;
        //获取属性值
        String value= PropertiesUtil.getProperyValue(key);
        return value;
    }
}
