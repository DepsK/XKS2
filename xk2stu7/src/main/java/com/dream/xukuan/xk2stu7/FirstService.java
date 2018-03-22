package com.dream.xukuan.xk2stu7;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author XK
 * @date 2018/3/22.
 */
public class FirstService extends Service {


    private static final String TAG = "FirstService";

    public String getMsg() {
        return "今天自助餐打折，200元/位，吃海鲜大餐！";
    }

    public void setData(String msg) {
        Log.d(TAG, "setData: ...."+msg);
    }

    public class MyBinder extends Binder{
        public FirstService getServiceInstance(){
            return FirstService.this;
        }
    }

    MyBinder mBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ...."+Integer.toHexString(mBinder.hashCode()));
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ....");
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind:  .......");
        return onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: .....");
    }


}