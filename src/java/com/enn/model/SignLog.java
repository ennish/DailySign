package com.enn.model;

import ch.qos.logback.core.net.SyslogOutputStream;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hacker
 * 签到记录
 */
@Table
public class SignLog {
    @Id
    private Integer slId;

    private Integer slUserId;

    private String slSignTime;

    private Float slLocX;

    private Float slLocY;

    private Integer slBonus;

    public Integer getSlUserId() {
        return slUserId;
    }

    public void setSlUserId(Integer slUserId) {
        this.slUserId = slUserId;
    }

    public String getSlSignTime() {
        return slSignTime;
    }

    public void setSlSignTime(String slSignTime) {
        this.slSignTime = slSignTime;
    }

    public Float getSlLocX() {
        return slLocX;
    }

    public void setSlLocX(Float slLocX) {
        this.slLocX = slLocX;
    }

    public Float getSlLocY() {
        return slLocY;
    }

    public void setSlLocY(Float slLocY) {
        this.slLocY = slLocY;
    }

    public Integer getSlBonus() {
        return slBonus;
    }

    public void setSlBonus(Integer slBonus) {
        this.slBonus = slBonus;
    }

    public Integer getSlId() {
        return slId;
    }

    public void setSlId(Integer slId) {
        this.slId = slId;
    }
}
