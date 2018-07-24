package com.enn.core;

import com.enn.DTO.Result;

/**
 * @author hacker
 */
public class ResultGenerator {
    /**
     *
     * 前端通信状态码
     *
     * 默认为
     * 1200 : 状态已完成
     * 1400 : 请求参数有错误
     * 1500 : 服务端处理请求失败
     */
    public final static String STATUS_COMPLETE_MESSAGE = "SUCCESS";

    public final static String STATUS_INVALID_REQUEST = "PARAM INVALID";

    /**
     * 数据库procedure通信状态码
     *
     */
    public final static String DB_STATUS_COMPLETE = "200";
    public final static String DB_STATUS_ERROR = "500";

    public static Result generateSuccessResult(){
        return new Result().setCode(Result.STATUS_COMPLETE).setMessage(STATUS_COMPLETE_MESSAGE);
    }


    public static Result generateSuccessResult(Object data){
        return new Result().setCode(Result.STATUS_COMPLETE).setMessage(STATUS_COMPLETE_MESSAGE).setBody(data);
    }

    public static Result generateFailResult(String message){
        return new Result().setCode(Result.STATUS_INVALID_REQUEST).setMessage(STATUS_INVALID_REQUEST);
    }

    public static Result generateFailInvalidRequest(){
        return new Result().setCode(Result.STATUS_INVALID_REQUEST).setMessage(STATUS_INVALID_REQUEST);
    }

    /**
     * @author tw
     * 读取微信配置
     */
    public static interface WxConfig {

         String getAppid();

         void setAppid(String appid);

         String getSecret();

         void setSecret(String secret);

         String getToken();

         void setToken(String token);

         String getMsgData();

         void setMsgData(String msgData);

         String getAesKey();

         void setAesKey(String aesKey);

         String getMsgDataFormat();

         void setMsgDataFormat(String msgDataFormat);
    }
}
