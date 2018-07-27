package com.enn.mapper;

import com.enn.DTO.Result;
import com.enn.model.Task;
import com.enn.model.TaskLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 签到任务及签到任务完成记录
 * @author hacker
 */
public interface TaskMapper extends Mapper<Task> {

    /**
     * 获取用户签到进度
     * @param userId 用户id
     * @return 任务记录
     */
    @Select("select * from task_log where task_user_id = #{userId} and TO_DAYS(task_time)=TO_DAYS(NOW()) order by task_log_id")
    List<TaskLog>   getTaskList(@Param("userId")int userId);
    /**
     *
     * 用户领取任务奖励
     * @param taskId 任务Id
     * @param userId 用户Id
     * @return 领取是否成功
     *
     */
    @Select("call p_get_task_bonus(#{taskId},#{userId})")
    Result getTaskBonus(@Param("taskId") int taskId,@Param("userId") int userId);

}
