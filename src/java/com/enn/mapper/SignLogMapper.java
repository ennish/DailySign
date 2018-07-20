package com.enn.mapper;

import com.enn.DTO.DatePair;
import com.enn.DTO.Result;
import com.enn.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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
     *
     * @param userId 要查询的用户id
     */
    @Select("select count(*) from sign_log where sl_user_id = #{slUserId} and TO_DAYS(NOW())=TO_DAYS(sl_sign_time)")
    int hasDaySignLog(int userId);

    /**
     * 为用户新增今日签到记录
     *
     * @param signLog
     */
    @Insert("insert into sign_log(sl_user_id,sl_project_id,sl_sign_time) value(#{slUserId},#{slProjectId},NOW())")
    int addSignLog(SignLog signLog);

    /**
     * 用户完成签到
     * 奖励分配
     *
     * @param userId
     * @param projectId
     * @return 签到成功返回值大于0
     * @Update("update sign_log set sl_status = #{project.projectId} and sl_bonus = #{project.bonusProject} and sl_finish_time = NOW()")
     */
    @Select("call p_user_sign(#{userId},#{projectId})")
    Result finishSign(@Param("userId") int userId, @Param("projectId") int projectId);

    /**
     * 用户 分享任务完成 更改状态
     *
     * @param log
     * @return
     */
    @Update("update sign_log set sl_status = 1 where sl_user_id = #{shareUserId} and TO_DAYS(sl_sign_time) = TO_DAYS(NOW())")
    int updateUserSign(UserShareLog log);

    /**
     * 根据签到周期获取，当前周期第一天，最后一天
     * 签到周期在project中设置
     * @return DatePair include attribute startDay and endDay
     */
    @Select("call p_get_cycle()")
    DatePair getCycleDate();

    /**
     * 获取用户周期内连续签到天数
     * @param userId 用户id
     * @param startDay 开始时间
     * @param endDay 结束时间
     * @return 签到天数
     */
    @Select("select ifnull(count(*),0) from sign_log where sl_user_id = #{userId} and sl_status=2 and sl_finish_time between #{startDay} and #{endDay}")
    int getSignNums(@Param("userId") int userId,@Param("startDay") String startDay,@Param("endDay") String endDay);

    /**
     * 获取用户本月签到记录，每条记录只包含一列（当月第几天）
     * @param userId
     * @return
     */
    @Select("SELECT DATE_FORMAT(sl_finish_time,'%e') FROM sign_log WHERE sl_user_id = #{userId} and MONTH(NOW())=MONTH(sl_finish_time) order by sl_finish_time")
    List<String> getSignRecord(@Param("userId")int userId);

}
