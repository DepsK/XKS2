package com.dream.xukuan.xk2stu4;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    private Snackbar mSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setToolBar();
        setViewPager();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.third_float);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Replace with your own action",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show();
            }
        });
    }

    private void setViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.third_viewpager);
        String[] titles = {"科技","社会","娱乐","军事","生活","美食","体育"};
        ThirdFragmentAdapter adapter = new ThirdFragmentAdapter(getSupportFragmentManager(),titles);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.third_tablayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.third_toolbar);
        setSupportActionBar(toolbar);
        //不显示actionBar标题:
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.third_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.third_action_settings) {
            mSnackbar = Snackbar.make(findViewById(R.id.third_viewpager), "点击了菜单", Snackbar.LENGTH_INDEFINITE)
                    .setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mSnackbar.dismiss();
                        }
                    });
            mSnackbar.show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
