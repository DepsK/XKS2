package com.dream.xukuan.xk2stu8;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        TextView textView = (TextView) findViewById(R.id.tv);
        //版本大于19
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
            textView.setTextColor(Color.RED);
        else
            textView.setTextColor(Color.BLUE);
    }
}
