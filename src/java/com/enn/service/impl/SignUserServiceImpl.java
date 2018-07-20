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
     *
     * @param user
     * @return
     */
    @Override
    public Integer addUser(SignUser user) {
        Integer result = signUserMapper.insertSelective(user);
        if (result > 0) {
            return result;
        }
        return 0;
    }

    /**
     * 根据openid或userid查询用户是否存在
     *
     * @param user
     * @return
     */
    @Override
    public boolean isUserExists(SignUser user) {
        return signUserMapper.isUserExists(user) > 0;
    }


    /**
     * 根据openId查询user
     *
     * @param openId
     * @return
     */
    @Override
    public SignUser getSignUserByOpenId(String openId) {
        Example example = new Example(SignUser.class);
        example.createCriteria().andEqualTo("openId", openId);
        return signUserMapper.selectOneByExample(example);
    }

    /**
     * 获取用户简略信息
     */
    @Override
    public SignUser getUserAbstract(String openId) {
        SignUser user = new SignUser();
        Example example = new Example(SignUser.class);
        example.createCriteria().andEqualTo("openId", openId);
        SignUser userTemp = signUserMapper.selectOneByExample(example);
        if (userTemp != null) {
            user.setNickName(userTemp.getNickName());
            user.setLastLoginTime(userTemp.getLastLoginTime());
            user.setAvatarUrl(userTemp.getLastLoginTime());
            user.setAccount(userTemp.getAccount());
        }
        return user;
    }
}
