package com.dream.xukuan.xk2stu10.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Attr;

/**
 * @author XK
 * @date 2018/3/26.
 */
public class MyLayout extends ViewGroup {

    public MyLayout(Context context){
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public MyLayout(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    //当设置了子控件的margin值之类的跟父容器有关的属性de话就回调
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View childView = getChildAt(0);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //需要重写generateLayoutParams方法，在其中返回一个MarginLayoutParams的对象
        MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
        int measuredWidth = 0;
        //获得宽度方向上的数值的类型
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if(widthMode == MeasureSpec.EXACTLY){
            //如果用户设置的宽度值是200dp,match_parent(精确模式),那么此容器的宽度就是用户设置的值，跟子控件没关系
            measuredWidth = MeasureSpec.getSize(widthMeasureSpec) ;
        }else if(widthMode == MeasureSpec.AT_MOST){
            //如果用户设置的宽度值是wrap_content(最大值模式)，那么容器的宽度就和子控件的宽度有关
            //获得子控件的宽度
            //必须先通知视图管理器，测量子视图的宽高
            measuredWidth = childView.getMeasuredWidth()+params.leftMargin+params.rightMargin;
        }
        //获得高度
        int measuredHeight = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY){
            measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        }else if (heightMode == MeasureSpec.AT_MOST){
            measuredHeight = childView.getMeasuredHeight()+params.topMargin+params.bottomMargin;
        }
        //必须传回测量到的高度给视图管理器
        setMeasuredDimension(measuredWidth,measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childAt = getChildAt(0);
        MarginLayoutParams params = (MarginLayoutParams) childAt.getLayoutParams();
        int ml = params.leftMargin;
        int mt = params.topMargin;
        int mr = ml+childAt.getMeasuredWidth();
        int mb = mt+childAt.getMeasuredHeight();
        childAt.layout(ml,mt,mr,mb);
    }
}