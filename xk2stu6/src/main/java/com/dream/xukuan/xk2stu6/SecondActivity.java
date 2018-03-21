package com.dream.xukuan.xk2stu6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    Context context;
    String urlString = "http://img1.kwcdn.kuwo.cn/star/KuwoArtPic/2013/188/1397039123208_w.jpg";
    BroadcastReceiver receiver;
    private LocalBroadcastManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        context= SecondActivity.this;
        imageView = (ImageView) findViewById(R.id.second_image_view);
        receiver = new MyReceiver();
        manager = LocalBroadcastManager.getInstance(context);
        manager.registerReceiver(receiver,new IntentFilter("my_broad"));
    }

    public void load(View view){
        Intent intent = new Intent(context,SecondLoadService.class);
        intent.putExtra("url",urlString);
        startService(intent);
    }

    public void loadVideo(View view){
        String urlString = "http://10.20.163.65:8080/webserver/media/media.mp4";
        Intent intent = new Intent(this,SecondIntentService.class);
        intent.putExtra("url",urlString);
        startService(intent);
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String path = intent.getStringExtra("path");
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver);
    }
}
