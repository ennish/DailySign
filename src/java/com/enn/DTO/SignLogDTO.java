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
    private Integer signStatus;
    /**
     * 当日签到获得的积分
     */
    private Integer signBonus;
    /**
     * 用户当日总积分
     */
    private Integer totalBonus;
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

    public SignLogDTO(Integer signStatus, Integer signBonus, String signTime, String finishTime) {
        this.signStatus = signStatus;
        this.signBonus = signBonus;
        this.signTime = signTime;
        this.finishTime = finishTime;
    }

    public Integer getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Integer signStatus) {
        this.signStatus = signStatus;
    }

    public Integer getSignBonus() {
        return signBonus;
    }

    public void setSignBonus(Integer signBonus) {
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

    public Integer getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(Integer totalBonus) {
        this.totalBonus = totalBonus;
    }

}
