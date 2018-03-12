package com.dream.xukuan.xks3;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    Context context;
    ViewPager viewPager;
    LinearLayout ll;
    int[] pics = new int[]{R.mipmap.p1,R.mipmap.p2,R.mipmap.p3,R.mipmap.p4,R.mipmap.p5,R.mipmap.p6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        context = SecondActivity.this;
        viewPager = (ViewPager) findViewById(R.id.second_viewpager);
        ll = (LinearLayout) findViewById(R.id.second_dot_container);
        initDot();
        MySecondAdapter adapter = new MySecondAdapter(context,pics);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: ..."+position+"--->"+positionOffset+"---->"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                int count = ll.getChildCount();
                for (int i =0;i<count;i++){
                    ImageView imageView = (ImageView) ll.getChildAt(i);
                    imageView.setImageResource(R.mipmap.page);
                    if (position == i){
                        imageView.setImageResource(R.mipmap.page_now);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE){
                    Log.d(TAG,"onPageScrollStateChanged---静止");
                }
                if(state == ViewPager.SCROLL_STATE_SETTLING){
                    Log.d(TAG,"onPageScrollStateChanged---选中");
                }
                if(state == ViewPager.SCROLL_STATE_DRAGGING){
                    Log.d(TAG,"onPageScrollStateChanged---拖动");
                }
            }
        });
    }

    private void initDot() {
        for(int i=0;i<pics.length;i++){
            ImageView imageView = new ImageView(context);
            imageView.setPadding(10,0,10,0);
            imageView.setImageResource(R.mipmap.page);
            if (i==0){
                imageView.setImageResource(R.mipmap.page_now);
            }
            imageView.setTag(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = Integer.parseInt(v.getTag().toString());
                    //参数二：设置是否平滑滚动，默认为true平滑
                    viewPager.setCurrentItem(index,false);
                }
            });
            ll.addView(imageView);
        }
    }
}
