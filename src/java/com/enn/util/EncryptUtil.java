package com.enn.util;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加解密工具
 *
 * @author tw
 */
public class EncryptUtil {
    /**
     * 结果状态
     */
    public static final String CODE_SUCCESS = "1";
    public static final String CODE_FAIL = "2";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将message加密以md5方式加密，并以base64形式转码
     *
     * @param message
     * @return
     */
    public String encryptByMd5(String message) {
        String newMessage = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            Base64Encoder base64Encoder = new Base64Encoder();
            newMessage = base64Encoder.encode(messageDigest.digest(message.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            logger.error("加密失败", e);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newMessage;
    }

    /**
     * 微信aes-128 对称解密
     * 对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充。
     * 对称解密的目标密文为 Base64_Decode(encryptedData)。
     * 对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
     * 对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
     *
     * @param encryptedData 消息体
     * @param session_key   微信返回的session_key
     * @param iv            解密向量
     * @author tw
     */
    public static Map<String, String> descrptAes(String encryptedData, String session_key, String iv) {
        Map map = new HashMap();
        try {
            byte[] resultByte = AES.decrypt(Base64.decodeBase64(encryptedData),
                    Base64.decodeBase64(session_key),
                    Base64.decodeBase64(iv));
            if (null != resultByte && resultByte.length > 0) {
                String userInfo = new String(resultByte, "UTF-8");
                map.put("status", CODE_SUCCESS);
                map.put("msg", "解密成功");
                map.put("data", userInfo);
            } else {
                map.put("status", CODE_FAIL);
                map.put("msg", "解密失败");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String args[]) {
        String content = "psjo91fUSyeHT9v75trpCS27C1pjg24R6xOC+8fZsPlNar3uKw753LIBzBKA60rI3UgvnTYgYiDqwpR9jOmgT3BptcNeRAp+MXYhq/dbEinfDaMBctQZbUCpEz/gX2ggg9WbYBmDK8fLBvn4OsTCoQ==";
        String iv = "Gl+MpJ6G8m56NLJtNRTEow==";
        String aesKey = "iemjVgZ6z1Qi/Vdrz/9PbQ==";
//        String gid = descryptByAes(content,aesKey,iv);
//        String gid = decrypt(content,aesKey,iv);
//        System.out.println(gid);
//        Map map0 = descrptAes(content, aesKey, iv);
//        System.out.println(map0.size());
        String iv2 = "r7BXXKkLb8qrSNn05n0qiA==";
        String aesKey2 = "tiihtNczf5v6AKRyjwEUhQ==";
        String encryptedData =
                "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM" +
                        "QmRzooG2xrDcvSnxIMXFufNstNGTyaGS" +
                        "9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+" +
                        "3hVbJSRgv+4lGOETKUQz6OYStslQ142d" +
                        "NCuabNPGBzlooOmB231qMM85d2/fV6Ch" +
                        "evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6" +
                        "/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw" +
                        "u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn" +
                        "/Hz7saL8xz+W//FRAUid1OksQaQx4CMs" +
                        "8LOddcQhULW4ucetDf96JcR3g0gfRK4P" +
                        "C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB" +
                        "6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns" +
                        "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd" +
                        "lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV" +
                        "oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG" +
                        "20f0a04COwfneQAGGwd5oa+T8yO5hzuy" +
                        "Db/XcxxmK01EpqOyuxINew==";
//        Map map = descrptAes(encryptedData, aesKey2, iv2);
//        System.out.println(map.size());
    }
}
