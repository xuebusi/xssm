package com.xuebusi.xssm.controller;

import com.alibaba.fastjson.JSON;
import com.belerweb.social.bean.Result;
import com.belerweb.social.qq.connect.api.QQConnect;
import com.belerweb.social.weibo.api.Weibo;
import com.belerweb.social.weixin.api.Weixin;
import com.belerweb.social.weixin.bean.AccessToken;
import com.belerweb.social.weixin.bean.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    private static String weixinRedirectUri = "http://xuebusi.com/login/loginByWeChat";

    /**
     * QQ开发者账号和密码
     */
    private static String qqAppid = "";
    private static String qqSecret = "";
    private static String qqRedirectUri = "";

    /**
     * 微博开发者账号和密码
     */
    private static String weiboAppid = "";
    private static String weiboSecret = "";
    //private static String weiboRedirectUri = "";

    @RequestMapping(value = "/getUrl")
    @ResponseBody
    public String getUrl() {
        return getAuthCodeUrl(1);
    }

    private String getAuthCodeUrl(int type) {
        String url = null;
        if (type == 1) {
            Weixin weixin = new Weixin(weixinAppid, weixinSecret);
            try {
                url = weixin.getOAuth2().authorize(weixinAppid, URLEncoder.encode(weixinRedirectUri, "UTF-8"),
                        "code", Scope.SNSAPI_USERINFO, "xuebusi", true);
                System.out.println(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (type == 2) {
            //测试连接[来自网络] https://graph.qq.com/oauth2.0/show?which=Login&display=pc&response_type=code&client_id=101137684&redirect_uri=http%3A%2F%2Fmfxuan.free.800m.net%2Flogin.jsp&state=1&scope=get_user_info,get_info
            QQConnect qqConnect = new QQConnect(qqAppid, qqSecret);
            try {
                url = qqConnect.getOAuth2().authorize(URLEncoder.encode(qqRedirectUri, "UTF-8"));
                /*url = qqConnect.getOAuth2().authorize(qqAppid, qqRedirectUri, "code", "xuebusi",
                    com.belerweb.social.qq.connect.bean.Scope.ALL, null, null, true);*/
                System.out.println(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (type ==3) {

        } else {

        }
        return url;
    }


    /**
     * 从微信接口获取code
     * 该url只能使用微信中访问
     * @return
     */
    @RequestMapping(value = "/getCodeUrl")
    @ResponseBody
    public String getCodeUrl() {
        Weixin weixin = new Weixin(weixinAppid, weixinSecret);
        String url = weixin.getOAuth2().authorize(weixinRedirectUri);
        System.out.println(url);
        return url;
    }

    /**
     * 微信授权登陆
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginByWeChat")
    public String loginByWeChat(HttpServletRequest request, Model model) {
        String code = request.getParameter("code");
        System.out.println("\ncode=========\n" + code);
        Weixin weixin = new Weixin(weixinAppid, weixinSecret);
        //根据code获取access_token
        Result<AccessToken> accessTokenResult = weixin.getOAuth2().accessToken(code);
        AccessToken accessToken = accessTokenResult.getResult();
        System.out.println("\naccessToken=============\n" + JSON.toJSONString(accessToken));
        //刷新 access_token
        //Result<AccessToken> newAccessTokenResult = weixin.getOAuth2().refreshAccessToken(accessToken.getRefreshToken());
        //根据access_token获取用户信息
        Result<com.belerweb.social.weixin.bean.User> userResult = weixin.getUser().snsapiUserInfo(accessToken.getToken(), accessToken.getOpenId());
        com.belerweb.social.weixin.bean.User user = userResult.getResult();
        System.out.println("\nuser============\n" + JSON.toJSONString(user));
        model.addAttribute("user", user);
        System.out.println("\n-----------------------------------------------------------\n");
        return "success";
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
