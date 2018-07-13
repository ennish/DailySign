package com.enn.service;

import com.enn.DTO.WxConfig;
import com.enn.DTO.WxSessionData;

import java.util.Map;

/**
 * 微信工具service
 */
public interface WxExtraService {
    String JSCODE_TO_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
    /**
     * 解密 公共数据
     * @param sessionKey
     * @param encryptData
     * @param iv
     * @return
     */
     Map<String,String> getResData(String sessionKey,String encryptData,String iv);

    /**
     * 请求sessionKey
     */
    WxSessionData requestData(String code);
}
