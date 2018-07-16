package com.enn.mapper;

import com.enn.DTO.Result;
import com.enn.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author hacker
 */
public interface SignLogMapper extends Mapper<SignLog> {

    /**
     * 获取用户今日签到信息
     */
    @Select("select * from sign_log where sl_user_id = #{userId} and TO_DAYS(NOW())=TO_DAYS(sl_sign_time)")
    SignLog getSignLogByUser(SignUser user);
    /**
     * 判断用户今日签到记录数
     * @param userId 要查询的用户id
     */
    @Select("select count(*) from sign_log where sl_user_id = #{slUserId} and TO_DAYS(NOW())=TO_DAYS(sl_sign_time)")
    int hasDaySignLog(int userId);
    /**
     * 为用户新增今日签到记录
     * @param signLog
     */
    @Insert("insert into sign_log(sl_user_id,sl_project_id,sl_sign_time) value(#{slUserId},#{slProjectId},NOW())")
    int addSignLog(SignLog signLog);

    /**
     * 用户完成签到
     * 奖励分配
     * @param user
     * @return 签到成功返回值大于0
     * @Update("update sign_log set sl_status = #{project.projectId} and sl_bonus = #{project.bonusProject} and sl_finish_time = NOW()")
     */
    @Select("select user_sign(#{user.userId},#{project.projectId})")
    Result finishSign(SignUser user, Project project);

    /**
     *  用户 分享任务完成 更改状态
     * @param log
     * @return
     */
    @Update("update sign_log set sl_status = 1 where sl_user_id = #{shareUserId} and TO_DAYS(sl_sign_time) = TO_DAYS(NOW())")
    int updateUserSign(UserShareLog log);
}
