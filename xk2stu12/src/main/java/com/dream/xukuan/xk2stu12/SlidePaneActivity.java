package com.dream.xukuan.xk2stu12;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class SlidePaneActivity extends AppCompatActivity {

    ListView listView;
    FrameLayout frameLayout;
    SlidingPaneLayout slidingPaneLayout;
    Toolbar toolbar;
    String[] items = {"QQ钱包","会员特权","信息设置"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_pane);
        initViews();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.header_btn_more_nor);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开菜单
                slidingPaneLayout.openPane();
            }
        });
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new SlideFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title",items[position]);
                fragment.setArguments(bundle);
                transaction.replace(R.id.slide_frame,fragment);
                transaction.commit();
                //关闭菜单
                slidingPaneLayout.closePane();
            }
        });
    }

    private void initViews() {
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slide_pane);
        frameLayout = (FrameLayout) findViewById(R.id.slide_frame);
        listView = (ListView) findViewById(R.id.slide_list);
        toolbar = (Toolbar) findViewById(R.id.slide_toolbar);
    }
}
