package com.qiuguan.perfect.spring.callback;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author qiuguan
 * @date 2023/06/01 18:49:58  星期四
 */
@Target(value = {ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CanRetryParam {

    String value() default "";

    int id() default 100;
}
