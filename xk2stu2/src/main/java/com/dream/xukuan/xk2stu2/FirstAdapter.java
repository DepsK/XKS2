package com.dream.xukuan.xk2stu2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class FirstAdapter extends PagerAdapter {


    private final List<FirstTopBean> list;
    private final Context context;

    public FirstAdapter(Context context, List<FirstTopBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //创建ImageView
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        String url = FirstContant.hostUrlString+list.get(position).getImageUrl();
        //用Picasso下载图片显示到imageView
        Picasso.with(context).load(url).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}