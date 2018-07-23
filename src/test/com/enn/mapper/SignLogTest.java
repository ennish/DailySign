package com.enn.mapper;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.enn.DTO.DatePair;
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
public class SignLogTest {

    @Autowired
    private SignLogMapper signLogMapper;

    @Test
    public void testSelect(){

         DatePair datePair = signLogMapper.getCycleDate();
         System.out.println(datePair.getStartDay()+"and"+datePair.getEndDay());

         int nums = signLogMapper.getSignNums(1,"2018-01-11","2018-11-11");
        System.out.println(nums);

    }
    @Test
    public void testGetSignCalendar(){
        List<String> list = signLogMapper.getSignRecord(72);
        for (String s :
                list) {
            System.out.println(s+";");
        }
    }
}
