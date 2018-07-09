package com.enn.mapper;

import com.enn.model.SignUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author hacker
 */
public interface SignUserMapper extends Mapper<SignUser> {
    /**
     * 根据userid或openid判断用户是否存在
     * @author tw
     *
     * @param user
     * @return
     */
    @Select("select count(1) from sign_user where open_id = #{openId}")
    int isUserExists(SignUser user);

    /**
     * 根据openId查询
     *
     */
    @Select("select user_id,open_id,nick_name,gender,language,city,province,country,avatar_url,union_id,phone_no,account from sign_user where open_id = #{openId}")
    SignUser selectByOpenId(String openId);
}
