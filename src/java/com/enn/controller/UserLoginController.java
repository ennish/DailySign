package com.enn.controller;

import com.enn.DTO.Result;
import com.enn.DTO.WxSessionData;
import com.enn.core.ResultGenerator;
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
@ResponseBody
@RestController
@RequestMapping(value = "user/")
public class UserLoginController {
    @Autowired
    private SignUserService signUserService;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private WxExtraService wxExtraService;

    /**
     * 微信登录
     * 登录前应用checkSession检验，session的有效性
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "login")
    public String wxLogin(@RequestParam("code") String code) {
        Result r = new Result();
        SignUser user = new SignUser();
        try {
            WxSessionData session = wxExtraService.requestData(code);
            if (session == null || session.getOpenid() == null) {
                r.setCode(Result.STATUS_INVALID_REQUEST);
                return r.toString();
            }
            user.setOpenId(session.getOpenid());
            /**
             * 数据库中无数据应加入
             */
            if (!signUserService.isUserExists(user)) {
                signUserService.addUser(user);
            }
            SignUser user2 = signUserService.getSignUserByOpenId(user.getOpenId());
            //放入缓存前为会话加入sessionId
            user2.setSessionKey(session.getSessionKey());
            user2.setSessionId(UUID.randomUUID().toString());
            jedisUtil.set(user2.getSessionId(), user2, ConstantUtil.SESSION_EXPIRE_SECONDS);
            r.setBody(user2.getSessionId());
        } catch (Exception e) {
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

    /**
     * 获取个人相关信息
     * 当前积分 etc
     */
    @RequestMapping(value = "detail")
    public String getUserInfo(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId) {
        Result r = new Result();
        SignUser user = new SignUser();
        if (!jedisUtil.exists(sessionId)) {
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage("invalid sessionid");
            return r.toString();
        }
        user = (SignUser) jedisUtil.get(sessionId);
        user = signUserService.getUserAbstract(user.getOpenId());
        r.setBody(user);
        return r.toString();
    }

    /**
     * 用户获取账户绑定验证码。
     * 1.判断当前用户是否已绑定过，绑定过的不允许再绑定
     * 2.调用chainway接口，判断该号码是否为chainway会员
     * 3.若2返回结果为true，生成、记录并发送验证码
     */
    @RequestMapping(value = "getBindCode")
    public String getAccountCode(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId, @RequestParam("phone") String phone) {
        Result r = new Result();
        SignUser user = new SignUser();
        if (!jedisUtil.exists(sessionId)) {
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage("invalid sessionid");
            return r.toString();
        }
        jedisUtil.set(user.getUserId()+"-"+phone, UUID.randomUUID().toString()
                , 60 * 10L);
        return ResultGenerator.generateSuccessResult().toString();
    }

    /**
     * 绑定用户手机号。
     * 1.验证code的有效性（a.是否有对应phone。b.时效性。）
     * 2.绑定手机号。
     */
    @RequestMapping(value = "accountBind")
    public String bindAccount(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId,@RequestParam("phone")String phone, @RequestParam("code") String code) {
        Result r = new Result();
        if (!jedisUtil.exists(sessionId)) {
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage("invalid sessionid");
            return r.toString();
        }
        SignUser user = (SignUser) jedisUtil.get(sessionId);
        String tempCode = jedisUtil.get(user.getUserId()+"-"+phone).toString();
        if (tempCode==null||code.equals(tempCode)) {
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage("invalid code");
            return r.toString();
        }
        r = signUserService.updateUserBind(user,phone);
        return r.toString();
    }

}
