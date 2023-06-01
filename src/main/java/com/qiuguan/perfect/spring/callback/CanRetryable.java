package com.qiuguan.perfect.spring.callback;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author qiuguan
 * @date 2023/06/01 18:18:16  星期四
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CanRetryable {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    boolean enable() default false;

    String prefix() default "cache_";
}
