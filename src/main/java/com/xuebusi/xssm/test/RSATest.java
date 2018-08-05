package com.xuebusi.xssm.test;

import com.xuebusi.xssm.common.security.RSAEncryption;

/**
 * @Author: syj
 * @CreateDate: 2018/7/20 9:20
 */
public class RSATest {
    public static void main(String[] args) {
        rsaTest();
    }

    /**
     * 测试RSA加密解密
     */
    private static void rsaTest() {
        // 测试数据
        String password = "123456";
        System.out.println("=========加密前:" + password);
        try {
            // 使用公钥加密数据
            String encryData = RSAEncryption.encryptByPubKey(password);
            System.out.println("=========加密后:" + encryData);
            // 使用私钥解密数据
            String decryData = RSAEncryption.decryptByPriKey(encryData);
            System.out.println("=========解密后:" + decryData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("=========RSA测试异常:" + e.toString());
        }
    }
}
