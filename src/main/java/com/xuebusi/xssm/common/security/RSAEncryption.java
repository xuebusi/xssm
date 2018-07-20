package com.xuebusi.xssm.common.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA数据签名及数据加密
 */
public class RSAEncryption {

	/**
	 * 公钥字符串
	 */
	private static final String PUBLIC_KEY_STRING = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDSUmOXyQmYYSnZacp0btvAZCOvCNPtzixAp7eJmzmAG4mgy/VgrY/s1BDLh9qTNHIRWXepUtwMrf1kYul/A45qE/2oxIbeeq4238YDWQ7ModOVXR9ytEHsT0jpCFvoYfYXYZnnoWRrLIBylQeXzqxbLDxxBxGCs4AjoRKh5S7nNQIDAQAB";

	/**
	 * 私钥字符串
	 */
	private static final String PRIVATE_KEY_STRING = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANJSY5fJCZhhKdlpynRu28BkI68I0+3OLECnt4mbOYAbiaDL9WCtj+zUEMuH2pM0chFZd6lS3Ayt/WRi6X8DjmoT/ajEht56rjbfxgNZDsyh05VdH3K0QexPSOkIW+hh9hdhmeehZGssgHKVB5fOrFssPHEHEYKzgCOhEqHlLuc1AgMBAAECgYEAqTB9zWx7u4juEWd45ZEIVgw4aGXBllt0Xc6NZrTn3JZKcH+iNNNqJCm0GQaAXkqiODKwgBWXzttoK4kmLHa/6D7rXouWN8PGYXj7DHUNzyOe3IgmzYanowp/A8gu99mJQJzyhZGQ+Uo9dZXAgUDin6HAVLaxF3yWD8/yTKWN4UECQQD8Q72r7qdAfzdLMMSQl50VxRmbdhQYbo3D9FmwUw6W1gy2jhJyPXMi0JZKdKaqhxMZIT3zy4jYqw8/0zF2xc5/AkEA1W+n24Ef3ucbPgyiOu+XGwW0DNpJ9F8D3ZkEKPBgjOMojM7oqlehRwgy52hU+HaL4Toq9ghL1SwxBQPxSWCYSwJAGQUO9tKAvCDh9w8rL7wZ1GLsG0Mm0xWD8f92NcrHE6a/NAv7QGFf3gAaJ+BR92/WMRPe9SMmu3ab2JS1vzX3OQJAdN70/T8RYo8N3cYxNzBmf4d59ee5wzQb+8WD/57QX5UraR8LS+s8Bpc4uHnqvTq8kZG2YI5eZ9YQ6XwlLVbVTQJAKOSXNT+XEPWaol1YdWZDvr2m/ChbX2uwz52s8577Tey96O4Z6S/YA7V6Fr7hZEzkNF+K0LNUd79EOB6m2eQq5w==";

	/**
	 * 公钥
	 */
    private static byte[] pub_key = RSAEncryption.getPublicKey();

	/**
	 * 私钥
	 */
	private static byte[] pri_key = RSAEncryption.getPrivateKey();

    /**
     * 数字签名，密钥算法
     */
	private static final String RSA_KEY_ALGORITHM = "RSA";

    /**
     * 数字签名签名/验证算法
     */
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * RSA密钥长度，RSA算法的默认密钥长度是1024密钥长度必须是64的倍数，在512到65536位之间
     */
	private static final int KEY_SIZE = 1024;

    /**
     * 数字签名生成密钥 第一步生成密钥对,如果已经生成过,本过程就可以跳过
     */
    private static Map<String, String> initKey() throws Exception {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
		SecureRandom secrand = new SecureRandom();
        /**
         * 初始化随机产生器
         */
		secrand.setSeed("initSeed".getBytes());
        /**
         * 初始化密钥生成器
         */
		keygen.initialize(KEY_SIZE, secrand);
		KeyPair keys = keygen.genKeyPair();

		byte[] pub_key = keys.getPublic().getEncoded();
		String publicKeyString = Base64.encodeBase64String(pub_key);
		System.out.println("公钥：" + publicKeyString);

        byte[] pri_key = keys.getPrivate().getEncoded();
		String privateKeyString = Base64.encodeBase64String(pri_key);
		System.out.println("私钥：" + privateKeyString);

		Map<String, String> keyPairMap = new HashMap<>();
		keyPairMap.put("publicKeyString", publicKeyString);
		keyPairMap.put("privateKeyString", privateKeyString);

		return keyPairMap;
	}

