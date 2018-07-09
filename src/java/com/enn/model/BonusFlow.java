package com.enn.model;

import javax.persistence.Id;

/**
 * @author tw
 */
public class BonusFlow{

    /**
     * 积分来源
     * 每日签到
     */
    public static final String DAILY_SIGN = "DAILY_SIGN";
    /**
     * 积分来源
     * 累计签到奖励
     */
    public static final String ACC_SIGN = "ACC_SIGN";
    @Id
    private int bonusFlowId;

    private int userId;

    private String bonusTime;

    private int bonusAmount;

    private int bonusInOut;

    private String bonusOrigin;


    public int getBonusFlowId() {
        return bonusFlowId;
    }

    public void setBonusFlowId(int bonusFlowId) {
        this.bonusFlowId = bonusFlowId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBonusTime() {
        return bonusTime;
    }

    public void setBonusTime(String bonusTime) {
        this.bonusTime = bonusTime;
    }

    public int getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(int bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public int getBonusInOut() {
        return bonusInOut;
    }

    public void setBonusInOut(int bonusInOut) {
        this.bonusInOut = bonusInOut;
    }

    public String getBonusOrigin() {
        return bonusOrigin;
    }

    public void setBonusOrigin(String bonusOrigin) {
        this.bonusOrigin = bonusOrigin;
    }
}
