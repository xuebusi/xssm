package com.emy.mms.soap;

public class MMSSoapProxy implements MMSSoap {
  private String _endpoint = null;
  private MMSSoap mMSSoap = null;
  
  public MMSSoapProxy() {
    _initMMSSoapProxy();
  }
  
  public MMSSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initMMSSoapProxy();
  }
  
  private void _initMMSSoapProxy() {
    try {
      mMSSoap = (new MMSLocator()).getMMSSoap();
      if (mMSSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mMSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mMSSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mMSSoap != null)
      ((javax.xml.rpc.Stub)mMSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public MMSSoap getMMSSoap() {
    if (mMSSoap == null)
      _initMMSSoapProxy();
    return mMSSoap;
  }
  
  @Override
  public java.lang.String sendMMS(java.lang.String userName, java.lang.String password, java.lang.String title, java.lang.String userNumbers, byte[] MMSContent, int sendType) throws java.rmi.RemoteException{
    if (mMSSoap == null)
      _initMMSSoapProxy();
    return mMSSoap.sendMMS(userName, password, title, userNumbers, MMSContent, sendType);
  }
  
  @Override
  public java.lang.String sendMMSEx(java.lang.String userName, java.lang.String password, java.lang.String title, java.lang.String userNumbers, byte[] MMSContent, java.lang.String sendtime, int sendType) throws java.rmi.RemoteException{
    if (mMSSoap == null)
      _initMMSSoapProxy();
    return mMSSoap.sendMMSEx(userName, password, title, userNumbers, MMSContent, sendtime, sendType);
  }
  
  @Override
  public long getMMSCount(java.lang.String userName, java.lang.String password, int sendType) throws java.rmi.RemoteException{
    if (mMSSoap == null)
      _initMMSSoapProxy();
    return mMSSoap.getMMSCount(userName, password, sendType);
  }
  
  @Override
  public java.lang.String getStatusReport(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (mMSSoap == null)
      _initMMSSoapProxy();
    return mMSSoap.getStatusReport(userName, password);
  }
  
  @Override
  public java.lang.String getMoContent(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (mMSSoap == null)
      _initMMSSoapProxy();
    return mMSSoap.getMoContent(userName, password);
  }
  
  
}