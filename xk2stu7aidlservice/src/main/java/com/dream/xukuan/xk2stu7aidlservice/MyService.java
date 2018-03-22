package com.dream.xukuan.xk2stu7aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author XK
 * @date 2018/3/22.
 */
public class MyService extends Service {


    private static final String TAG = "MyService";

    public class MyBinder extends IMyAidlInterface.Stub{

        @Override
        public void setData(String msg) throws RemoteException {
            Log.i(TAG, "setData: ....收到远端数据："+msg);
        }

        @Override
        public String getMsg() throws RemoteException {
            return "你好！我是远程服务";
        }

        @Override
        public void sendObject(Car car) throws RemoteException {
            Log.i(TAG, "sendObject: 收到对象："+car);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ...");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ....");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ....");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ....");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ....");
    }
}