package com.enn.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.enn.DTO.ShareInfoDTO;
import com.enn.mapper.UserShareMapper;
import com.enn.model.Result;
import com.enn.model.SignLog;
import com.enn.model.SignUser;
import com.enn.model.UserShareLog;
import com.enn.service.SignLogService;
import com.enn.service.SignUserService;
import com.enn.util.ConstantUtil;
import com.enn.util.JedisUtil;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 获取用户签到信息
     * 返回用户签到状态及用户分享信息
     */
    @RequestMapping(value="info")
    public String getSignInfo(@RequestParam(ConstantUtil.SESSION_ID_NAME)String sessionId){
        Result r = new Result();
        if(!jedisUtil.exists(sessionId)){
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage("session无效");
            return r.toString();
        }
        SignUser user = new SignUser();
        user = (SignUser) jedisUtil.get(sessionId);
        r = signLogService.getUserSignInfo(user);
        return r.toString();
    }
    /**
     * 获取签到分享 进度
     *
     * @param sessionId sessionId
     * @return
     */
    @RequestMapping(value = "shareInfo")
    public String getShareInfo(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId) {
        Result r = new Result();
        if (!jedisUtil.exists(sessionId)) {
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage("invalid sessionId");
            return r.toString();
        }
        SignUser user = (SignUser) jedisUtil.get(sessionId);
        if (user != null) {
            r = signLogService.getUserShareList(user);
        }
        return r.toString();
    }

    /**
     * 签到分享
     */
    @RequestMapping("share")
    public String userShare(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId, @RequestParam("shareObj") String shareObj) {
        Result r = new Result();
        SignUser user = new SignUser();
        if(!jedisUtil.exists(sessionId)){
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage("invalid sessionid");
        }
        UserShareLog log = new UserShareLog();
        log.setShareObj(shareObj);
        log.setShareObjAvatarUrl("");
        log.setShareUserId(user.getUserId());
        r = signLogService.userShare(user,log);
        return r.toString();
    }


    /**
     * 签到
     *
     * @param sessionid sessionId
     * @return
     */
    @RequestMapping(value = "signIn")
    public String signIn(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionid) {
        Result r = new Result();
        SignUser user = new SignUser();

        return r.toString();
    }


}
