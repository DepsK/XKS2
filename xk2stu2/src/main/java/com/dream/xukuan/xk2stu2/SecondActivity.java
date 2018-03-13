package com.dream.xukuan.xk2stu2;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout titleLL;
    View indicatorView;
    HorizontalScrollView scrollView;
    String[] titles = {"社会","军事","娱乐","体育","科技","本地","房产","经济"};
    String[] urls = {"社会url","军事url","娱乐url","体育url","科技url","本地url","房产url","经济url"};
    int lastIndex = -1;
    List<Fragment> fragments = new ArrayList<>();
    int tabWidth;
    int screenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        initTab();
        setViewPager();
    }

    private void initTab() {
        lastIndex = 0;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        tabWidth = screenWidth/4;
        //加入标题
        for (int i = 0; i < titles.length ; i++) {
            TextView titleView = new TextView(SecondActivity.this);
            titleView.setText(titles[i]);
            titleView.setWidth(tabWidth);
            titleView.setGravity(Gravity.CENTER);
            titleView.setTag(i);
            titleView.setTextColor(Color.DKGRAY);
            if (i==0){
                titleView.setTextColor(Color.RED);
            }
            titleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = Integer.parseInt(v.getTag().toString());
                    viewPager.setCurrentItem(index,false);
                }
            });
            titleLL.addView(titleView);
        }
        //设置滑块的初始值：指示器
        ViewGroup.LayoutParams params = indicatorView.getLayoutParams();
        params.width = tabWidth;
        indicatorView.setLayoutParams(params);

    }

    private void setViewPager() {
        SecondAdapter adapter = new SecondAdapter(getSupportFragmentManager(),urls);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滑块跟随：设置滑块的左边距：marginLeft
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) indicatorView.getLayoutParams();
                int left = (int) ((position+positionOffset)*tabWidth);
                params.leftMargin = left;
                indicatorView.setLayoutParams(params);
                //让scrollView跟随
                scrollView.scrollTo(left-tabWidth,0);
            }

            @Override
            public void onPageSelected(int position) {
                TextView preTextView = (TextView) titleLL.getChildAt(lastIndex);
                preTextView.setTextColor(Color.DKGRAY);
                TextView titleView = (TextView) titleLL.getChildAt(position);
                titleView.setTextColor(Color.RED);
                lastIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        scrollView = (HorizontalScrollView) findViewById(R.id.second_scrollview);
        viewPager = (ViewPager) findViewById(R.id.second_viewpager);
        titleLL = (LinearLayout) findViewById(R.id.second_title_ll);
        indicatorView = findViewById(R.id.second_indicator_view);
    }
}
