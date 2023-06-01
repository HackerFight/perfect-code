package com.qiuguan.perfect.spring.callback;

/**
 * @author qiuguan
 * @date 2023/06/01 14:50:49  星期四
 */
@CanRetryable
public class Student {

    @CanRetryable()
    public String say(){
        return "say hello";
    }

    public void run(@CanRetryParam int id){
        System.out.println("run.....");
    }
}
