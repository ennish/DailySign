package com.enn.service;

import com.enn.model.Project;
import com.enn.DTO.Result;
import com.enn.model.SignUser;
import com.enn.model.UserShareLog;

/**
 * @author hacker
 */
public interface SignLogService {

    /**
     * 获取用户签到信息
     */
    Result getUserSignInfo(SignUser user);
    /**
     *  获取用户当日分享进度
     * @param user 签到用户
     * @return 添加成功后返回主键
     */
    Result getUserShareList(SignUser user);

    /**
     * 用户签到分享
     * @param log 微信分享群唯一标识
     * @return boolean
     */
    Result addUserShare(UserShareLog log,String data,String iv,SignUser user);

    /**
     * 用户签到
     * @param user 签到用户
     */
    Result addUserSign(SignUser user, Project project);

    /**
     *获取任务列表
     *
     */
    Result initTaskList(int userId);

    /**
     *领取签到奖励
     *
     */
    Result getTaskBonus(int taskId,int userId);

    /**
     * 获取本月签到记录
     * @param userId 用户id
     */
    Result getSignRecord(int userId);
}
