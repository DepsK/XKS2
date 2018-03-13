package com.dream.xukuan.xk2stu2;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FourthActivity extends AppCompatActivity {

    String[] titles = {"社会","军事","娱乐","体育","科技","本地","房产","经济"};
    String[] urls = {"社会url","军事url","娱乐url","体育url","科技url","本地url","房产url","经济url"};
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        tabLayout = (TabLayout) findViewById(R.id.fourth_design);
        viewPager = (ViewPager) findViewById(R.id.fourth_viewpager);
        FourthAdapter adapter = new FourthAdapter(getSupportFragmentManager(),urls,titles);
        viewPager.setAdapter(adapter);
        //设置tab部分可以滚动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置tab的标题颜色
        tabLayout.setTabTextColors(Color.DKGRAY,Color.RED);
        //设置指示器的颜色
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
        //设置TabLayout和ViewPager的联动，必须在ViewPager设置完适配器之后调用
        tabLayout.setupWithViewPager(viewPager);

    }
}
