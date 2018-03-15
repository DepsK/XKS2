package com.dream.xukuan.xk2stu5;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FourthActivity extends AppCompatActivity {

    FourthReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        receiver = new FourthReceiver();
        LocalBroadcastManager.getInstance(FourthActivity.this).registerReceiver(receiver,
                new IntentFilter("fourth_broad"));
    }

    public void click(View view){
        Intent intent = new Intent("fourth_broad");
        LocalBroadcastManager.getInstance(FourthActivity.this).sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(FourthActivity.this).unregisterReceiver(receiver);
    }
}
