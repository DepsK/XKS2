package com.dream.xukuan.xk2stu6;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.bitmap;

/**
 * @author XK
 * @date 2018/3/21.
 */
public class SecondLoadService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String url = intent.getStringExtra("url");
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadImage(url);
            }
        }).start();
        return super.onStartCommand(intent,flags,startId);
    }

    private void loadImage(String url) {
        try {
            HttpURLConnection connection= (HttpURLConnection) new URL(url).openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            File dir = Environment.getDataDirectory();
            File file = new File(dir,"save.jpg");
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            Intent intent = new Intent("my_broad");
            intent.putExtra("path",file.getAbsolutePath());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            stopSelf();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}