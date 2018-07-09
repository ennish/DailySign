package com.enn.mapper;


import com.enn.model.SignUser;
import com.enn.model.UserShareLog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-root.xml"})
public class UserShareTest {

    @Autowired
    private UserShareMapper userShareMapper;

    @Test
    public void TestSelect() {
        SignUser user = new SignUser();
        user.setUserId(55);
        List<UserShareLog> list = userShareMapper.getShareLogsByUser(user);
//        Assert.assertEquals(1,list.size());

        UserShareLog log = new UserShareLog();
        log.setShareUserId(67);
        log.setShareObj("80ed39b1-affc-43b4-a4a4-96db898cac37");

        Assert.assertEquals(1,userShareMapper.isObjShared(log));
    }

}
