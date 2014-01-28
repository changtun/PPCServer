/* * File Name:  PPCService.java * Copyright:  Beijing Jaeger Communication Electronic Technology Co., Ltd. Copyright YYYY-YYYY,  All rights reserved * Descriptions:  <Descriptions> * Changed By:   * Changed Time:  2014-1-16 * Changed Content:  <Changed Content> */package com.pbi.ppc.service;import java.io.File;import java.io.InputStream;import java.net.HttpURLConnection;import java.net.URL;import android.app.Service;import android.content.Intent;import android.content.pm.PackageInfo;import android.content.pm.PackageManager;import android.content.pm.PackageManager.NameNotFoundException;import android.os.Handler;import android.os.IBinder;import android.os.Message;import android.os.RemoteException;import android.util.Log;import com.pbi.dvb.aidl.PPCAIDL;import com.pbi.ppc.domain.MountInfoBean;import com.pbi.ppc.domain.VersionBean;import com.pbi.ppc.utils.CommonUtils;import com.pbi.ppc.utils.StorageUtils;/** * <Functional overview> <Functional Details> *  * @author * @version [Version Number, 2014-1-16] * @see [Relevant Class/Method] * @since [Product/Module Version] */public class PPCService extends Service{        private static final String TAG = "PPCService";        private static final String UPDATE_FILE_PATH = "http://10.10.90.218:8080/ppc/version_update.xml";        private static final int CONNECT_SERVER_FAILED = 404;        private static final int START_DOWNLOAD_SERVICE = 201;    private String apkPath;    private Handler mHandler = new Handler()    {                @Override        public void handleMessage(Message msg)        {            switch (msg.what)            {                case CONNECT_SERVER_FAILED:                    Log.i(TAG, "--->>>connect the server failed.");                    break;                case START_DOWNLOAD_SERVICE:                    Log.i(TAG, "--->>>start download service.");                    Intent intent = new Intent("com.pbi.ppc.service.PPCDownloadService");                    intent.putExtra("apk_path", apkPath);                    startService(intent);                    break;            }        }            };        @Override    public void onCreate()    {        super.onCreate();        Log.i(TAG, "--->>> ppc service create!");    }        @Override    public int onStartCommand(Intent intent, int flags, int startId)    {        Log.i(TAG, "--->>> ppc service start!");        return super.onStartCommand(intent, flags, startId);    }        /**     * Override Method     *      * @param intent     * @return     */    @Override    public IBinder onBind(Intent intent)    {        return new PPCBinder();    }        public class PPCBinder extends PPCAIDL.Stub    {        @Override        public void checkUpdate()            throws RemoteException        {            // start a thread to check the version.            new CheckVersionTask(mHandler).run();        }                @Override        public void switchService(String serviceName, int serviceId, int tsId, int netId, String mac)            throws RemoteException        {            String startPPC =                "http://127.0.0.1:9906/?opt=channel_start&channel_name=" + serviceName + "&service_id=" + serviceId                    + "&transport_stream_id=" + tsId + "&network_id=" + netId + "&mac_id=" + mac;            Log.i(TAG, "--->>>ppc request  " + startPPC);            CommonUtils.setPPCRequest(startPPC);        }                @Override        public void stopPPC()            throws RemoteException        {            Log.i(TAG, "--->>> stop ppc request!");            CommonUtils.setPPCRequest("http://127.0.0.1:9906/?opt=channel_stop");        }            }        /**     *      * <get version from manifest.xml>     *      * @return     * @throws Exception     * @see [Class, Class#Method, Class#Member]     */    private String getVersion()    {        PackageManager packageManager = this.getPackageManager();        PackageInfo packageInfo = null;        try        {            packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);        }        catch (NameNotFoundException e)        {            e.printStackTrace();        }        return packageInfo.versionName;    }        class CheckVersionTask extends Thread    {        private Handler handler;                public CheckVersionTask(Handler handler)        {            this.handler = handler;        }                public void run()        {            VersionBean vBean = null;            try            {                URL url = new URL(UPDATE_FILE_PATH);                HttpURLConnection conn = (HttpURLConnection)url.openConnection();                conn.setConnectTimeout(5000);                if (conn.getResponseCode() != 200)                {                    handler.sendEmptyMessage(CONNECT_SERVER_FAILED);                    return;                }                InputStream is = conn.getInputStream();                String versionName = getVersion();                vBean = CommonUtils.parserXml(is);                apkPath = vBean.getApkPath();                                Log.i(TAG, "--->>> local version (remote version) " + versionName + "(" + vBean.getVersionName() + ")");                if (versionName.equals(vBean.getVersionName()))                {                    // the local version equals the remote version.                    Log.i(TAG, "--->>>It's already the lastest version. ");                }                else                {                    Log.i(TAG, "--->>>It need to update the lastest version. ");                    handler.sendEmptyMessage(START_DOWNLOAD_SERVICE);                }            }            catch (Exception e)            {                handler.sendEmptyMessage(CONNECT_SERVER_FAILED);                e.printStackTrace();            }        }    }    }