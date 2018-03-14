package com.dream.xukuan.xk2stu3;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        String str = (String) msg.obj;
                        Log.d(TAG,"handleMessage: ..."+str);
                    }
                };
                Looper.loop();
            }
        }).start();

    }

    public void click(View view){
        //主线程发出消息给子线程
        Message msg = new Message();
        msg.obj = "我是主线程发出的消息！";
        handler.sendMessage(msg);
    }
}
