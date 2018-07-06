package com.enn.DTO;

/**
 * @author tw
 *
 *  dto for shareLog
 */
public class ShareInfoDTO {

    private String avatalUrl;

    private String shareDate;

    public ShareInfoDTO() {
    }

    public ShareInfoDTO(String avatalUrl, String shareDate) {
        this.avatalUrl = avatalUrl;
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
}
