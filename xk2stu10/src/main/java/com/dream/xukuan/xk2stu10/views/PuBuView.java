package com.dream.xukuan.xk2stu10.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * @author XK
 * @date 2018/3/26.
 */
public class PuBuView extends ScrollView {

    private LinearLayout mRootLayout;

    public PuBuView(Context context) {
        this(context,null);
    }

    public PuBuView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PuBuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //在ScrollView中增加一个H的线性
    //在H的线性中增加三个V的线性
    private void init() {
        mRootLayout = new LinearLayout(getContext());
        mRootLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        for (int i = 0; i <3 ; i++) {
            LinearLayout childLayout = new LinearLayout(getContext());
            childLayout.setOrientation(LinearLayout.VERTICAL);
            //设置子控件在父容器中的布局参数
            childLayout.setLayoutParams(params);
            //把v线性加入h线性
            mRootLayout.addView(childLayout);
        }
        addView(mRootLayout);
    }
    int count = 0;//count代表图片数量
    public void addImage(Bitmap bitmap){
        if(bitmap !=null){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(bitmap);
            imageView.setMaxWidth(getWidth()/3);
            imageView.setAdjustViewBounds(true);
            //将imageView加入V的线性
            int index = count%mRootLayout.getChildCount();
            LinearLayout liner = (LinearLayout) mRootLayout.getChildAt(index);
            liner.addView(imageView);
            count++;
        }
    }
}