package com.example.android.camera2basic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private Intent intent=null;
    public MyService() {
        Log.d(TAG, "MyService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {

        intent=new Intent("com.seven.broadcast");
        super.onCreate();
        proess();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    public void proess()
    {
        new Thread(new Runnable() {
            @Override

            public void run() {
                NetCmdServer Udp=new NetCmdServer();
                Udp.initSocket();
                intent.putExtra("loader",1);
                while (true) {
                    String str=null;
                    str=Udp.recSocket();
                    sendBroadcast(intent);
                    Log.d(TAG, "run:"+str);
                }
            }
        }).start();


    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

}
