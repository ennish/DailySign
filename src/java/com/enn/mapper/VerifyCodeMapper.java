package com.enn.mapper;

import com.enn.DTO.Result;
import com.enn.model.Task;
import com.enn.model.TaskLog;
import com.enn.model.VerifyCode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 签到任务及签到任务完成记录
 *
 * @author hacker
 */
public interface VerifyCodeMapper extends Mapper<VerifyCode> {

    /**
     * 添加verifycode
     */


    /**
     * 判断验证码是否有效
     */

}
