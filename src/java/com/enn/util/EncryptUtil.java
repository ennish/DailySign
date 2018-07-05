package com.enn.util;

import com.thoughtworks.xstream.core.util.Base64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author tw
 */
public class EncryptUtil {
    /**
     * 将message加密以md5方式加密，并以base64形式转码
     * @param message
     * @return
     */
    public static String encryptByMd5(String message){
        String newMessage = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            Base64Encoder base64Encoder = new Base64Encoder();
            newMessage = base64Encoder.encode(messageDigest.digest(message.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newMessage;
    }

}
