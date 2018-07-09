package com.enn.service;

import com.enn.model.SignUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Service;

/**
 * @author hacker
 */
public interface SignUserService {

    /**
     * 签到用户注册
     * @param user
     * @return 添加成功后返回主键
     */
    Integer userRegister(SignUser user);

    /**
     * 根据id或openid判断用户是否存在
     * @param user 根据用户openid或userId判断
     * @return boolean
     */
    boolean isUserExists(SignUser user);

    /**
     * 根据openid查找userid
     */
    SignUser getSignUserByOpenId(SignUser user);
}
