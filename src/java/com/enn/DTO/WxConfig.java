package com.enn.DTO;

/**
 * @author tw
 * 读取微信配置
 */
public interface WxConfig {

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
