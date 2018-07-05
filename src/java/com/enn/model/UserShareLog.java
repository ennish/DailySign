package com.enn.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hacker
 * 签到记录
 */
@Table
public class UserShareLog {
    @Id
    private int shareId;

    private int shareUserId;

    private int shareSlId;

    private String shareDate;

    private String shareObj;

    private String shareObjAvatarUrl;

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(int shareUserId) {
        this.shareUserId = shareUserId;
    }

    public int getShareSlId() {
        return shareSlId;
    }

    public void setShareSlId(int shareSlId) {
        this.shareSlId = shareSlId;
    }

    public String getShareDate() {
        return shareDate;
    }

    public void setShareDate(String shareDate) {
        this.shareDate = shareDate;
    }

    public String getShareObj() {
        return shareObj;
    }

    public void setShareObj(String shareObj) {
        this.shareObj = shareObj;
    }

    public String getShareObjAvatarUrl() {
        return shareObjAvatarUrl;
    }

    public void setShareObjAvatarUrl(String shareObjAvatarUrl) {
        this.shareObjAvatarUrl = shareObjAvatarUrl;
    }
}
