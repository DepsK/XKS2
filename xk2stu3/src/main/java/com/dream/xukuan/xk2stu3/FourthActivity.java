package com.dream.xukuan.xk2stu3;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FourthActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    List<View> views;
    List<Integer> colors;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        frameLayout = (FrameLayout) findViewById(R.id.activity_fourth);
        //得到所有的Button
        views = new ArrayList<>();
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            views.add(frameLayout.getChildAt(i));
        }
        colors = new ArrayList<>();
        //准备颜色值
        for (int i = 0; i < views.size(); i++) {
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            int color = Color.rgb(red, green, blue);
            colors.add(color);
        }

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    handler.sendEmptyMessage(1);
                    SystemClock.sleep(2000);
                }
            }
        }).start();*/

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < colors.size() ; i++) {
                                views.get(i).setBackgroundColor(colors.get(i));
                            }
                            Collections.rotate(colors,-1);
                        }
                    });
                    SystemClock.sleep(500);
                }
            }
        }).start();*/

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < colors.size(); i++) {
                    views.get(i).setBackgroundColor(colors.get(i));
                }
                Collections.rotate(colors, -1);
                handler.postDelayed(this, 500);
            }
        }, 500);
    }
}
