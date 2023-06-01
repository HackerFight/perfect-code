package com.qiuguan.perfect.spring.callback;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.retry.annotation.Retryable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author qiuguan
 * @date 2023/06/01 13:21:59  星期四
 */
@Retryable
public class Test {

    public static void main(String[] args) throws Exception {

        boolean b = MyAnnotationUtils.hasAnnotatedMethods(Student.class);
        System.out.println("b = " + b);


        //spring 自带的工具类, 判断某一个类上是否存在某个注解
        CanRetryable annotation = AnnotationUtils.findAnnotation(Student.class, CanRetryable.class);
        System.out.println("annotation = " + annotation);

        //spring 自带的工具类, 判断某一个方法上是否存在某个注解
        CanRetryable say = AnnotationUtils.findAnnotation(Student.class.getMethod("say"), CanRetryable.class);
        System.out.println("say = " + say);

        Annotation[] annotations = Student.class.getAnnotations();
        for (Annotation ann : annotations) {
            CanRetryable a = AnnotationUtils.getAnnotation(ann, CanRetryable.class);
            System.out.println("a = " + a);

            Map<String, Object> annotationAttributes = AnnotationUtils.getAnnotationAttributes(ann);
            System.out.println("annotationAttributes = " + annotationAttributes);
        }

        //获取参数上的注解
        Method method = Student.class.getMethod("run", int.class);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            System.out.println(parameterAnnotation[0]);
        }

        boolean hasParamAnnotation = MyAnnotationUtils.hasParamAnnotation(method, CanRetryParam.class);
        System.out.println("hasParamAnnotation = " + hasParamAnnotation);
    }
}
