package com.enn.controller;

import com.enn.DTO.Result;
import com.enn.DTO.WxSessionData;
import com.enn.model.SignUser;
import com.enn.service.SignUserService;
import com.enn.service.WxExtraService;
import com.enn.util.ConstantUtil;
import com.enn.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author hacker
 */
@RestController
@RequestMapping(value = "user/")
public class UserLoginController {
    @Autowired
    private SignUserService signUserService;
    @Autowired
    private JedisUtil jedisUtil;
//    @Autowired
//    private WxMaService wxMaService;
    @Autowired
    private WxExtraService wxExtraService;

    /**
     *
     * 微信登录
     *
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login")
    public String wxLogin(@RequestParam("code") String code) {
        Result r = new Result();
        SignUser user = new SignUser();
        try {
//            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            WxSessionData session = wxExtraService.requestData(code);
            if(session==null||session.getOpenid()==null){
                r.setCode(Result.STATUS_INVALID_REQUEST);
                return r.toString();
            }
            user.setOpenId(session.getOpenid());
            /**
             * 数据库中无数据应加入
             */
            if (!signUserService.isUserExists(user)) {
                signUserService.userRegister(user);
            }
            user = signUserService.getSignUserByOpenId(user);
            //放入缓存前为回话加入sessionIdh
            user.setSessionKey(session.getSessionKey());
            user.setSessionId(UUID.randomUUID().toString());
            jedisUtil.set(user.getSessionId(), user, ConstantUtil.SESSION_EXPIRE_SECONDS);
            r.setBody(user.getSessionId());
        } catch ( Exception e) {
            /**
             * 登录失败
             */
            e.printStackTrace();
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage(e.getMessage());
        }
        return r.toString();
    }

    /**
     * 判断session是否有效
     *
     * @param sessionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "authentic")
    public String authenticate(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId) {
        Result r = new Result();
        if (!jedisUtil.exists(sessionId)) {
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage("invalid sessionid");
            return r.toString();
        }
        return r.toString();
    }
}
