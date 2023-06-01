package com.qiuguan.perfect.spring.factory;

import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author qiuguan
 * @date 2023/06/01 13:00:33  星期四
 *
 * @see org.springframework.boot.ApplicationContextFactory
 *
 * 其实这就是一个简单工厂，只不过写法比较优雅
 */
@FunctionalInterface
public interface MyApplicationContextFactory {

    MyApplicationContextFactory DEFAULT = (myWebApplicationType) -> {
        try {
            switch (myWebApplicationType) {
                case SERVLET:
                    return new AnnotationConfigServletWebServerApplicationContext();
                case REACTIVE:
                    return new AnnotationConfigReactiveWebApplicationContext();
                default:
                    return new AnnotationConfigApplicationContext();
            }

        } catch (Exception ex) {
            throw new IllegalStateException("not create server instance", ex);
        }
    };


    ConfigurableApplicationContext create(MyWebApplicationType myWebApplicationType);
}
