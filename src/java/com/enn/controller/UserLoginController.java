package com.enn.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.enn.DTO.*;
import com.enn.core.ResultGenerator;
import com.enn.core.SysConstants;
import com.enn.model.SignUser;
import com.enn.service.SignUserService;
import com.enn.service.WxExtraService;
import com.enn.util.ConstantUtil;
import com.enn.util.JedisUtil;
import com.enn.util.message.chainway.ChainwayNewSMS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 微信登录
     * 登录前应用checkSession检验，session的有效性
     *
     * @param code 微信登录接口参数
     */
    @RequestMapping(value = "login")
    public String wxLogin(@RequestParam("code") String code) {
        Result r = new Result();
        SignUser user = new SignUser();
        try {
            WxSessionData session = wxExtraService.requestData(code);
            if (session == null || session.getOpenid() == null) {
                r.setCode(Result.STATUS_INVALID_REQUEST);
                logger.warn("用户登录失败code="+code);
                return r.toString();
            }
            user.setOpenId(session.getOpenid());
            //数据库中无数据应加入
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
            logger.error("登录失败："+e.getMessage(),e);
            e.printStackTrace();
            r.setCode(Result.STATUS_INVALID_REQUEST);
            r.setMessage(e.getMessage());
        }
        return r.toString();
    }

    /**
     * 判断session是否有效
     */
    @Deprecated
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
        SignUser user = (SignUser) jedisUtil.get(sessionId);
        user = signUserService.getSignUserByOpenId(user.getOpenId());
        if (user == null || user.getUserId() <= 0) {
            return ResultGenerator.generateFailResult().setMessage("用户不存在").toString();
        }
        UserDTO userDTO = new UserDTO(user.getNickName(), user.getAvatarUrl(), user.getAccount(), user.getLastLoginTime());
        userDTO.setExAccount(user.getExAccount());
        return ResultGenerator.generateSuccessResult().setBody(userDTO).toString();
    }

    /**
     * 用户获取账户绑定验证码。
     * 1.判断当前用户是否已绑定过，绑定过的不允许再绑定
     * 2.调用chainway注册验证码申请接口，判断该号码是否为chainway会员
     * 3.若该号码是chainway会员，DailySign 生成、记录并发送验证码
     * 4.若该号码不是chainway会员，chainway 发送注册验证码给该手机号
     */
    @RequestMapping(value = "getBindCode/{phone}")
    public String getAccountCode(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId, @PathVariable("phone") String phone) {
        SignUser user = (SignUser) jedisUtil.get(sessionId);

        RestTemplate restTemplate = new RestTemplate();
        ExResult result = restTemplate.getForObject(SysConstants.getRegValifyURL()+phone, ExResult.class);

         // 是chainway会员,绑定账号,验证码使用6位（避免与chainway重复）
        if (ExResult.ERROR_CODE_EXIST == result.getErrorCode()) {
            String randomCode = UUID.randomUUID().toString();
            randomCode = randomCode.substring(randomCode.length()-6);
            jedisUtil.set(user.getUserId() + "-" + phone, randomCode
                    , 60 * 10L);
            try {
                if (ChainwayNewSMS.sendSms(phone, randomCode)) {
                    return ResultGenerator.generateSuccessResult("验证码发送成功").toString();
                }
            } catch (UnsupportedEncodingException e) {
                logger.error("短信发送失败:"+e.getStackTrace(),e);
                e.printStackTrace();
            }
        }
        // 不是chainway会员，则验证码已发至用户手机
        if(ExResult.ERROR_CODE_SUCCESS == result.getErrorCode()){

        }
        return ResultGenerator.generateFailResult("获取验证码失败").toString();
    }

    /**
     * 绑定用户手机号。
     * 1. 先判断是否是DailySign的验证码，是的话完成绑定。
     * 2. 如果不是DailySign的验证码，说明是新注册用户。
     * 3. 调用chainway注册接口，完成注册，注册成功则完成绑定。
     *
     */
    @RequestMapping(value = "accountBind")
    public String bindAccount(@RequestParam(ConstantUtil.SESSION_ID_NAME) String sessionId, @RequestParam("phone") String phone, @RequestParam("code") String passport) {
        SignUser user = (SignUser) jedisUtil.get(sessionId);
        String tempCode = jedisUtil.get(user.getUserId() + "-" + phone).toString();

        if(StringUtils.isEmpty(passport)){
            return ResultGenerator.generateFailResult("验证码无效").toString();
        }
        System.out.println(passport);
        //验证码来自DailySign以外，交由chainway接口处理
        if (!tempCode.equals(passport)) {
            RestTemplate restTemplate = new RestTemplate();
            ExResult exResult = restTemplate.postForObject(SysConstants.getUserRegisterURL(), "phone=" + phone+"passport="+passport+"&code=&wechatid=", ExResult.class);
            if (ExResult.ERROR_CODE_SUCCESS == exResult.getErrorCode()){
               ExUser exUser = JSON.parseObject(exResult.getResult().toString(),ExUser.class);
               user.setToken(exUser.getUid()+"");
               return ResultGenerator.generateSuccessResult("绑定成功").toString();
            }
            return ResultGenerator.generateFailResult("绑定失败").toString();
        }
        System.out.println(phone);
        //号码绑定
        Result r = signUserService.updateUserBind(user, phone);
        return r.toString();
    }

}
