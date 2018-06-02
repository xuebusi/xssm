package com.xuebusi.xssm.common.security;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class MainTest {

    public static void main(String[] args) throws Exception {
        //生成私钥公钥对
        KeyPair keyPair = RSAEncrypt.genKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 得到公钥字符串
        String publicKeyString = Base64.encode(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = Base64.encode(privateKey.getEncoded());


        System.out.println("--------------公钥加密私钥解密过程-------------------");
        String plainText = "zxcvbnm123";
        //公钥加密过程  
        byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(publicKeyString), plainText.getBytes());
        String cipher = Base64.encode(cipherData);
        //私钥解密过程  
        byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(privateKeyString), Base64.decode(cipher));
        String restr = new String(res);
        System.out.println("原文：" + plainText);
        System.out.println("加密：" + cipher);
        System.out.println("解密：" + restr);
        System.out.println();

        System.out.println("--------------私钥加密公钥解密过程-------------------");
        plainText = "1q2w3e4r";
        //私钥加密过程  
        cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(privateKeyString), plainText.getBytes());
        cipher = Base64.encode(cipherData);
        //公钥解密过程  
        res = RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(publicKeyString), Base64.decode(cipher));
        restr = new String(res);
        System.out.println("原文：" + plainText);
        System.out.println("加密：" + cipher);
        System.out.println("解密：" + restr);
        System.out.println();

        System.out.println("---------------私钥签名过程------------------");
        String content = "xuebusi123";
        String signstr = RSASignature.sign(content, privateKeyString);
        System.out.println("签名原串：" + content);
        System.out.println("签名串：" + signstr);
        System.out.println();

        System.out.println("---------------公钥校验签名------------------");
        System.out.println("签名原串：" + content);
        System.out.println("签名串：" + signstr);

        System.out.println("验签结果：" + RSASignature.doCheck(content, signstr, publicKeyString));
        System.out.println();

    }
} 