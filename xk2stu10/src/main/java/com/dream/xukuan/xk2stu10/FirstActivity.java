package com.dream.xukuan.xk2stu10;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dream.xukuan.xk2stu10.views.MyNotePadView;

import java.util.Random;

public class FirstActivity extends AppCompatActivity {


    MyNotePadView notePadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        notePadView = (MyNotePadView) findViewById(R.id.first_note_view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("修改线条颜色");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("修改线条颜色")){
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            int color = Color.rgb(red,green,blue);
            notePadView.setLineColor(color);
        }
        return true;
    }
}
