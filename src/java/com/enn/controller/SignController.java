package com.enn.controller;

import com.enn.DTO.Result;
import com.enn.core.ResultGenerator;
import com.enn.model.*;
import com.enn.service.ProjectService;
import com.enn.service.SignLogService;
import com.enn.util.ConstantUtil;
import com.enn.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 签到任务相关接口
 *
 * @author hacker
 */
@ResponseBody
@RestController
@RequestMapping(value = "sign/")
public class SignController {
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private SignLogService signLogService;
    @Autowired
    private ProjectService projectService;


    /**
     * 获取用户签到信息
     * 用户签到状态及用户分享信息
     */
    @RequestMapping(value = "info")
    public String getSignInfo(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId) {
        Result r = new Result();
        SignUser user = (SignUser) jedisUtil.get(sessionId);
        if (user == null || user.getUserId() <= 0) {
           return ResultGenerator.generateFailResult("该用户不存在").toString();
        }
        r = signLogService.getUserSignInfo(user);
        return r.toString();
    }


    /**
     * 签到分享
     *
     * @param data 微信群敏感数据
     * @param iv   加密向量
     */
    @RequestMapping(value = "share")
    public String userShare(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId, @RequestParam("encryptedData") String data, @RequestParam("iv") String iv) {
        SignUser user = (SignUser) jedisUtil.get(sessionId);
        UserShareLog log = new UserShareLog();
        log.setShareUserId(user.getUserId());
        Result r = signLogService.addUserShare(log, data, iv, user);
        return r.toString();
    }

    /**
     * 签到
     *
     * @param sessionId sessionId
     * @return
     */
    @RequestMapping(value = "signIn")
    public String signIn(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId) {
        SignUser user = (SignUser) jedisUtil.get(sessionId);
        Project project = projectService.getProjectActive();
        Result r = signLogService.addUserSign(user, project);
        return r.toString();
    }

    /**
     * 获取签到任务进度
     */
    @RequestMapping(value = "tasks")
    public String getSignTaskList(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId) {
        SignUser user = (SignUser) jedisUtil.get(sessionId);
        if (user == null || user.getUserId() <= 0) {

        }
        Result r = signLogService.initTaskList(user.getUserId());
        return r.toString();
    }

    /**
     * 领取签到任务奖励
     */
    @RequestMapping(value = "taskBonus")
    public String getTaskBonus(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId, @RequestParam("taskId") int taskId) {

        SignUser user = (SignUser) jedisUtil.get(sessionId);
        Result r = signLogService.getTaskBonus(taskId, user.getUserId());
        return r.toString();
    }

    /**
     * 获取用户本月签到记录
     */
    @RequestMapping(value = "taskRecord")
    public String getSignRecord(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId) {
        SignUser user = (SignUser) jedisUtil.get(sessionId);
        Result r = signLogService.getSignRecord(user.getUserId());
        return r.toString();
    }

}
