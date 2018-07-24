package com.enn.model;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author hacker
*/
public class VerifyCode implements Serializable {

    private static final long serialVersionUID = 1532424511474L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    @Id
    private Integer userId;

    /**
    * 验证码发送的手机号
    * isNullAble:0
    */
    private String phone;

    /**
    * 验证码
    * isNullAble:1
    */
    private String code;

    /**
    * 到期时间
    * isNullAble:1
    */
    private String expireTime;

    /**
    * 验证码类型 1账号绑定
    * isNullAble:1
    */
    private Integer verifyType;

    /**
    * 发送状态
    * isNullAble:1,defaultVal:0
    */
    private Integer status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(Integer verifyType) {
        this.verifyType = verifyType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
