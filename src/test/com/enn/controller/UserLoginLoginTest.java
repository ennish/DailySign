package com.enn.controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:config/spring-servlet.xml"})
public class UserLoginLoginTest {


    @Autowired
    private UserLoginController userLoginController;


    @Test
    public void test1(){
        userLoginController.getAccountCode("","15737644231");
    }
}
