package com.enn.controller;
import com.enn.DTO.Result;
import com.enn.DTO.TaskDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:config/spring-servlet.xml"})
public class SignControllerTest {
    @Autowired
    private SignController signController;

    @Test
    public void test(){

    }
}
