package com.enn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enn.DTO.WxSessionData;
import com.enn.core.WxConfig;
import com.enn.service.WxExtraService;
import com.enn.util.EncryptUtil;
import com.enn.util.http.apache.ClientExecutor;
import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hacker
 */
@Service
public class WxExtraServiceImpl implements WxExtraService {
    @Autowired
    private WxConfig wxConfig;

    @Override
    public Map<String, String> getResData(String sessionKey, String encryptData, String iv) {
        EncryptUtil.descrptAes(sessionKey, encryptData, iv);
        return null;
    }

    @Override
    public WxSessionData requestData(String jsCode) {
       WxConfig config = getWxConfig();
        Map<String, String> params = new HashMap<String, String>(8);
        params.put("appid", config.getAppid());
        params.put("secret", config.getSecret());
        params.put("js_code", jsCode);
        params.put("grant_type", "authorization_code");
        String result = "";
        try {
            result = ClientExecutor.execute(JSCODE_TO_SESSION_URL, Joiner.on("&").withKeyValueSeparator("=").join(params));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        WxSessionData data = null;
        try {
            data = JSONObject.parseObject(result, WxSessionData.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return data;
    }

    public  WxConfig getWxConfig() {
        return this.wxConfig;
    }

    public void setWxConfig(WxConfig wxMaConfig) {
        this.wxConfig = wxConfig;
    }
}
