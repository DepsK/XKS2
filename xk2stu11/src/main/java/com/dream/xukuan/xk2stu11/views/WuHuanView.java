package com.dream.xukuan.xk2stu11.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author XK
 * @date 2018/3/27.
 */
public class WuHuanView extends View {

    private static final String TAG = "WuHuanView";
    Paint mPaint;

    public WuHuanView(Context context) {
        this(context,null);
    }

    public WuHuanView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WuHuanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    class MyCircle{

        int cx,cy,cr,color;

        public MyCircle(int cx,int cy ,int cr,int color){
            this.cx = cx;
            this.cy = cy;
            this.cr = cr;
            this.color = color;
        }
    }
    MyCircle[] circles = new MyCircle[5];

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = (widthMode ==MeasureSpec.EXACTLY )?MeasureSpec.getSize(widthMeasureSpec):600;
        int height = (heightMode == MeasureSpec.EXACTLY)?MeasureSpec.getSize(heightMeasureSpec):600;

        int cx = width/2;
        int cy = height/2;

        int cr = width/8;
        int cd = cr/2;//圆之间的间隙
        circles[0] = new MyCircle(cx,cy,cr, Color.BLACK);
        circles[1] = new MyCircle(cx-2*cr-cd,cy,cr,Color.BLUE);
        circles[2] = new MyCircle(cx+2*cr+cd,cy,cr,Color.RED);
        circles[3] = new MyCircle(cx-cr-cd/2,cy+cr,cr,Color.YELLOW);
        circles[4] = new MyCircle(cx+cr+cd/2,cy+cr,cr,Color.GREEN);
        setMeasuredDimension(width,height);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i <circles.length ; i++) {
            MyCircle circle = circles[i];
            mPaint.setColor(circle.color);
            canvas.drawCircle(circle.cx,circle.cy,circle.cr,mPaint);
        }
    }


}