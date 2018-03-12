package com.dream.xukuan.xks3;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author XK
 * @date 2018/3/12.
 */
public class MySecondAdapter extends PagerAdapter {

    private final Context context;
    private final int[] pics;

    public MySecondAdapter(Context context, int[] pics) {
        this.context = context;
        this.pics = pics;
    }

    @Override
    public int getCount() {
        return pics.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(pics[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}