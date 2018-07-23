package com.enn.mapper;


import com.enn.DTO.TaskDTO;
import com.enn.model.Project;
import com.enn.model.SignUser;
import com.enn.model.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-servlet.xml","classpath:applicationContext.xml"})
public class TaskTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testSelect(){

        Example example = new Example(Task.class);
        example.orderBy("taskId");
        List<Task> listTask = taskMapper.selectByExample(example);
        Assert.assertEquals(listTask.size(),3);

    }
}
