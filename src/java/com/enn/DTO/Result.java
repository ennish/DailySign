package com.enn.DTO;

import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hacker
 */
public class Result<T>{
    /**
     *
     * 状态码
     *
     * 默认为
     * 1200 : 状态已完成
     * 1400 : 请求参数有错误
     * 1500 : 服务端处理请求失败
     */
    public final static String STATUS_COMPLETE = "1200";

    public final static String STATUS_INVALID_REQUEST = "1400";

    public final static String STATUS_SERVER_ERROR = "1500";

    /**
     * result code
     */
    private String code;

    /**
     * result message
     */
    private String message;

    /**
     * result body
     */
    private T body;

    /**
     * current time
     */
    private Date time;

    public Result() {
        code = STATUS_COMPLETE;
    }

    public Result(String code,String message,T body,Date time){
        code=code;
        message=message;
        body=body;
        time=time;
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
