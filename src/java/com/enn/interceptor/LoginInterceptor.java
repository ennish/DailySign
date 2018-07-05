package com.enn.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.enn.model.SignUser;
import com.enn.service.SignUserService;
import com.enn.util.ConstantUtil;
import com.enn.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author tw
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private SignUserService signUserService;
    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 1.对接口调用进行拦截，判断是否为微信用户，非微信入口用户不能调用
     * 2.判断用户是否登录，除了登录接口，其他接口必须登录状态才能调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 登录判断
         */
        String sessionId = request.getParameter(ConstantUtil.SESSION_ID_NAME);
        if(!StringUtils.isEmpty(sessionId)&& jedisUtil.exists(sessionId)){
            /**
             * 用户已登录,更新缓存
             */
            SignUser user = (SignUser) jedisUtil.get(sessionId);
            user.setLastLoginTime(new Date().toString());
            jedisUtil.set(sessionId,user);
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
