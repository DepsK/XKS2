package com.dream.xukuan.xk2stu6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    TextView textView;
    LocalBroadcastManager manager;
    private MyCountReceiver countReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        textView = (TextView) findViewById(R.id.first_text_view);
        manager = LocalBroadcastManager.getInstance(FirstActivity.this);
        countReceiver = new MyCountReceiver();
        IntentFilter filter = new IntentFilter("my_count_broad");
        manager.registerReceiver(countReceiver,filter);
    }

    public void start(View view){
        Intent intent = new Intent(FirstActivity.this,FirstService.class);
        intent.putExtra("msg","今天星期四了！就快到双休了！");
        startService(intent);
    }

    public void stop(View view){
        Intent intent = new Intent(FirstActivity.this,FirstService.class);
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止服务
        Intent intent = new Intent(this,FirstService.class);
        stopService(intent);
        //解除注册
        manager.unregisterReceiver(countReceiver);
    }

    private class MyCountReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra("count", -1);
            textView.setText("现在是："+count);
        }
    }
}