	public static byte[] getPublicKey() {
		return decodeBase64(PUBLIC_KEY_STRING);
	}

	public static byte[] getPrivateKey() {
		return decodeBase64(PRIVATE_KEY_STRING);
	}

	public static String getPublicKeyString() {
		return PUBLIC_KEY_STRING;
	}

	public static String getPrivateString() {
		return PRIVATE_KEY_STRING;
	}

    /**
     * 密钥转成字符串
     * @param key
     * @return
     */
	public static String encodeBase64String(byte[] key) {
        return Base64.encodeBase64String(key);
    }

    /**
     * 将密钥从字符串转成byte[]
     * @param key
     * @return
     */
    public static byte[] decodeBase64(String key) {
        return Base64.decodeBase64(key);
    }

	/**
	 * RSA签名
	 * 
	 * @return byte[] 数字签名
	 * */
	public static String sign(byte[] data) throws Exception {
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		// 生成私钥
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 实例化Signature
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		// 初始化Signature
		signature.initSign(priKey);
		// 更新
		signature.update(data);

		return Base64.encodeBase64String(signature.sign());
	}

	/**
	 * RSA校验数字签名
	 * 
	 * @param data
	 *            待校验数据
	 * @param sign
	 *            数字签名
	 * @return boolean 校验成功返回true，失败返回false
	 * */
	public boolean verify(byte[] data, byte[] sign) throws Exception {
		// 转换公钥材料
		// 实例化密钥工厂
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		// 初始化公钥
		// 密钥材料转换
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pub_key);
		// 产生公钥
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		// 实例化Signature
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		// 初始化Signature
		signature.initVerify(pubKey);
		// 更新
		signature.update(data);
		// 验证
		return signature.verify(sign);
	}

	/**
	 * 用公钥加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPubKey(byte[] data) throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pub_key);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 用公钥加密
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPubKey(String data) throws Exception {
		byte[] enSign = encryptByPubKey(data.getBytes());
		return Base64.encodeBase64String(enSign);
	}

	/**
	 * 用私钥加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPriKey(byte[] data) throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 用私钥加密
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPriKey(String data) throws Exception {
		byte[] enSign = encryptByPriKey(data.getBytes());
		return Base64.encodeBase64String(enSign);
	}

	/**
	 * 用公钥解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPubKey(byte[] data) throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pub_key);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 用公钥解密
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPubKey(String data) throws Exception {
		byte[] design = decryptByPubKey(Base64.decodeBase64(data));
		return new String(design);
	}

	/**
	 * 用私钥解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPriKey(byte[] data) throws Exception {
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 用私钥解密
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPriKey(String data) throws Exception {
		byte[] design = decryptByPriKey(Base64.decodeBase64(data));
		return new String(design);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		RSAEncryption das = new RSAEncryption();

		String datastr = "加密前数据";
		System.out.println("加密数据:" + datastr);
		// 公匙加密
		String pubKeyStr = RSAEncryption.encryptByPubKey(datastr);
		System.out.println("公匙加密:" + pubKeyStr);
		String priKeyStr = RSAEncryption.decryptByPriKey(pubKeyStr);
		// 私匙解密
		System.out.println("私匙解密:" + priKeyStr);

		// 数据签名
		String str1 = "before";
		String str2 = "after";
		String sign = RSAEncryption.sign(str1.getBytes());
		System.out.println("数据签名:" + sign);
		boolean vflag1 = das.verify(str1.getBytes(), Base64.decodeBase64(sign));
		System.out.println("数据验证结果1:" + vflag1);
		boolean vflag2 = das.verify(str2.getBytes(), Base64.decodeBase64(sign));
		System.out.println("数据验证结果2:" + vflag2);

	}

/*	*//**
	 * 使用公钥加密
	 * syj
	 * @param data
	 *//*
	public static String encryptByPubKey2(String data) {
		String base64String = null;
		try {
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(PUBLIC_KEY_STRING));
			KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
			PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] bytes = cipher.doFinal(data.getBytes());
			base64String = Base64.encodeBase64String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64String;
	}

	*//**
	 * 使用私钥解密
	 * syj
	 * @param data
	 * @return
	 *//*
	public static String decryptByPriKey2(String data) {
		String decryByPriKeyData = null;
		try {
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(PRIVATE_KEY_STRING));
			KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// 对数据解密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] bytes = cipher.doFinal(Base64.decodeBase64(data));
			decryByPriKeyData = new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryByPriKeyData;
	}*/
}