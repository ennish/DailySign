package com.enn.model;


import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author hacker
 * 签到用户
 */

public class SignUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer userId;
    /**
     * 28 chars from weixin
     */
    private String openId;
    /**
     * 20 chars from weixin
     */
    private String nickName;
    private String gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    /**
     * 29 chars from weixin
     */
    private String unionId;

    private String phoneNo;
    /**
     * 积分账户
     */
    private Integer account;
    /**
     * 微信返回的sessionKey
     */
    @Transient
    private String sessionKey;
    /**
     * 自定义session id
     */
    @Transient
    private String sessionId;
    /**
     * 上次登录时间
     */
    @Transient
    private String lastLoginTime;

    private String token;
    private String exAccount;

    public SignUser() {
    }

    public SignUser(String openId, String nickName) {
        this.openId = openId;
        this.nickName = nickName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getExAccount() {
        return exAccount;
    }

    public void setExAccount(String exAccount) {
        this.exAccount = exAccount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
