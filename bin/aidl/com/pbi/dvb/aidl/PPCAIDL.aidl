package com.pbi.dvb.aidl;

interface PPCAIDL 
{
	void checkUpdate();
	void switchService(String serviceName,int serviceId,int tsId,int netId, String mac);
	void stopPPC();
	
}
