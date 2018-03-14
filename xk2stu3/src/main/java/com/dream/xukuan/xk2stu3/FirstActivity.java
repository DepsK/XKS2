package com.dream.xukuan.xk2stu3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirstActivity extends AppCompatActivity {


    ImageView imageView;
    String imgUrl= "http://img5q.duitang.com/uploads/item/201506/22/20150622142642_KjTtJ.thumb.700_0.jpeg";
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                imageView.setImageBitmap((Bitmap) msg.obj);
            }else if(msg.what ==2){
                Toast.makeText(FirstActivity.this, "下载失败了！", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        imageView = (ImageView) findViewById(R.id.first_img);
    }

    public void load(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(imgUrl).openConnection();
                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    if(bitmap != null){
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj =bitmap;
                        handler.sendMessage(msg);
                    }else {
                        handler.sendEmptyMessage(2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(2);
                }
            }
        }).start();

    }
}
