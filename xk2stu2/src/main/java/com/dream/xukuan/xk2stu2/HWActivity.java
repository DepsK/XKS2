package com.dream.xukuan.xk2stu2;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class HWActivity extends AppCompatActivity {

    ViewPager viewPager;
    RadioGroup rg;
    FragmentPagerAdapter adapter;
    List<Fragment> fragments = new ArrayList<>();
    HWJingXuanFragment jingXuanFragment;
    HWLunTanFragment lunTanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw);
        jingXuanFragment = new HWJingXuanFragment();
        lunTanFragment = new HWLunTanFragment();
        fragments.add(jingXuanFragment);
        fragments.add(lunTanFragment);
        initViews();
        setAdapter();
    }

    private void setAdapter() {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(adapter);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.hw_rb_1:{
                        viewPager.setCurrentItem(0);
                    }
                    break;
                    case R.id.hw_rb_2:{
                        viewPager.setCurrentItem(1);
                    }
                    break;
                    default:
                }
            }
        });
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.hw_viewPager);
        rg = (RadioGroup) findViewById(R.id.hw_rg);
    }
}
