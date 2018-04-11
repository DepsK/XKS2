package com.dream.xukuan.xk2stu12;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshActivity extends AppCompatActivity {

    SwipeRefreshLayout refresh;
    ListView listView;
    List<String > datas = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        listView = (ListView) findViewById(R.id.swipe_listView);
        mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(mAdapter);
        refresh.setColorSchemeColors(Color.RED,Color.BLUE, Color.GREEN);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<String> newDatas = new ArrayList<String>();
                        for (int i = 0; i <50 ; i++) {
                            SystemClock.sleep(200);
                            newDatas.add("数据----》"+i);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.addAll(newDatas);
                                //让下拉刷新头部隐藏
                                refresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
