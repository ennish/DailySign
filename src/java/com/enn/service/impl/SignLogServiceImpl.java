package com.enn.service.impl;

import com.alibaba.fastjson.JSON;
import com.enn.DTO.*;
import com.enn.mapper.*;
import com.enn.model.*;
import com.enn.service.ProjectService;
import com.enn.service.SignLogService;
import com.enn.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.enn.DTO.Result.DB_STATUS_ERROR;
import static com.enn.DTO.TaskDTO.TaskStatus.ACCESSIBLE;
import static com.enn.DTO.TaskDTO.TaskStatus.FINISH;
import static com.enn.DTO.TaskDTO.TaskStatus.INACCESSIBLE;

/**
 * @author hacker
 */
@Service
public class SignLogServiceImpl implements SignLogService {

    @Autowired
    private SignLogMapper signLogMapper;
    @Autowired
    private UserShareMapper userShareMapper;
    @Autowired
    private BonusFlowMapper bonusFlowMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private SignUserMapper signUserMapper;
    @Autowired
    private ProjectService projectService;

    /**
     * 获取用户签到信息
     *
     * @param user
     * @return
     */
    @Override
    public Result getUserSignInfo(SignUser user) {
        Result result = new Result();
        SignLogDTO signLogDTO = new SignLogDTO();

        //获取今日签到信息
        SignLog signLog = signLogMapper.getSignLogByUser(user);
        if (signLog != null) {
            //获取用户分享信息
            signLogDTO = new SignLogDTO(signLog.getSlStatus(), signLog.getSlBonus(), signLog.getSlSignTime(), signLog.getSlFinishTime());
            List<UserShareLog> shareLogList = userShareMapper.getShareLogsByUser(user);
            List<ShareInfoDTO> shareInfoDTOS = new ArrayList<ShareInfoDTO>(shareLogList.size());
            for (UserShareLog log : shareLogList
                    ) {
                ShareInfoDTO tempDTO = new ShareInfoDTO(log.getShareObjAvatarUrl(), log.getShareObj(), log.getShareDate());
                shareInfoDTOS.add(tempDTO);
            }
            signLogDTO.setShareInfos(shareInfoDTOS);
            result.setBody(signLogDTO);
        }
        return result;
    }

    /**
     * 获取用户当天分享进度
     *
     * @param user
     * @return
     */
    @Override
    public Result getUserShareList(SignUser user) {
        Result result = new Result();
        List<UserShareLog> shareLogs = userShareMapper.getShareLogsByUser(user);
        List<ShareInfoDTO> shareInfoDTOS = null;
        if (shareLogs != null) {
            shareInfoDTOS = new ArrayList<ShareInfoDTO>(shareLogs.size());
            ShareInfoDTO temp;
            for (UserShareLog log : shareLogs
                    ) {
                temp = new ShareInfoDTO(log.getShareObjAvatarUrl(), log.getShareObj(), log.getShareDate());
                shareInfoDTOS.add(temp);
            }
        }
        result.setBody(shareInfoDTOS);
        return result;
    }

    /**
     * 用户分享到微信群
     *
     * @param log 用户
     * @return res wx敏感数据
     */
//    @Transactional
    @Override
    public Result addUserShare(UserShareLog log, String data, String iv, SignUser user) {
        Result result = new Result();
        /**
         * 解密res
         */
        Map<String, String> map = EncryptUtil.descrptAes(data, user.getSessionKey(), iv);
        if (map != null && EncryptUtil.CODE_SUCCESS.equals(map.get("status"))) {
            String innerData = map.get("data");
            Map innerMap = (Map) JSON.parse(innerData);
            String openGId = (String) innerMap.get("openGId");
            log.setShareObj(openGId);
            Map innerMap2 = (Map) innerMap.get("watermark");
            //TODO 数据真实性校验
//            if (!innerMap2.get(WxConstants.APPID_NAME).equals("此处校验自己的appid")) {
//
//            }

        } else {
            result.setCode(Result.STATUS_INVALID_REQUEST);
            result.setMessage("invalid data");
            return result;
        }
        Project project = projectService.getProjectActive();
        //目标群当天只能被同一用户分享一次,每个用户每天分享次数达到要求，才能签到
        if (userShareMapper.isObjShared(log) > 0 || userShareMapper.getShareNums(log.getShareUserId()) >= project.getShareTimesLimit()) {
            result.setCode(Result.STATUS_INVALID_REQUEST);
            result.setMessage("您已分享过该群,或您的分享次数已到达最大值");
            return result;
        }
        //是否有签到记录，没有则新增一条
        if (signLogMapper.hasDaySignLog(log.getShareUserId()) <= 0) {
            SignLog slog = new SignLog();
            slog.setSlUserId(log.getShareUserId());
            if (signLogMapper.addSignLog(slog) <= 0) {
                result.setCode(Result.STATUS_INVALID_REQUEST);
                result.setMessage("分享失败");
                return result;
            }
        }

        //新增用户分享记录
        if (userShareMapper.userShare(log) > 0) {
            result.setMessage("分享成功");
        }
        //如果满足签到条件，修改签到记录状态为可签到
        int nums = userShareMapper.getShareNums(log.getShareUserId());
        if (nums >= project.getShareTimesLimit()) {
            signLogMapper.updateUserSign(log);
        }
        return result;
    }

