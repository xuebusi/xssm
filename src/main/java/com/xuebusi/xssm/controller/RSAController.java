package com.xuebusi.xssm.controller;

import com.xuebusi.xssm.common.security.RSAEncryption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * RSA测试类
 *
 * @Author: syj
 * @CreateDate: 2018/7/19 22:00
 */
@Controller
@RequestMapping("rsa")
@ResponseBody
public class RSAController {

    /**
     * 获取公钥
     *
     * @return
     */
    @RequestMapping("getPublicKey")
    public String getPublicKey() {
        return RSAEncryption.getPublicKeyString();
    }

    /**
     * 测试公钥加密和私钥解密
     *
     * @param password
     * @return
     */
    @RequestMapping("decryptByPriKey")
    public String decryptByPriKey(@RequestParam("password") String password) {
        System.out.println("=============加密前:" + password);
        try {
            // 使用公钥加密数据
            String encryData = RSAEncryption.encryptByPubKey(password);
            System.out.println("=============加密后:" + encryData);
            // 使用私钥解密数据
            String decryData = RSAEncryption.decryptByPriKey(encryData);
            System.out.println("=============解密后:" + decryData);
            return decryData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "解密失败";
    }
}
