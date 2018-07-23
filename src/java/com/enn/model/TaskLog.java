package com.enn.model;

import javax.persistence.Id;

/**
 * 签到记录类
 *
 * @author hacker
 */
public class TaskLog {
    @Id
    private Integer taskLogId;

    private Integer taskUserId;

    private Integer taskId;

    private Integer taskBonus;

    private String taskTime;

    public Integer getTaskLogId() {
        return taskLogId;
    }

    public void setTaskLogId(Integer taskLogId) {
        this.taskLogId = taskLogId;
    }

    public Integer getTaskUserId() {
        return taskUserId;
    }

    public void setTaskUserId(Integer taskUserId) {
        this.taskUserId = taskUserId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskBonus() {
        return taskBonus;
    }

    public void setTaskBonus(Integer taskBonus) {
        this.taskBonus = taskBonus;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }
}
