package com.dream.xukuan.xk2stu6;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author XK
 * @date 2018/3/16.
 */
public class FirstService extends Service {


    private LocalBroadcastManager localBroadcastManager;
    private String msg;
    private Timer mTimer;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

    }

    int count =0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        msg = null;
        if(intent!=null){
            msg = intent.getStringExtra("msg");
        }else {
            msg = "暂无数据";
        }
        if (mTimer == null) {//说明定时器还没有开始干活
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent countIntent = new Intent("my_count_broad");
                    countIntent.putExtra("count",count);
                    localBroadcastManager.sendBroadcast(countIntent);
                    count++;
                }
            }, 0, 1000);
        }else {//说明定时器正在工作
            mTimer.cancel();
            mTimer = null;
        }

        return Service.START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent countIntent = new Intent("my_count_broad");
        countIntent.putExtra("count",0);
        localBroadcastManager.sendBroadcast(countIntent);
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }
}