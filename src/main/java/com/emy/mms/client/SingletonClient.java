package com.emy.mms.client;


public class SingletonClient {
	private static MMSClient client=null;
	private SingletonClient(){
	}

	public synchronized static MMSClient getClient(String userName, String password){
		if(client==null){
			try {
				client=new MMSClient(userName, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
}
