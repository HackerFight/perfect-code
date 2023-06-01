package com.qiuguan.perfect.spring.factory;

/**
 * @author qiuguan
 * @date 2023/06/01 13:01:58  星期四
 */
public enum MyWebApplicationType {


    /**
     *非web环境
     */
    NONE,

    /**
     * tomcat/undertow/jetty
     */
    SERVLET,

    /**
     * netty
     */
    REACTIVE;

    private static final String[] SERVLET_INDICATOR_CLASSES = {"javax.servlet.Servlet",
            "org.springframework.web.context.ConfigurableWebApplicationContext"};

    private static final String WEBMVC_INDICATOR_CLASS = "org.springframework.web.servlet.DispatcherServlet";
}
