package com.emy.mms;

import com.emy.mms.client.MMSClient;
import com.emy.mms.client.SingletonClient;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;

public class TestMMSDemo {

	private static final String USER_NAME = "bf-test";
	private static final String PASSWORD = "c5f1213ce8";
	private static final int SEND_TYPE = 1;

	public static void main(String[] args) {
		// 测试发送彩信
		System.out.println(sendMMS());
		System.out.println("====================");

		// 测试查询余额
		System.out.println(testGetBalance());
		System.out.println("====================");

		// 测试获得状态报告
		System.out.println(testGetReport());

	}
	
	/**
	 * 发送彩信
	 */
	public static String sendMMS() {
		String result = null;
		try {
			String mmsTitle = "测试亿美发送彩信接口";
			String userNumbers = "15801081566";
			byte[] binaryData = readContent("Report.zip");

			String base64String = Base64.encodeBase64String(binaryData);
			byte[] mmsContent = Base64.decodeBase64(base64String);

			MMSClient mmsClient = SingletonClient.getClient(USER_NAME, PASSWORD);
			result = mmsClient.sendMMS(mmsTitle, userNumbers, mmsContent, SEND_TYPE);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取彩信内容
	 * @return
	 */
	private static byte[] readContent(String filePath) {
		FileInputStream fs;
		try {
			fs = new FileInputStream(filePath);
			byte[] content=new byte[fs.available()];
			fs.read(content,0,content.length);
			fs.close();
			return content;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询余额
	 * 1是网关账户，2是点卡账户
	 */
	public static Long testGetBalance() {
		Long mmsCount = null;
		try {
			MMSClient mmsClient = SingletonClient.getClient(USER_NAME, PASSWORD);
			mmsCount = mmsClient.getMMSCount(SEND_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mmsCount;
	}
	
	/**
	 * 获得状态报告
	 */
	public static String testGetReport() {
		String statusReport = null;
		try {
			MMSClient mmsClient = SingletonClient.getClient(USER_NAME, PASSWORD);
			statusReport = mmsClient.getStatusReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusReport;
	}

}
