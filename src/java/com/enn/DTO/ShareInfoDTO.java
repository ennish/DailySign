package com.enn.DTO;

/**
 * @author tw
 *
 *  dto for shareLog
 */
public class ShareInfoDTO {

    /**
     * 该信息暂时获取不到，用微信群名
     */
    private String avatalUrl;

    private String shareObj;

    private String shareDate;

    public ShareInfoDTO() {
    }

    public ShareInfoDTO(String avatalUrl, String shareObj, String shareDate) {
        this.avatalUrl = avatalUrl;
        this.shareObj = shareObj;
        this.shareDate = shareDate;
    }

    public String getAvatalUrl() {
        return avatalUrl;
    }

    public void setAvatalUrl(String avatalUrl) {
        this.avatalUrl = avatalUrl;
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
}
