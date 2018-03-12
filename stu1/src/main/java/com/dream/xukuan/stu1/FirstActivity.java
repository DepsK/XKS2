package com.dream.xukuan.stu1;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        viewPager = (ViewPager) findViewById(R.id.first_viewpager);
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.activity_first_pager1,null);
        View view2 = inflater.inflate(R.layout.activity_first_pager2,null);
        View view3 = inflater.inflate(R.layout.activity_first_pager3,null);
        List<View> viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        MyFirstAdapter adapter = new MyFirstAdapter(viewList);
        viewPager.setAdapter(adapter);
    }
}
