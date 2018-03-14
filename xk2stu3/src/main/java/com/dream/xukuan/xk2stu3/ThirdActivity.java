package com.dream.xukuan.xk2stu3;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ThirdActivity extends AppCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 100){
                imageView.setImageResource(msg.arg1);
            }
        }
    };

    int[] imgIds = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6};

    ImageView imageView;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        imageView = (ImageView) findViewById(R.id.third_img);
    }

    int position = 0;
    public void play(View view){
        //隔500ms置换img
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    position %= imgIds.length;
                    Message msg = handler.obtainMessage();
                    msg.what = 100;
                    msg.arg1 = imgIds[position];
                    handler.sendMessage(msg);
                    SystemClock.sleep(500);
                    position++;
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
