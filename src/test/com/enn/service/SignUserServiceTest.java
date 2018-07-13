package com.enn.service;

import com.enn.DTO.WxSessionData;
import com.enn.model.SignUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-servlet.xml"})
public class SignUserServiceTest {

    @Resource
    SignUserService signUserService;
    @Resource
    WxExtraService wxExtraService;
    @Test
    public void testSelect(){

        SignUser user = new SignUser();
        user.setOpenId("123345tw");
        signUserService.userRegister(user);
        user = signUserService.getSignUserByOpenId(user);
        Assert.assertEquals("123345tw",user.getSessionId());
    }

    @Test
    public void testWxLogin(){
        String code ="033lUSpD1WPao205dSpD1lYVpD1lUSpD";
        WxSessionData data = wxExtraService.requestData(code);
        System.out.println(data.getOpenid()+";"+data.getSessionKey());
    }
}
