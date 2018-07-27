package com.enn.core;

public final class SysConstants {

    public static final String PROTOCOL_HTTP_DEV = "http://";

    public static final String URL_DEV = "192.9.200.98:8083/";
    public static final String URL_PRO = "120.24.227.202:8080/";
    public static final String CONTEXT = "ChainWayInsurance/rest/";

    /**
     * 判断账户是否存在，若存在会返回uid
     */
    public static final String SERVICE_CHECKPHONE = "userservice/queryIfExists";
    /**
     * 获取注册验证码，末尾必须带phone参数
     */
    public static final String SERVICE_getUserRegValify = "userservice/getUserRegValify/";
    /**
     * 用户注册
     */
    public static final String SERVICE_userRegister = "userservice/userRegister";

    private String protocol;
    private String url;
    private String context;


    public static String getCheckPHoneURL(){
        return PROTOCOL_HTTP_DEV + URL_PRO + CONTEXT + SERVICE_CHECKPHONE;
    }

    public static String getRegValifyURL(){
        return PROTOCOL_HTTP_DEV + URL_PRO + CONTEXT + SERVICE_getUserRegValify;
    }

    public static String getUserRegisterURL(){
        return PROTOCOL_HTTP_DEV + URL_PRO + CONTEXT + SERVICE_userRegister;
    }
}
