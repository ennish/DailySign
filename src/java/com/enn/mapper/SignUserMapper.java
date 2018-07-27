package com.enn.mapper;

import com.enn.model.SignUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    /**
     * 根据userid 获取用户基本信息
     */
    @Select("SELECT nick_name,avatar_url,account FROM sign_user where open_id = #{openId}")
    SignUser selectUserInfo(@Param("openId")String openId);

    /**
     * 判断用户是否绑定账号。
     *
     */
    @Select("SELECT count(1) from sign_user WHERE  user_id=#{userId} and ex_account is not null")
    int queryIfExits(@Param("userId")int userId);

    /**
     * 用户绑定账号
     *
     */
    @Update("UPDATE sign_user SET ex_account = #{phone} WHERE user_Id = #{userId}")
    int updateSignUser(@Param("userId")int userId,@Param("phone")String phone);
}
