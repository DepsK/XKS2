package com.dream.xukuan.xk2stu8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    String urlString = "http://image.cnpp.cn/upload/images/20160608/1465375920_11648_2.jpg";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView = (ImageView) findViewById(R.id.second_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyImageLoader.with(SecondActivity.this).load(urlString).into(imageView);
            }
        });
    }
}
