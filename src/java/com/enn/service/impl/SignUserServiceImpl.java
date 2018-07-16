package com.enn.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.enn.mapper.SignUserMapper;
import com.enn.model.SignUser;
import com.enn.service.SignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author hacker
 */
@Service
public class SignUserServiceImpl implements SignUserService {

    @Autowired
    private SignUserMapper signUserMapper;

    /**
     * 微信登录用户默认注册
     * @param user
     * @return
     */
    @Override
    public Integer addUser(SignUser user) {
        Integer result = signUserMapper.insertSelective(user);
        if(result>0) {
            return result;
        }
        return 0;
    }

    /**
     * 根据openid或userid查询用户是否存在
     * @param user
     * @return
     */
    @Override
    public boolean isUserExists(SignUser user) {
        return signUserMapper.isUserExists(user)>0;
    }


    /**
     * 根据openId查询
     * @param user
     * @return
     */
    @Override
    public SignUser getSignUserByOpenId(SignUser user) {
        if(StringUtils.isEmpty(user.getOpenId())){
            return null;
        }
        Example example = new Example(SignUser.class);
        example.createCriteria().andEqualTo("openId",user.getOpenId());
        return signUserMapper.selectOneByExample(example);
    }
}
