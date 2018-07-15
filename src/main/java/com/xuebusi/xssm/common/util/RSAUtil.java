package com.xuebusi.xssm.common.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSAUtil {

	public static final String PUBLIC_KEY = "RSAPublicKey";
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	
	/**
	 * 生成RSA的公钥和私钥
	 */
	public static Map<String, Object> initKey() throws Exception{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		//512-65536 & 64的倍数
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		
		return keyMap;
	}
	
	/**
	 * 获得公钥
	 */
	public static RSAPublicKey getPublicKey(Map<String, Object> keyMap){
		RSAPublicKey publicKey = (RSAPublicKey) keyMap.get(PUBLIC_KEY);
		return publicKey;
	}
	
	/**
	 * 获得私钥
	 */
	public static RSAPrivateKey getPrivateKey(Map<String, Object> keyMap){
		RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get(PRIVATE_KEY);
		return privateKey;
	}
	
	/**
	 * 公钥加密
	 */
	public static byte[] encrypt(byte[] data, RSAPublicKey publicKey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] cipherBytes = cipher.doFinal(data);
		return cipherBytes;
	}
	
	/**
	 * 私钥解密
	 */
	public static byte[] decrypt(byte[] data, RSAPrivateKey privateKey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] plainBytes = cipher.doFinal(data);
		return plainBytes;
	}

	public static void main(String[] args) {
		// 生成公钥和私钥
		try {
			Map<String, Object> map = RSAUtil.initKey();
			RSAPublicKey publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
			RSAPrivateKey privateKey = (RSAPrivateKey) map.get(RSAUtil.PRIVATE_KEY);

			String content = "xuebusi";
			// Base64加密
			String base64Encrypt = Base64Util.base64Encrypt(content.getBytes());
			// Base64解密
			String base64Decrypt = Base64Util.base64Decrypt(base64Encrypt);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}