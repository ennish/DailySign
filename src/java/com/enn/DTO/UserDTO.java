package com.enn.DTO;

public class UserDTO {

    private String nickName;
    private String avatarUrl;
    private Integer account;
    private String lastLoginTime;
    private String exAccount;

    public UserDTO() {
    }

    public UserDTO(String nickName, String avatarUrl, Integer account, String lastLoginTime) {
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.account = account;
        this.lastLoginTime = lastLoginTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getExAccount() {
        return exAccount;
    }

    public void setExAccount(String exAccount) {
        this.exAccount = exAccount;
    }
}
