package com.qiuguan.perfect.spring.factory;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author qiuguan
 * @date 2023/06/01 12:59:21  星期四
 */
public class MainTest {

    public static void main(String[] args) {

        MySpringApplication mySpringApplication = new MySpringApplication();
        ConfigurableApplicationContext applicationContext = mySpringApplication.createApplicationContext();
        System.out.println("applicationContext = " + applicationContext);
    }
}
