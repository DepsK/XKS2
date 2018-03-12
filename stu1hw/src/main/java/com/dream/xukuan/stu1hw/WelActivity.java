package com.dream.xukuan.stu1hw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                Intent intent;
                if(isFirst()){
                    //跳入广告页
                    intent = new Intent(WelActivity.this,SlideActivity.class);
                }else {
                    //跳入主页
                    intent = new Intent(WelActivity.this,MainActivity.class);
                }
                startActivity(intent);
                WelActivity.this.finish();
            }
        }).start();
    }

    private boolean isFirst() {
        SharedPreferences sp = getSharedPreferences("isFirst",MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        return isFirst;
    }
}
