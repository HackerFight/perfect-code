package com.qiuguan.perfect.spring.factory;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author qiuguan
 * @date 2023/06/01 13:06:17  星期四
 */
public class MySpringApplication {

    private final MyApplicationContextFactory applicationContextFactory = MyApplicationContextFactory.DEFAULT;

    private MyWebApplicationType myWebApplicationType;


    public MySpringApplication(MyWebApplicationType myWebApplicationType) {
        this.myWebApplicationType = myWebApplicationType;
    }

    public MySpringApplication() {
        this(MyWebApplicationType.SERVLET);
    }

    public ConfigurableApplicationContext createApplicationContext(){
        return this.applicationContextFactory.create(this.myWebApplicationType);
    }
}
