package com.dream.xukuan.stu1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author XK
 * @date 2018/3/12.
 */
public class MyThirdAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public MyThirdAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("title",ThirdContant.titles[position]);
        Fragment fragment = fragments.get(position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
      return   fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container,position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container,position,object);
    }


}
