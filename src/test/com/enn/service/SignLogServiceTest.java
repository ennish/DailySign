package com.enn.service;

import com.enn.DTO.Result;
import com.enn.DTO.TaskDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:config/spring-servlet.xml"})
public class SignLogServiceTest {

    @Resource
    SignLogService signLogServiceService;

    @Test
    public void testSelect(){
        Result result = signLogServiceService.initTaskList(1);
        List<TaskDTO> list = (List<TaskDTO>) result.getBody();
        Assert.assertEquals(list.size(),3);
    }

    /**
     * 获取用户任务进度列表
     */
    @Test
    public void testGetTask(){
        Result result = signLogServiceService.initTaskList(72);
        System.out.println(result.toString());

    }
}
