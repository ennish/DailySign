package com.enn.DTO;

/**
 * 签到任务
 *
 * @author hacker
 */
public class TaskDTO {

    public enum TaskStatus {
        /**
         * INACCESSIBLE 不可领取状态
         * ACCESSIBLE 可领取状态
         * FINISH 已完成状态
         */
        INACCESSIBLE(0), ACCESSIBLE(1), FINISH(2);
        private int status;

        TaskStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }
    }

    private Integer taskId;
    private String taskName;
    private Integer taskBonus;
    private Integer taskSignNum;
    private Integer status;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public Integer getStatus() {
        return status;
    }

    public Integer getTaskBonus() {
        return taskBonus;
    }

    public void setTaskBonus(Integer taskBonus) {
        this.taskBonus = taskBonus;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskBonus='" + taskBonus + '\'' +
                ", taskSignNum=" + taskSignNum +
                ", status=" + status +
                '}';
    }
}
