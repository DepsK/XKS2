package com.dream.xukuan.xk2stu2;

import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private TextView titleView;
    private ViewPager bannerViewPager;
    private LinearLayout dotContainer;
    private boolean flag = false;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initViews();
        //用okHttp，下载数据
        FirstHttpUtils.loadJson(FirstActivity.this, new FirstHttpUtils.OnLoadCompletedListener() {
                    @Override
                    public void OnLoadCompleted(String json) {
                        //json解析，显示到ViewPager
                        List<FirstTopBean> list = parseJson(json);
                        //根据下载到的结果，设置视图
                        setView(list);
                    }
                });

    }


    private void setView(final List<FirstTopBean> list) {
        //设置ViewPager
        FirstAdapter adapter = new FirstAdapter(this,list);
        bannerViewPager.setAdapter(adapter);
        bannerViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                //修改标题
                titleView.setText(list.get(position).getSubject());

                //修改小圆点的选中状态
                for (int i = 0; i < dotContainer.getChildCount(); i++) {
                    ImageView dotView = (ImageView) dotContainer.getChildAt(i);
                    dotView.setImageResource(R.mipmap.page);
                    if (position == i){
                        dotView.setImageResource(R.mipmap.page_now);
                    }
                }
            }
        });

        flag = true;
        startPlay(list.size());

        //添加小圆点
        for (int i = 0; i < list.size(); i++) {
            ImageView dotView = new ImageView(this);
            dotView.setPadding(5,0,5,0);
            dotView.setImageResource(R.mipmap.page);
            if (i == 0){

                dotView.setImageResource(R.mipmap.page_now);
            }

            dotContainer.addView(dotView);
        }

        //修改title
        titleView.setText(list.get(0).getSubject());
    }

    private void startPlay(final int size) {
        //开始自动播放
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    SystemClock.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //获得当前的位置
                            currentItem = bannerViewPager.getCurrentItem();
                            bannerViewPager.setCurrentItem((currentItem + 1) % size);
                        }
                    });
                }
            }
        }).start();
    }

    private List<FirstTopBean> parseJson(String json) {
        List<FirstTopBean> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject paramzObject = jsonObject.getJSONObject("paramz");
            JSONArray jsonArray = paramzObject.getJSONArray("tops");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject topBeanJsonObject = jsonArray.getJSONObject(i);
                String imgUrl = topBeanJsonObject.getString("photo");
                String title = topBeanJsonObject.getString("subject");
                FirstTopBean topBean = new FirstTopBean();
                topBean.setImageUrl(imgUrl);
                topBean.setSubject(title);
                list.add(topBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void initViews() {
        bannerViewPager = (ViewPager) findViewById(R.id.first_viewpager);
        titleView = (TextView) findViewById(R.id.first_text);
        dotContainer = (LinearLayout) findViewById(R.id.first_ll);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
