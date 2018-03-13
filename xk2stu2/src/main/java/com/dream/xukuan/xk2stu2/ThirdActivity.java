package com.dream.xukuan.xk2stu2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jude.rollviewpager.RollPagerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    RollPagerView pagerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        pagerView = (RollPagerView) findViewById(R.id.third_roll);
        ThirdHttpUtils.loadJson(ThirdActivity.this, new ThirdHttpUtils.OnLoadCompletedListener() {
            @Override
            public void OnLoadCompleted(String json) {
                List<ThirdTopBean> list = parseJson(json);
                setRollView(list);
            }
        });

    }

    private void setRollView(List<ThirdTopBean> list) {
        ThirdAdapter adapter = new ThirdAdapter(ThirdActivity.this,pagerView,list);
        pagerView.setAdapter(adapter);
        pagerView.setPlayDelay(2000);
    }

    private List<ThirdTopBean> parseJson(String json) {
        List<ThirdTopBean> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject paramzObject = jsonObject.getJSONObject("paramz");
            JSONArray jsonArray = paramzObject.getJSONArray("tops");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject topBeanJsonObject = jsonArray.getJSONObject(i);
                String imgUrl = topBeanJsonObject.getString("photo");
                String title = topBeanJsonObject.getString("subject");
                ThirdTopBean topBean = new ThirdTopBean();
                topBean.setImageUrl(imgUrl);
                topBean.setSubject(title);
                list.add(topBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }



}
