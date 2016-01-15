package com.util;

/**
 * Created by risen on 16/1/13.
 */
public class HttpUrl {
    private static String url = "http://120.27.196.221:8080";
    /*注册-发送短信验证码*/
    public static final String SENDSMSCODE = url + "/api/sendSmscode.do";
    /*注册-验证电话号码可用*/
    public static final String AVAILABLEPHONE = url + "/api/availablePhone.do";

}
