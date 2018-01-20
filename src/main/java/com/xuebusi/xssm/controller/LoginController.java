package com.xuebusi.xssm.controller;

import com.alibaba.fastjson.JSON;
import com.belerweb.social.bean.Result;
import com.belerweb.social.qq.connect.api.QQConnect;
import com.belerweb.social.weibo.api.Weibo;
import com.belerweb.social.weixin.api.Weixin;
import com.belerweb.social.weixin.bean.AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: syj
 * @CreateDate: 2018/1/20 13:46
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    /**
     * 微信开发者账号和密码
     */
    private static String weixinAppid = "";
    private static String weixinSecret = "";

    /**
     * QQ开发者账号和密码
     */
    private static String qqAppid = "";
    private static String qqSecret = "";
    //private static String qqRedirectUri = "";

    /**
     * 微博开发者账号和密码
     */
    private static String weiboAppid = "";
    private static String weiboSecret = "";
    //private static String weiboRedirectUri = "";

    /**
     * 微信授权登陆
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginByWeChat")
    @ResponseBody
    public com.belerweb.social.weixin.bean.User loginByWeChat(HttpServletRequest request) {
        String code = request.getParameter("code");
        Weixin weixin = new Weixin(weixinAppid, weixinSecret);
        //根据code获取access_token
        Result<AccessToken> accessTokenResult = weixin.getOAuth2().accessToken(code);
        AccessToken accessToken = accessTokenResult.getResult();
        //刷新 access_token
        //Result<AccessToken> newAccessTokenResult = weixin.getOAuth2().refreshAccessToken(accessToken.getRefreshToken());
        //根据access_token获取用户信息
        Result<com.belerweb.social.weixin.bean.User> userResult = weixin.getUser().snsapiUserInfo(accessToken.getToken(), accessToken.getOpenId());
        com.belerweb.social.weixin.bean.User user = userResult.getResult();
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
    public com.belerweb.social.qq.connect.bean.User loginByQQ(HttpServletRequest request) {
        String code = request.getParameter("code");
        QQConnect qqConnect = new QQConnect(qqAppid, qqSecret);
        //根据code获取access_token
        Result<com.belerweb.social.qq.connect.bean.AccessToken> tokenResult = qqConnect.getOAuth2().accessToken(code, true);
        //根据access_token获取openid
        String openId = qqConnect.getOAuth2().openId(tokenResult.getResult().getToken(), true).getResult().getOpenId();
        //根据access_token和openid获取用户信息
        Result<com.belerweb.social.qq.connect.bean.User> userResult = qqConnect.getUser().getUserInfo(tokenResult.getResult().getToken(), openId);
        com.belerweb.social.qq.connect.bean.User user = userResult.getResult();
        System.out.println(JSON.toJSONString(user));
        return user;
    }

    /**
     * 微博授权登陆
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginByWeiBo")
    @ResponseBody
    public Result<com.belerweb.social.weibo.bean.User> loginByWeiBo(HttpServletRequest request) {
        String code = request.getParameter("code");
        Weibo weibo = new Weibo(weiboAppid, weiboSecret);
        //根据code获取access_token
        Result<com.belerweb.social.weibo.bean.AccessToken> tokenResult = weibo.getOAuth2().accessToken(code);
        com.belerweb.social.weibo.bean.AccessToken at = tokenResult.getResult();
        //根据access_token和uid获取用户信息
        Result<com.belerweb.social.weibo.bean.User> user = weibo.getUser().show(weiboAppid, at.getToken(), at.getUid(), null);
        System.out.println(JSON.toJSONString(user));
        return user;
    }
}
