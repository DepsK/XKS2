package com.dream.xukuan.xk2stu11.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author XK
 * @date 2018/3/28.
 */
public class GuaGuaView extends View {

    private Paint mPaint;
    private String text = "￥1000万";
    Path path = new Path();

    public GuaGuaView(Context context) {
        this(context,null);
    }

    public GuaGuaView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GuaGuaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(250,100);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(25);
        mPaint.setFakeBoldText(true);
        //用于存放文字的边框的
        Rect rect = new Rect();
        mPaint.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text,getWidth()/2-rect.width()/2,getHeight()/2+rect.height()/2,mPaint);
        //绘制涂层：需要在新的画布上绘制
        Bitmap bitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tucengCanvas = new Canvas(bitmap);
        //先画颜色
        tucengCanvas.drawColor(Color.GRAY);
        //设置画笔的混合模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        //绘制用户手指划过的路径
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        tucengCanvas.drawPath(path,mPaint);
        //将混合好的涂层画到控件中
        canvas.drawBitmap(bitmap,0,0,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                path.moveTo(event.getX(),event.getY());
            }
            break;
            case MotionEvent.ACTION_MOVE:{
                path.lineTo(event.getX(),event.getY());
                invalidate();
            }
            break;
            default:
        }
        return true;
    }
}