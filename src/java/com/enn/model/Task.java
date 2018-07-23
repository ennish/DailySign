package com.enn.model;

import javax.persistence.Id;

/**
 * 签到任务类
 *
 * @author hacker
 */
public class Task {
    /**
     * 任务周期 周(1),月(2),年(3)
     */
    public enum taskCycle {
        CYCLE_WEEK(1), CYCLE_MONTH(2), CYCLE_YEAR(3);
        private int code;

        taskCycle(int code) {
            this.code = code;
        }
    }

    @Id
    private Integer taskId;
    private Integer taskProjectId;
    private String taskName;
    private Integer taskSignNum;
    private Integer taskBonus;
    private Integer taskCycle;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskProjectId() {
        return taskProjectId;
    }

    public void setTaskProjectId(Integer taskProjectId) {
        this.taskProjectId = taskProjectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskSignNum() {
        return taskSignNum;
    }

    public void setTaskSignNum(Integer taskSignNum) {
        this.taskSignNum = taskSignNum;
    }

    public Integer getTaskBonus() {
        return taskBonus;
    }

    public void setTaskBonus(Integer taskBonus) {
        this.taskBonus = taskBonus;
    }

    public Integer getTaskCycle() {
        return taskCycle;
    }

    public void setTaskCycle(Integer taskCycle) {
        this.taskCycle = taskCycle;
    }
}
