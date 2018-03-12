package com.dream.xukuan.stu1hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long lastTime = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (lastTime < 0) {
            Toast.makeText(MainActivity.this, "第一次点返回，再按一次退出", Toast.LENGTH_SHORT).show();
            lastTime = System.currentTimeMillis();
        } else if (currentTime - lastTime > 2000) {
            Toast.makeText(MainActivity.this, "两次点击的时间间隔太久，再按一次退出", Toast.LENGTH_SHORT).show();
            lastTime = System.currentTimeMillis();
        } else {
            MainActivity.this.finish();
        }
    }
}
