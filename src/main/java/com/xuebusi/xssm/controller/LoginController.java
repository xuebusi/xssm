package com.xuebusi.xssm.controller;

import com.alibaba.fastjson.JSON;
import com.belerweb.social.bean.Result;
import com.belerweb.social.weixin.api.Weixin;
import com.belerweb.social.weixin.bean.AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.belerweb.social.weixin.bean.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: syj
 * @CreateDate: 2018/1/20 13:46
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    private static String appid = "";
    private static String secret = "";

    /**
     * 微信授权登陆
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginByWeChat")
    @ResponseBody
    public User loginByWeChat(HttpServletRequest request) {
        String code = request.getParameter("code");
        Weixin weixin = new Weixin(appid, secret);
        //根据code获取access_token
        Result<AccessToken> accessTokenResult = weixin.getOAuth2().accessToken(code);
        AccessToken accessToken = accessTokenResult.getResult();
        //刷新 access_token
        //Result<AccessToken> newAccessTokenResult = weixin.getOAuth2().refreshAccessToken(accessToken.getRefreshToken());
        //根据access_token获取用户信息
        Result<User> userResult = weixin.getUser().snsapiUserInfo(accessToken.getToken(), accessToken.getOpenId());
        User user = userResult.getResult();
        System.out.println(JSON.toJSONString(user));
        return user;
    }

    /**
     * QQ授权登陆
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginByQQ")
    @ResponseBody
    public User loginByQQ(HttpServletRequest request) {


        return null;
    }

    /**
     * 微博授权登陆
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginByWeiBo")
    @ResponseBody
    public User loginByWeiBo(HttpServletRequest request) {


        return null;
    }
}
