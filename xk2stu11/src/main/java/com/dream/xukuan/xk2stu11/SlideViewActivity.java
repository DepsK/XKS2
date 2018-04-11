package com.dream.xukuan.xk2stu11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dream.xukuan.xk2stu11.views.SlideSwitch;

public class SlideViewActivity extends AppCompatActivity {

    SlideSwitch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_view);
        aSwitch = (SlideSwitch) findViewById(R.id.slideview);
        aSwitch.setOnSlideStateChangedListener(new SlideSwitch.OnSlideStateChangedListener() {
            @Override
            public void onSlideStateChanged(boolean state) {
                if(state){
                    Toast.makeText(SlideViewActivity.this,"开关是打开的",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SlideViewActivity.this,"开关是关闭的",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
