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
        
        return project;
    }
}
