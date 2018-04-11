package com.dream.xukuan.xk2stu11.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author XK
 * @date 2018/3/27.
 */
public class ProgressView extends View {

    private int mWidth;
    private int mHeight;
    private Paint paint;
    private int max ;
    private  int progress;
    public ProgressView(Context context) {
        this(context,null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        max = 100;
        progress = 50;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置成固定大小
        setMeasuredDimension(300,300);
        mWidth = getWidth();
        mHeight = getHeight();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        //让视图管理器尽快回调onDraw重新绘图
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float cx = mWidth/2;
        float cy = mHeight/2;
        float cr = cx-5;
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        //绘制大圆环
        canvas.drawCircle(cx,cy,cr,paint);
        //绘制圆圈:大小和进度有关
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(cx,cy,cr*(progress*1.0f)/max,paint);
        //绘制圆弧
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(5,5,mWidth-5,mHeight-5);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF,-90,360*(progress*1.0f)/max,false,paint);
    }
}