package com.enn.mapper;

import com.enn.model.SignUser;
import org.apache.ibatis.annotations.Insert;
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
    @Select("select count(1) from sign_user where user_id = #{userId} or open_id = #{openId}")
    int isUserExists(SignUser user);


}
