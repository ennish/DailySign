package com.enn.mapper;


import com.enn.model.Project;
import com.enn.model.SignUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-servlet.xml","classpath:applicationContext.xml"})
public class ProjectTest {

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void testSelect(){

        List<Project> projects = projectMapper.selectActive();
        Assert.assertEquals(projects.size(),1);
    }
}
