package com.dream.xukuan.xk2stu6;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author XK
 * @date 2018/3/21.
 */
public class FourthService extends Service {

    private MediaPlayer mediaPlayer;
    private LocalBroadcastManager manager;
    private MyReceiver reciver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this,R.raw.gz);
        manager = LocalBroadcastManager.getInstance(this);
        //注册一个接收定位位置的广播接收者
        reciver = new MyReceiver();
        manager.registerReceiver(reciver,new IntentFilter("seek.broad"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else {
            mediaPlayer.start();
            //播放时获取进度
            getProgress();
        }
        return super.onStartCommand(intent,flags,startId);
    }

    private void getProgress() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //获得进度:正在播放才获取
                if(mediaPlayer.isPlaying()){
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    int duration = mediaPlayer.getDuration();
                    Intent intent = new Intent("progress.broad");
                    intent.putExtra("currentPosition",currentPosition);
                    intent.putExtra("duration",duration);
                    manager.sendBroadcast(intent);
                }else {
                    timer.cancel();
                }
            }
        },0,200);
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int seek = intent.getIntExtra("seek", 0);
            mediaPlayer.seekTo(seek);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    }
}