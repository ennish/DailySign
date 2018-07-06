package com.enn.service.impl;

import com.enn.DTO.ShareInfoDTO;
import com.enn.DTO.SignLogDTO;
import com.enn.mapper.BonusFlowMapper;
import com.enn.mapper.SignLogMapper;
import com.enn.mapper.UserShareMapper;
import com.enn.model.Result;
import com.enn.model.SignLog;
import com.enn.model.SignUser;
import com.enn.model.UserShareLog;
import com.enn.service.SignLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            List<ShareInfoDTO> shareInfoDTOS = new ArrayList<>(shareLogList.size());
            for (UserShareLog log : shareLogList
                    ) {
                ShareInfoDTO tempDTO = new ShareInfoDTO(log.getShareObjAvatarUrl(),log.getShareDate());
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
        Result result = new Result<>();
        List<UserShareLog> shareLogs = userShareMapper.getShareLogsByUser(user);
        List<ShareInfoDTO> shareInfoDTOS = null;
        if (shareLogs != null) {
            shareInfoDTOS = new ArrayList<>(shareLogs.size());
            ShareInfoDTO temp;
            for (UserShareLog log : shareLogs
                    ) {
                temp = new ShareInfoDTO(log.getShareObjAvatarUrl(), log.getShareDate());
                shareInfoDTOS.add(temp);
            }
        }
        result.setBody(shareInfoDTOS);
        return result;
    }

    /**
     * 用户分享到微信群
     *
     * @param user 用户
     * @return
     */
    @Transactional
    @Override
    public Result userShare(SignUser user, UserShareLog log) {
        Result result = new Result();
        /**
         * 目标群当天只能被同一用户分享一次
         */
        if (userShareMapper.isObjShared(user, log.getShareObj()) > 0 || userShareMapper.isObjShared(user, log.getShareObj()) >= 5) {
            result.setCode(Result.STATUS_INVALID_REQUEST);
            result.setMessage("您已分享过该群,或您的分享次数已到达最大值");
            return result;
        }
        /**
         * 若用户当天无签到记录，先建立一条
         */
        if (signLogMapper.hasDaySignLog(user.getUserId()) <= 0) {
            SignLog slog = new SignLog();
            slog.setSlUserId(user.getUserId());
//            slog.setSlLocX();
//            slog.setSlLocY();
            signLogMapper.addSignLog(slog);
        }
        if (userShareMapper.userShare(user, log.getShareObj()) > 0) {
            result.setMessage("分享成功");
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
    public Result userSign(SignUser user) {
        Result result = new Result();
        /**
         * 获取签到活动的次数要求及奖励
         *  活动次数及奖励应从project获取
         */
        int bonus = 1;
        int times = 5;
        if (userShareMapper.getShareNums(user) >= times) {
            if (signLogMapper.finishSign(user, bonus) <= 0) {
                result.setCode(Result.STATUS_INVALID_REQUEST);
                result.setMessage("签到失败");
                return result;
            }
            /**
             * TODO
             * 奖励分配
             * 记录流水
             * 更改用户积分数
             *
             */

        }
        result.setMessage(bonus + "");
        return result;
    }
}
