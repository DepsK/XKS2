package com.dream.xukuan.xk2stu5;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    ThirdReceiver2 receiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        receiver2 = new ThirdReceiver2();
        IntentFilter filter = new IntentFilter();
        filter.addAction("my_broad");
        filter.setPriority(200);
        registerReceiver(receiver2,filter);
    }
    //sendBroadcast发出普通广播：接收的顺序和优先级没有关系，几乎是同时收到
    public void click1(View view){
        //发出广播:参数是一个意图，意图需要指明广播的接收者
        Intent intent = new Intent("my_broad");
        intent.putExtra("msg","我是无序广播");
        sendBroadcast(intent);
    }
    //sendOrderedBroadcast发出有序广播:接受者按照优先级从高到低的顺序收到广播
    public void click2(View view){
        Intent intent = new Intent("my_broad");
        intent.putExtra("msg","我是有序广播");
        sendOrderedBroadcast(intent,null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver2);
    }
}
