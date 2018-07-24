package com.enn.core;

/**
 * @author tw
 * 读取微信配置
 */
public class InternalWxConfig implements ResultGenerator.WxConfig {

    private String appid;
    private String secret;
    private String token;
    private String msgData;
    private String aesKey;
    private String msgDataFormat;

    @Override
    public String getAppid() {
        return appid;
    }

    @Override
    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getMsgData() {
        return msgData;
    }

    @Override
    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }

    @Override
    public String getAesKey() {
        return aesKey;
    }

    @Override
    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    public String getMsgDataFormat() {
        return msgDataFormat;
    }

    @Override
    public void setMsgDataFormat(String msgDataFormat) {
        this.msgDataFormat = msgDataFormat;
    }

}
