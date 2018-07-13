package com.enn.service.impl;

import com.enn.model.Project;
import com.enn.service.ProjectService;
import org.springframework.stereotype.Service;

/**
 * @author hacker
 */
@Service
public class ProjectServiceImpl implements ProjectService {


    @Override
    public Project getProjectActive() {
        Project project = new Project();
        project.setProjectName("default");
        project.setProjectFrom("2018-8-1");
        project.setProjectEnd("2018-12-1");
        project.setProjectId(1);
        /**
         * 要求分享次数
         */
        project.setShareTimesLimit(5);
        /**
         * 签到积分
         */
        project.setBonusProject(1);
        return project;
    }
}
