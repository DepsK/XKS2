package com.dream.xukuan.xk2stu5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirstActivity extends AppCompatActivity {

    private Intent mIntent;
    private int num;
    private String urlString = "http://c.hiphotos.baidu.com/image/pic/item/cc11728b4710b912d2396844c0fdfc039345228b.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

    }

    public void clickNormal(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(FirstActivity.this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("下载完成");
        builder.setContentText("点击跳转");
        builder.setTicker("下载完成！");
        mIntent = new Intent(FirstActivity.this, SecondActivity.class);
        mIntent.putExtra("num", num);
        PendingIntent pendingIntent = PendingIntent.getActivity(FirstActivity.this, 1, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        //设置默认效果
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);

        Notification notification = builder.build();
        //发出通知
        manager.notify(100, notification);
        num++;
    }

    public void clickBig(View view) {
        //1.获得通知管理器
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //2.创建小图通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(FirstActivity.this);
        builder.setTicker("有新通知！")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.epx)
                .setContentText("下午三点放学！")
                .setContentTitle("通知")
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        //3.转换成大图
        NotificationCompat.BigPictureStyle bigBuilder = new android.support.v4.app.NotificationCompat.BigPictureStyle(builder);
        bigBuilder.setBigContentTitle("这是大图通知标题")
                .setSummaryText("大图通知的摘要文本")
                // 设置大图标
                .bigLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.hmv))
                //设置大图片
                .bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.big));
        Notification notification = bigBuilder.build();
        manager.notify(200,notification);
    }

    public void clickProgress(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(FirstActivity.this);
                builder.setTicker("开始下载")
                        .setContentTitle("开始下载")
                        .setContentText("准备中。。。。")
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.epx);
                Notification notification = builder.build();
                manager.notify(300,notification);
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
                    InputStream inputStream = connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buff = new byte[10];
                    int progress = 0;
                    int sum = 0;
                    int total = connection.getContentLength();
                    while (true){
                        int ret = inputStream.read(buff,0,buff.length);
                        if(ret==-1){
                            break;
                        }
                        sum += ret;
                        progress = (int) (sum*100.0/total);
                        if(progress%10 ==0){
                            builder.setProgress(100,progress,false);
                            builder.setContentText(progress+"%");
                            manager.notify(300,builder.build());
                        }
                        bos.write(buff,0,ret);
                    }
                    byte[] data = bos.toByteArray();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                    File dir = Environment.getExternalStorageDirectory();
                    File file = new File(dir,"save_girl.jpg");
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //注意：如果图片太大，发不成功,可以考虑发送路径
                    intent.putExtra("path", file.getAbsolutePath());
                    PendingIntent pendingIntent = PendingIntent.getActivity(FirstActivity.this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    //下载完成，发出大图通知
                    NotificationCompat.BigPictureStyle bigBuilder = new android.support.v4.app.NotificationCompat.BigPictureStyle(builder);
                    bigBuilder.setBigContentTitle("下载完成")
                            .setSummaryText("下载完成了！点击查看")
                            .bigPicture(bitmap);
                    manager.notify(300,bigBuilder.build());
                } catch (IOException e) {

                }

            }
        }).start();

    }

    public void clickCustom(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(FirstActivity.this);
        //加载自定义的布局
        RemoteViews customView = new RemoteViews(getPackageName(),R.layout.activity_first_custom);
        customView.setProgressBar(R.id.first_custom_progerssbar,100,50,false);
        customView.setTextViewText(R.id.first_custom_title,"放学了！");
        customView.setTextViewText(R.id.first_custom_content_tv, "去约会啦！");
        customView.setTextColor(R.id.first_custom_content_tv, Color.BLUE);
        //设置图片
        customView.setBitmap(R.id.first_custom_iv,"setImageBitmap",BitmapFactory.decodeResource(getResources(),R.mipmap.hmv));
        //设置自定义的布局视图
        builder.setCustomContentView(customView)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.epx)
                .setContentTitle("新消息");
        manager.notify(400,builder.build());
    }
}