    /**
     * 用户签到
     * TODO
     * 更改状态
     * 奖励分配
     * 记录流水
     * 更改用户积分数
     *
     * @param user 签到用户
     * @return 签到结果
     */
    @Override
    public Result addUserSign(SignUser user, Project project) {

        Result result = signLogMapper.finishSign(user.getUserId(), project.getProjectId());
        if (DB_STATUS_ERROR.equals(result.getCode())) {
            result.setCode(Result.STATUS_INVALID_REQUEST);
        } else {
            result.setCode(Result.STATUS_COMPLETE);
            result.setMessage("签到失败");
        }
        return result;
    }

    /**
     * 获取用户任务列表
     *
     * @param userId 用户id
     */
//    @Transactional
    @Override
    public Result initTaskList(int userId) {
        Result result = new Result();

        Example example = new Example(Task.class);
        example.orderBy("taskCycle");
        List<Task> listTask = taskMapper.selectByExample(example);
        //获取用户任务完成记录
        List<TaskLog> listTaskLog = taskMapper.getTaskList(userId);
        List<TaskDTO> listDTO = new ArrayList<TaskDTO>();
        //获取用户当前周期连续签到天数
        DatePair datePair = signLogMapper.getCycleDate();
        int signNumbs = signLogMapper.getSignNums(userId, datePair.getStartDay(), datePair.getEndDay());
        if (listTask != null) {
            //设置用户任务完成状态
            for (Task task : listTask) {

                TaskDTO taskDTO = new TaskDTO();
                taskDTO.setTaskId(task.getTaskId());
                taskDTO.setTaskName(task.getTaskName());
                taskDTO.setTaskSignNum(task.getTaskSignNum());
                taskDTO.setTaskBonus(task.getTaskBonus());
                taskDTO.setStatus(INACCESSIBLE.getStatus());
                //判断用户是否可以领取任务奖励
                if (signNumbs >= task.getTaskSignNum()) {
                    taskDTO.setStatus(ACCESSIBLE.getStatus());
                }
                for (TaskLog log : listTaskLog) {
                    //判断用户是否已经领取该任务奖励
                    if (log.getTaskId().equals(task.getTaskId())) {
                        taskDTO.setStatus(FINISH.getStatus());
                    }
                }
                listDTO.add(taskDTO);
            }
        }
        result.setBody(listDTO);
        return result;
    }

    /**
     * 用户领取任务奖励
     * 1获取任务周期
     * 2判断周期内连续签到天数是否满足要求
     * 3是否已签，否则签到
     *
     * @param taskId 任务id
     * @param userId 用户id
     */
//    @Transactional
    @Override
    public Result getTaskBonus(int taskId, int userId) {
        Result result = taskMapper.getTaskBonus(taskId, userId);
        if (Result.STATUS_COMPLETE.equals(result.getCode())) {
            result.setMessage("领取成功");
        } else {
            result.setMessage("领取失败");
        }
        return result;
    }

    /**
     * 获取本月签到记录
     */
    @Override
    public Result getSignRecord(int userId) {

        return null;
    }

}
