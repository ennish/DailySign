package com.enn.service;

import com.enn.model.Project;

/**
 * @author hacker
 */
public interface ProjectService {
    /**
     * 获取当前活动规则
     */
     Project getProjectActive();

}
