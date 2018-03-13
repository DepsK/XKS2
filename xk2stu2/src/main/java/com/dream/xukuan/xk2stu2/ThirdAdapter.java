package com.dream.xukuan.xk2stu2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class ThirdAdapter extends LoopPagerAdapter {

    private final Context context;
    private final List<ThirdTopBean> list;

    public ThirdAdapter(Context context, RollPagerView viewPager, List<ThirdTopBean> list) {
        super(viewPager);
        this.context = context;
        this.list =list;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(context).load(ThirdContant.hostUrlString+list.get(position).getImageUrl()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public int getRealCount() {
        return list.size();
    }
}