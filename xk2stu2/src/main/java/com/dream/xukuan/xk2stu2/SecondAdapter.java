package com.dream.xukuan.xk2stu2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class SecondAdapter extends FragmentPagerAdapter {


    private final String[] urls;

    public SecondAdapter(FragmentManager fm, String[] urls) {
        super(fm);
        this.urls = urls;
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

}