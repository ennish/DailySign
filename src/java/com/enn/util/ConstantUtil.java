package com.enn.util;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.time.Instant;
import java.util.Date;

/**
 * @author tw
 *
 * 该类定义一些常量
 */
public class ConstantUtil {

    /**
     * 自定义sessionId的名称
     */
    public static final String SESSION_ID_NAME = "ds_session_id";


    /**
     * session过期时间(秒)（默认一个小时）
     */
    public static final Long SESSION_EXPIRE_SECONDS = 60*60L;
    /**
     *
     */
    public static final String a ="";

    public static void main(String[] args){
        System.out.println(new Date());
        System.out.println(Instant.now());
    }
}
