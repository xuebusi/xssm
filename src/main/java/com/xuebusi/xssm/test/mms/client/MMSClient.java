package com.xuebusi.xssm.test.mms.client;

import com.xuebusi.xssm.test.mms.soap.MMS;
import com.xuebusi.xssm.test.mms.soap.MMSLocator;
import com.xuebusi.xssm.test.mms.soap.MMSSoap;

import javax.xml.rpc.ServiceException;

public class MMSClient {
	MMSSoap binding = null;
	private String userName;
	private String password;
	public MMSClient(String userName, String password){
		this.userName = userName;
		this.password = password;
		init();
	}
	public void init(){
//			UserInterface service = new UserInterfaceLocator();
//			binding = (UserInterfaceSoapStub)service.getUserInterfaceSoap();
		
		try {
			MMS service = new MMSLocator();
			binding = service.getMMSSoap();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	public String sendMMS(String title,String userNumbers,byte[] MMSContent,int sendType)throws Exception{
		String str = "";
		str = binding.sendMMS(userName, password, title, userNumbers, MMSContent, sendType);
		return str;
	}
	public String SendScheduledMMS(String title,String userNumbers,byte[] MMSContent,String sendtime,int sendType)throws Exception{
		String str = "";
		str = binding.sendMMSEx(userName, password, title, userNumbers, MMSContent, sendtime, sendType);
		return str;
	}
	public long getMMSCount(int sendType)throws Exception{
		long str = -1;
		str = binding.getMMSCount(userName, password, sendType);
		return str;
	}
	public String getStatusReport()throws Exception{
		String str = "";
		str = binding.getStatusReport(userName, password);
		return str;
	}
	public String getMoContent()throws Exception{
		String str = "";
		str = binding.getMoContent(userName, password);
		return str;
	}
}
