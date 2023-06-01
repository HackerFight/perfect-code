package com.qiuguan.perfect.spring.callback;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author qiuguan
 * @date 2023/06/01 18:06:15  星期四
 */
public class MyAnnotationUtils {

    /**
     * 判断某个类中是否存在 {@link CanRetryable} 的注解
     * @param clazz
     * @return
     *
     * 1.进来先调用 doWithMethods()方法
     * 2.进入到 doWithMethods()方法内部，然后调用 MethodCallback 和 MethodFilter
     *
     * 他是先定位到类，然后找到类中的所有方法，遍历所有方法，找到需要的方法
     *
     * @see org.springframework.retry.annotation.RetryConfiguration.AnnotationMethodsResolver
     */
    public static boolean hasAnnotatedMethods(Class<?> clazz) {
        final AtomicBoolean found = new AtomicBoolean();
        //doWithMethods 方法是没有返回值的
        MyReflectionUtils.doWithMethods(clazz, method -> {
            if (found.get()) {
                return;
            }

            CanRetryable annotation = AnnotationUtils.findAnnotation(method, CanRetryable.class);
            if (annotation != null) {
                found.set(true);
            }
        });

        return found.get();
    }

    /**
     * 1.进来先调用 doWithMethods()方法
     * 2.进入到 doWithMethods()方法内部，然后调用 MethodCallback 和 MethodFilter
     */
    public static boolean hasAnnotatedMethodsWithFilter(Class<?> clazz) {
        final AtomicBoolean found = new AtomicBoolean();
        MyReflectionUtils.doWithMethods(clazz, method -> {
            if (found.get()) {
                return;
            }

            CanRetryable annotation = AnnotationUtils.findAnnotation(method, CanRetryable.class);
            if (annotation != null) {
                found.set(true);
            }
        }, new MyReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                return !ReflectionUtils.isObjectMethod(method);
            }
        });

        return found.get();
    }

    public static boolean hasParamAnnotation(Method method, Class<? extends Annotation> annotationType) {
        for (Annotation[] parameterAnnotation : method.getParameterAnnotations()) {
            for (Annotation annotation : parameterAnnotation) {
                 if (annotation.annotationType() == annotationType) {
                     return true;
                 }
            }
        }
        return false;
    }


    public static <A extends Annotation> A getAnnotation(AnnotatedElement annotatedElement, Class<A> annotationType) {
        return AnnotationUtils.getAnnotation(annotatedElement, annotationType);
    }
}
