package com.dream.xukuan.stu1hw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SlideActivity extends AppCompatActivity {

    Button button;
    ViewPager viewPager;
    int[] images = {R.mipmap.guidance1,R.mipmap.guidance2,R.mipmap.guidance3,R.mipmap.guidance4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        button = (Button) findViewById(R.id.stu1hw_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存不是第一次
                SharedPreferences sp = getSharedPreferences("isFirst",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("isFirst",false);
                editor.commit();
                //跳转到主页
                Intent intent = new Intent(SlideActivity.this,MainActivity.class);
                startActivity(intent);
                SlideActivity.this.finish();
            }
        });
        viewPager = (ViewPager) findViewById(R.id.stu1hw_viewpager);
        MyHwAdapter adapter = new MyHwAdapter(SlideActivity.this,images);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                if(position == images.length -1){
                    button.setVisibility(View.VISIBLE);
                }else {
                    button.setVisibility(View.GONE);
                }
            }
        });
    }
}
