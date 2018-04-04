package com.luzj.kpimanage.addkpis.log;

import java.lang.annotation.*;

/**
 * @author luzj
 * @description: 注解规则AOP，使用该注解来标注统一日志记录的方法
 * @date 2018/4/4
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String name() default "";
}
