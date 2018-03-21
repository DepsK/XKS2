package com.dream.xukuan.xk2stu6;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.jar.Manifest;

public class ThirdActivity extends AppCompatActivity {

    String urlString = "http://dldir1.qq.com/weixin/android/weixin6331android940.apk";
    private MyLoadCompleteReceiver mReceiver;
    private long mId;
    private DownloadManager mDownloadManager;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        button = (Button) findViewById(R.id.load);
        mReceiver = new MyLoadCompleteReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(mReceiver, intentFilter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAllGranted = checkPermissionAllGranted(
                        new String[] {
                                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }
                );
                if (isAllGranted) {
                    setDownloadManager();
                    return;
                }
                ActivityCompat.requestPermissions(ThirdActivity.this,
                        new String[] {
                                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        1);
            }
        });

    }
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }


    public void clickCancel(View view){
        mDownloadManager.remove(mId);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1){
            boolean isAllGranted = true;
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
                // 如果所有的权限都授予了, 则执行备份代码
                setDownloadManager();

            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮

            }
        }
    }

    private void setDownloadManager() {
        //开始下载
        //获得下载管理器
        mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(urlString);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("微信开始下载：")
                .setDescription("下载中，请稍候。。。")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"mydown");
        //下载管理器发出下载请求:放入请求队列,返回这个下载任务的id
        mId = mDownloadManager.enqueue(request);
    }

    private class MyLoadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(mId);
            Cursor cursor = mDownloadManager.query(query);
            boolean flag = cursor.moveToFirst();
            if(flag){
                String fileUri  = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                String fileName = Uri.parse(fileUri).getPath();
                noti(fileName);
            }
        }
    }

    private void noti(String fileName) {
        //发出通知，提示安装
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(fileName)),"application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("点击安装")
                .setContentTitle("下载完成")
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setTicker("下载完成！")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManager.notify(100,builder.build());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
