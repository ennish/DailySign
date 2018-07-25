package com.enn.util.message.chainway;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ChainwayNewSMS {
    /**
     * 登录账号
     ****/
    private static final String OperID = "cwzn";

    /**
     * 登录密码
     ***/
    private static final String OperPass = "cwzn";

    /**
     * 内容类型
     ****/
    private static final String ContentType = "15";

    /*****/
    private static final String SendTime = "";

    /**********/
    private static final String ValidTime = "";

    /*******/
    private static final String AppendID = "";


    private static final String KEY = ""; //企业ID，参数值为服务商提供的API KEY；

    //这个是http的,端口88
    private static final String Send_URL = ""; //接口地址

    /**
     * 发送短信
     ***/
    public static boolean sendSms(String mobile, String content) {
        try {
            /* 将内容用URLEncoder编一次GBK */
//			String EncoderContent = URLEncoder.encode(content, "GBK");
            String EncoderContent = URLEncoder.encode(content, "UTF-8");  //GBK短信内容乱码
            String queryString = "key=" + KEY + "&phone=" + mobile + "&content=" + EncoderContent;
            System.out.println(queryString);
            String jsonStr = getStringByHttps(Send_URL + "?" + queryString);
            System.out.println("send sms resutl ==: " + jsonStr + "   " + queryString);
            if (StringUtils.isNotEmpty(jsonStr)) {
                JSONObject josnJsonObject = JSONObject.fromObject(jsonStr); //string 转jsonn
                Integer code = Integer.parseInt(josnJsonObject.getString("error")); //JSONObject 转String  再转int
                System.out.println(code);
                return getResult(code);
            }
            return false;
        } catch (Exception e) {
            System.out.println("send sms error --- mobile:" + mobile + " \tcontent:" + content);
            e.printStackTrace();
            return false;
        }

    }

    /**
     * /**
     *
     * @param url
     * @return
     */
    public static String getStringByHttps(String url) {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.custom().build();

        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            InputStream inputStream = response.getEntity().getContent();
            result = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"error\":-1 }";
    }


    private static boolean getResult(int code) {
        boolean flag = false;
        switch (code) {
            case 0: {
                flag = true;
                System.out.println("操作成功");
                break;
            }
            case 100001: {
                System.out.println("非法的企业账号");
                break;
            }
            case 100002: {
                flag = true;
                System.out.println("请求地址不匹配");
                break;
            }
            case 100003: {
                System.out.println("企业帐号剩余次数不足");
                break;
            }
            case 100004: {
                System.out.println("业务参数不完整");
                break;
            }
            case 200001: {
                System.out.println("数据查询异常");
                break;
            }
            case 200002: {
                System.out.println("服务器异常");
                break;
            }
            default :
                System.out.println("未知错误");
                break;
        }
        return flag;
    }

    public static void main(String[] args) {
//        ChainwayNewSMS.sendSms("15727644231", "测试短信test的验证码是 ：123145");
    }
}
