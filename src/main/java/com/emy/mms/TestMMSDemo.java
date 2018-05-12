package com.emy.mms;

import com.emy.mms.client.MMSClient;
import com.emy.mms.client.SingletonClient;

import java.io.FileInputStream;

public class TestMMSDemo {

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
			int sendType = 1;
			byte[] content = readContent();
			String userName = "bf-test";
			String password = "c5f1213ce8";
			MMSClient mmsClient = SingletonClient.getClient(userName, password);
			result = mmsClient.sendMMS(mmsTitle, userNumbers, content, sendType);
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
	private static byte[] readContent() {
		FileInputStream fs;
		try {
			fs = new FileInputStream("Report.zip");
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
			String userName = "bf-test";
			String password = "c5f1213ce8";
			System.out.println();
			MMSClient mmsClient = SingletonClient.getClient(userName, password);
			mmsCount = mmsClient.getMMSCount(1);
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
			String userName = "bf-test";
			String password = "c5f1213ce8";
			MMSClient mmsClient = SingletonClient.getClient(userName, password);
			statusReport = mmsClient.getStatusReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusReport;
	}

}
