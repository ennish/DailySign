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
@RestController
@RequestMapping(value = "sign/")
public class SignController {
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private SignLogService signLogService;

    /**
     * 获取签到分享进度
     *
     * @param sessionId sessionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "info")
    public String getSignInfo(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId) {
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
    @ResponseBody
    @RequestMapping()
    public String userShare(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId, @RequestParam("shareObj") String shareObj) {
        Result r = new Result();
        SignUser user = new SignUser();

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
