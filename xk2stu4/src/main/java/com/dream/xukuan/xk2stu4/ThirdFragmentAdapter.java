package com.dream.xukuan.xk2stu4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author XK
 * @date 2018/3/14.
 */
public class ThirdFragmentAdapter extends FragmentPagerAdapter {


    private final String[] titles;

    public ThirdFragmentAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
//        ThirdListFragment fragment = new ThirdListFragment();
        ThirdRecyclerFragment fragment = new ThirdRecyclerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",titles[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}