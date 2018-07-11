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
    Result userShare(UserShareLog log,String data,String iv,SignUser user);

    /**
     * 用户签到
     * @param user 签到用户

     */
    Result userSign(SignUser user, Project project);
}
