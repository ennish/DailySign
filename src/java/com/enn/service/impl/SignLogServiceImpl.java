package com.enn.service.impl;

import com.alibaba.fastjson.JSON;
import com.enn.DTO.Result;
import com.enn.DTO.ShareInfoDTO;
import com.enn.DTO.SignLogDTO;
import com.enn.mapper.BonusFlowMapper;
import com.enn.mapper.SignLogMapper;
import com.enn.mapper.UserShareMapper;
import com.enn.model.*;
import com.enn.service.ProjectService;
import com.enn.service.SignLogService;
import com.enn.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        SignLog signLog = signLogMapper.getSignLogByUser(user);
        if (signLog != null) {
            //获取用户分享信息
            SignLogDTO signLogDTO = new SignLogDTO(signLog.getSlStatus(), signLog.getSlBonus(), signLog.getSlSignTime(), signLog.getSlFinishTime());
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
    @Transactional
    @Override
    public Result userShare(UserShareLog log, String data, String iv, SignUser user) {
        Result result = new Result();
        /**
         * 解密res
         */
        Map<String, String> map = EncryptUtil.descrptAes(data, user.getSessionKey(), iv);
        if (map != null && map.get("status") == "1") {
            String innerData = map.get("data");
            Map innerMap = (Map) JSON.parse(innerData);
            String openGId = (String) innerMap.get("openGId");
            log.setShareObj(openGId);
            Map innerMap2 = (Map) innerMap.get("watermark");
            /**
             * 数据真实性校验
             */
            if (innerMap2.get("appid").equals("此处填写自己的appid")) {
                // TODO
            }

        } else {
            result.setCode(Result.STATUS_INVALID_REQUEST);
            result.setMessage("invalid data");
            return result;
        }
        /**
         * 目标群当天只能被同一用户分享一次,每个用户每天分享5次
         */
        if (userShareMapper.isObjShared(log) > 0 || userShareMapper.getShareNums(log.getShareUserId()) >= 5) {
            result.setCode(Result.STATUS_INVALID_REQUEST);
            result.setMessage("您已分享过该群,或您的分享次数已到达最大值");
            return result;
        }
        /**
         * 若用户当天无签到记录，先建立一条
         */
        if (signLogMapper.hasDaySignLog(log.getShareUserId()) <= 0) {
            SignLog slog = new SignLog();
            slog.setSlUserId(log.getShareUserId());
//            slog.setSlLocX();
//            slog.setSlLocY();
            /**
             * 更新签到数据
             */
            if (signLogMapper.addSignLog(slog) <= 0) {
                result.setCode(Result.STATUS_INVALID_REQUEST);
                result.setMessage("分享失败");
                return result;
            }
        }
        Project project = projectService.getProjectActive();

        if (userShareMapper.userShare(log) > 0) {
            result.setMessage("分享成功");
        }
        /**
         * 分享次数达到要求，更新用户签到状态
         */
        int nums = userShareMapper.getShareNums(log.getShareUserId());
        if (nums >= project.getShareTimesLimit()) {
            signLogMapper.updateUserSign(log);
        }
        return result;
    }

    /**
     * 用户签到
     *
     * @param user 签到用户
     * @return
     */
    @Transactional
    @Override
    public Result userSign(SignUser user, Project project) {
        Result result = new Result();
        /**
         * TODO
         * 奖励分配
         * 记录流水
         * 更改用户积分数
         *
         */
        result = signLogMapper.finishSign(user, project);

        return result;
    }
}
