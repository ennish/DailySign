package com.enn.service.impl;

import com.enn.mapper.ProjectMapper;
import com.enn.model.Project;
import com.enn.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hacker
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Project getProjectActive() {
        Project project = null;
        List<Project> projectList = projectMapper.selectActive();
        if (projectList != null && !projectList.isEmpty()) {
            project = projectList.get(0);
        }
        return project;
    }
}
