package com.dream.xukuan.xk2stu6;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.NotificationCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author XK
 * @date 2018/3/21.
 */
public class SecondIntentService extends IntentService {

    NotificationManager manager;
    NotificationCompat.Builder builder;

    public SecondIntentService() {
        super("haha");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        initNoti();
        String url = intent.getStringExtra("url");
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();
            File file = new File(Environment.getDataDirectory(),"load.mp4");
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buff = new byte[10];
            int progress = 0;
            int sum = 0;
            int total = connection.getContentLength();
            while (true){
                int ret = inputStream.read(buff,0,buff.length);
                if(ret ==-1){
                    break;
                }

                //发出通知：提示进度
                sum += ret;
                progress = (int) (sum*100.0/total);
                if(progress%10==0){
                    builder.setProgress(100,progress,false);
                    builder.setContentText(progress+"%");
                    manager.notify(100,builder.build());
                }
                fos.write(buff,0,ret);
                //发出通知：提示查看
                Intent playIntent = new Intent(Intent.ACTION_VIEW);
                playIntent.setDataAndType(Uri.fromFile(file),"video/*");
                playIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,1,playIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentText("点击查看")
                        .setContentTitle("下载完成")
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                manager.notify(100,builder.build());
            }
        } catch (IOException e) {

        }
    }

    private void initNoti() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        builder.setTicker("开始下载！").setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("下载中。。。。");
    }
}