package com.pbi.ppc;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.pbi.ppc.domain.VersionBean;
import com.pbi.ppc.utils.CommonUtils;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity
{
    private static final String UPDATE_FILE_PATH = "http://10.10.90.218:8080/examples/version_update.xml";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        new Thread(new Runnable()
//        {
//            
//            @Override
//            public void run()
//            {
//                URL url;
//                try
//                {
//                    url = new URL(UPDATE_FILE_PATH);
//                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                    conn.setConnectTimeout(5000);
//                    InputStream is = conn.getInputStream();
//                    VersionBean bean = CommonUtils.parserXml(is);
//                    Log.i(TAG, "--->>>version name: " +bean.getVersionName());
//                    Log.i(TAG, "--->>>version path: " + bean.getApkPath());
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
