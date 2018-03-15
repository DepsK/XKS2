package com.dream.xukuan.xk2stu5;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView = (ImageView) findViewById(R.id.second_image_view);
        int num = getIntent().getIntExtra("num", -1);
        Log.i("print", "onCreate: ..."+num);
        String path = getIntent().getStringExtra("path");
        if(!TextUtils.isEmpty(path)){
            imageView.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }
}
