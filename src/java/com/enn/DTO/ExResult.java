package com.enn.DTO;

import java.io.Serializable;

/**
 * 远程调用返回结果
 * @author hacker
 */
public class ExResult implements Serializable {
    /**错误码：正常、成功***/
    public static final int ERROR_CODE_SUCCESS = 0;

    /**错误码：失败***/
    public static final int ERROR_CODE_FAIL = 1;

    /**错误码：不存在***/
    public static final int ERROR_CODE_NOT_EXIST = 1001;

    /**错误码：存在***/
    public static final int ERROR_CODE_EXIST = 1002;

    /**错误码：不匹配***/
    public static final int ERROR_CODE_NOT_MATCH = 1003;

    //错误码：
    private int errorCode = 0;
    //结果
    private Object result = "";

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
