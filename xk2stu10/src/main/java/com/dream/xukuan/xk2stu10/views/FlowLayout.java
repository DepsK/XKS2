package com.dream.xukuan.xk2stu10.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/3/26.
 */
public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //重写的目的是为了得到子控件的外部边距
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return  new MarginLayoutParams(getContext(),attrs);
    }

    //用来描述一行
    class LineViews{
        //存放本行的控件
        List<View> childViews = new ArrayList<>();
        int lineWidth = 0;
        int lineHeight = 0;
    }
    //用来存放所有行的
    List<LineViews> lines = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int ml=0,mt=0,mr=0,mb=0;
        int startTop = 0;
        for (int i = 0; i <lines.size() ; i++) {
            List<View> childViews = lines.get(i).childViews;
            ml = 0;//每一行的第一个控件的左边线都从0开始
            if(i>0){
                //每一行要调整top
                startTop += lines.get(i-1).lineHeight;
            }
            for (int j = 0; j < childViews.size(); j++) {
                View view = childViews.get(j);
                MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                ml +=params.leftMargin;
                mt = startTop+params.topMargin;
                mr = ml+view.getMeasuredWidth();
                mb = mt+view.getMeasuredHeight();
                view.layout(ml,mt,mr,mb);
                //调整下一个控件的左边线
                ml = mr + params.rightMargin;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        //由于onMeasure方法会回调多次，如果不请空，会把子控件加入集合多次
        //一定要清空
        lines.clear();
        //创建一个行对象
        LineViews line = new LineViews();
        int maxLineWidth = 0;//记录行宽的最大值
        int sumLineHeight = 0;//记录所有的行高和
        //通知测量所有子控件的宽度和高度
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //统计每一行的信息，
        for (int i = 0; i < getChildCount() ; i++) {
            View childAt = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) childAt.getLayoutParams();
            //判断：子控件加入本行是否会超过父容器的宽度
            int childWidth = childAt.getWidth()+params.leftMargin+params.rightMargin;
            int childHeight = childAt.getHeight() + params.topMargin+params.bottomMargin;
            if((childWidth+line.lineWidth)>width){
                //超过，需要放入新行，并且之前的这行就完成了，需要统计行高和行宽
                maxLineWidth = Math.max(maxLineWidth,line.lineWidth);
                sumLineHeight += line.lineHeight;
                //将本行加入行集合
                lines.add(line);
                //新起一行
                line = new LineViews();
            }
            //有两种情况：新起的一行，加入本行不超过父容器宽度
            //将控件加入这行
            line.childViews.add(childAt);
            //统计本行的行高和行宽
            //行宽=之前的控件的宽度和+这次加入的控件的宽度
            line.lineWidth += childWidth;
            //本行控件的高度的最大值
            line.lineHeight+=Math.max(line.lineHeight,childHeight);
            //如果是最后一行，最后一行也需要加入行集合，也需要统计行的数据
            if(i==getChildCount()-1){
                lines.add(line);
                maxLineWidth = Math.max(maxLineWidth,line.lineWidth);//最后一行的行宽是否是最大的
                sumLineHeight += line.lineHeight;//最后一行的行高也要计入总高度
            }
        }
        //如果宽度是at_most模式，每一行的宽度找出最大值作为容器的宽度
        int measureWidth = (wMode ==MeasureSpec.AT_MOST)?width:maxLineWidth;
        //如果高度是at_most模式，每一行的高度累加作为容器的高度
        int measureHeight = (hMode == MeasureSpec.AT_MOST)?height:sumLineHeight;
        //设置最终的测量高度和宽度
        setMeasuredDimension(measureWidth,measureHeight);
    }
}