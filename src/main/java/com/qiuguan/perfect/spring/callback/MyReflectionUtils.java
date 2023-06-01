package com.qiuguan.perfect.spring.callback;

import java.lang.reflect.Method;

/**
 * @author qiuguan
 * @date 2023/06/01 14:46:53  星期四
 *
 * @see org.springframework.util.ReflectionUtils
 */
public class MyReflectionUtils {

    public static final MethodFilter USER_DECLARED_METHODS =
            (method -> !method.isBridge() && !method.isSynthetic());

    /**
     *
     */
    public static boolean isUserMethod(Method method){
        return USER_DECLARED_METHODS.matches(method);
    }


    /**
     * 将 MethodCallback 交给方法的调用者去实现
     * 虽然他也是一个工具类，但是还是流出拓展交给调用者
     * @see MyAnnotationUtils
     */
    public static void doWithMethods(Class<?> clazz, MethodCallback mc) {
        doWithMethods(clazz, mc, null);
    }

    /**
     * 将 MethodCallback 和 MethodFilter 交给方法的调用者去实现
     * @see MyAnnotationUtils
     */
    public static void doWithMethods(Class<?> clazz, MethodCallback mc, MethodFilter mf) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            if (mf != null && !mf.matches(m)) {
                continue;
            }

            try {
                mc.doWith(m);
            } catch (Exception e) {
                throw new IllegalStateException("Not allowed to access method '" + m.getName() + "': " + e);
            }
        }

        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            doWithMethods(clazz.getSuperclass(), mc, mf);
        } else if (clazz.isInterface()) {
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                doWithMethods(anInterface, mc, mf);
            }
        }
    }


    /**
     * 针对方法的
     */
    @FunctionalInterface
    public interface MethodFilter {

        boolean matches(Method method);
    }

    /**
     * 针对方法的
     */
    @FunctionalInterface
    public interface MethodCallback {

        void doWith(Method method) throws IllegalArgumentException, IllegalAccessException;
    }

    /**
     * 针对属性的。
     */
    @FunctionalInterface
    public interface FieldFilter {

        boolean matches(Method method);
    }
}
