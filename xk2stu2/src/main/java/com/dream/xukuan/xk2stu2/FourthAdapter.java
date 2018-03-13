package com.dream.xukuan.xk2stu2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class FourthAdapter extends FragmentPagerAdapter {


    private final String[] urls;
    private final String[] titles;

    public FourthAdapter(FragmentManager fm, String[] urls,String[] titles) {
        super(fm);
        this.urls = urls;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        SecondFragment fragment = new SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",urls[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}