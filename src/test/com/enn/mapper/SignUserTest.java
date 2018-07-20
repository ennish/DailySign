package com.enn.mapper;


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
public class SignUserTest {

    @Autowired
    private SignUserMapper signUserMapper;

    @Test
    public void testSelect(){

        signUserMapper.insert(new SignUser("owiefoqfqewf","tw"));
        signUserMapper.insert(new SignUser("028owijfqef","sw"));
        List<SignUser> signUserList = signUserMapper.selectAll();
        Assert.assertEquals(2,signUserList.size());

    }
    @Test
    public void selectByName(){
        signUserMapper.insert(new SignUser("123","sw"));
//        Example example = new Example(SignUser.class);
//        example.createCriteria().andEqualTo("openId","123");
//        SignUser user = signUserMapper.selectOneByExample(example);
        SignUser user = signUserMapper.selectByOpenId("123");
        Assert.assertNotNull(user.getUserId());
    }
}
