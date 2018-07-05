package com.enn.mapper;

import com.enn.model.SignLog;
import com.enn.model.SignUser;
import com.enn.model.UserShareLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author hacker
 */
public interface UserShareMapper extends Mapper<SignUser> {
    /**
     * 查询用户当天分享任务
     * @author tw
     * @param user
     * @return
     */
    @Select("select * from user_share_log where share_user_id = #{userId} and TO_DAYS(NOW())= TO_DAYS(share_date) " +
            " order by share_date desc")
    List<UserShareLog> getShareLogsByUser(SignUser user);

    /**
     * 用户分享
     * @param user
     * @param shareObj 分享群唯一标识
     * @return
     */
    @Insert("insert into user_share_log(share_user_id,share_date,share_obj)value(#{userId},NOW(),#{shareObj})")
    int userShare(SignUser user,String shareObj);

    /**
     * 判断该群当天被某一用户分享次数
     * @param user
     * @param shareObj
     * @return
     */
    @Select("select IFNULL(count(*),0) from user_share_log where share_user_id = #{shareUserId} and share_obj = #{shareObj} and TO_DAYS(NOW())=TO_DAYS(share_date)")
    int isObjShared(SignUser user,String shareObj);

    /**
     * 判断用户签到分享次数
     * @param user
     * @return
     */
    @Select("select IFNULL(count(*),0) from user_share_log where share_user_id = #{shareUserId}  and TO_DAYS(NOW()) = TO_DAYS(share_date)")
    int getShareNums(SignUser user);

}

