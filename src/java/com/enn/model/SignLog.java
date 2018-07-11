package com.enn.model;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.enn.DTO.SignLogDTO;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author hacker
 * 签到记录
 */
public class SignLog implements Serializable {



    @Id
    private Integer slId;

    private Integer slUserId;

    private String slSignTime;

    private String slFinishTime;

    private Float slLocX;

    private Float slLocY;

    private Integer slBonus;

    private Integer slStatus;

    private Integer slProjectId;

    public Integer getSlId() {
        return slId;
    }

    public void setSlId(Integer slId) {
        this.slId = slId;
    }

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

    public String getSlFinishTime() {
        return slFinishTime;
    }

    public void setSlFinishTime(String slFinishTime) {
        this.slFinishTime = slFinishTime;
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

    public Integer getSlStatus() {
        return slStatus;
    }

    public void setSlStatus(Integer slStatus) {
        this.slStatus = slStatus;
    }

    public Integer getSlProjectId() {
        return slProjectId;
    }

    public void setSlProjectId(Integer slProjectId) {
        this.slProjectId = slProjectId;
    }

}
