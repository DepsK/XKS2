package com.dream.xukuan.stu1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        radioGroup = (RadioGroup) findViewById(R.id.third_radio);
        setListener();
        List<Fragment> fragments = new ArrayList<>();
        ThirdMessageFragment mesFragment = new ThirdMessageFragment();
        fragments.add(mesFragment);
        Fragment contactFragment =  new ThirdListFragment();
        fragments.add(contactFragment);
        Fragment findFragment =  new ThirdListFragment();
        fragments.add(findFragment);
        Fragment meFragment =  new ThirdListFragment();
        fragments.add(meFragment);
        viewPager = (ViewPager) findViewById(R.id.third_viewpager);
        MyThirdAdapter adapter =new MyThirdAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
               RadioButton button = (RadioButton) radioGroup.getChildAt(position);
                button.setChecked(true);
            }
        });
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) radioGroup.findViewById(checkedId);
                int index = Integer.parseInt(button.getTag().toString());
                viewPager.setCurrentItem(index,false);
            }
        });
    }
}
