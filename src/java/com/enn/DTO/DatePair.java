package com.enn.DTO;

/**
 * 配合存储过程 get_cycle使用
 * 获取当前周/月/年的第一天，最后一天
 * @author hacker
 */
public class DatePair {

    private String startDay;
    private String endDay;

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
