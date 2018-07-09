package com.enn.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tw
 */
public class SignLogDTO {

    /**
     * 0未签到 1已签到
     */
    private int signStatus;
    /**
     * 本次签到获得的积分
     */
    private int signBonus;
    /**
     * 开始签到任务时间
     */
    private String signTime;

    private List<ShareInfoDTO> shareInfos = new ArrayList<ShareInfoDTO>();
    /**
     * 签到完成时间
     */
    private String finishTime;

    public SignLogDTO() {
    }

    public SignLogDTO(int signStatus, int signBonus, String signTime, String finishTime) {
        this.signStatus = signStatus;
        this.signBonus = signBonus;
        this.signTime = signTime;
        this.finishTime = finishTime;
    }

    public int getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(int signStatus) {
        this.signStatus = signStatus;
    }

    public int getSignBonus() {
        return signBonus;
    }

    public void setSignBonus(int signBonus) {
        this.signBonus = signBonus;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public List<ShareInfoDTO> getShareInfos() {
        return shareInfos;
    }

    public void setShareInfos(List<ShareInfoDTO> shareInfos) {
        this.shareInfos = shareInfos;
    }
}
