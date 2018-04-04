package com.luzj.kpimanage.addkpis.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author luzj
 * @description: 记录日志的切面，采用注解是切点定义
 * @date 2018/4/4
 */
@Component
@Aspect
public class LogAspect {
    private Logger logger = Logger.getLogger(getClass());

    //规定日志切点
    @Pointcut("@annotation(com.luzj.kpimanage.addkpis.log.MyLog)")
    public void logPoint(){};

    //建言
    @Before("logPoint()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录请求内容
        logger.info("URL:"+request.getRequestURL().toString());
        logger.info("Http_Method:"+request.getMethod());
        logger.info("IP:"+request.getRemoteAddr());
        logger.info("Class_Method:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        logger.info("ARGS："+ Arrays.toString(joinPoint.getArgs()));

        logger.info("请求参数打印:");
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length>0 )
            for (int i = 0; i < args.length; i++) {
                logger.info("\t"+args[i]);
            }
    }




    @AfterReturning(returning = "ret",pointcut = "logPoint()")
    public void after(Object ret){
        //todo 请求的返回值
        logger.info("RESPONSE:"+ret);
    }


}
