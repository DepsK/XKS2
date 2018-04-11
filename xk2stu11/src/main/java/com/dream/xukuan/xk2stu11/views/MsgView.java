package com.dream.xukuan.xk2stu11.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.dream.xukuan.xk2stu11.R;

/**
 * @author XK
 * @date 2018/3/28.
 */
public class MsgView extends View {


    private Bitmap bitmap;
    private Paint mPaint;

    public MsgView(Context context) {
        this(context,null);
    }

    public MsgView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MsgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.message_select);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(bitmap.getWidth(),bitmap.getHeight());
    }

    private int num = 100;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap,0,0,mPaint);
        //画圆形
        float cx = 0.75f*getWidth();
        float cy = 0.25f*getHeight();
        float cr = cy;
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx,cy,cr,mPaint);
        //画文字
        mPaint.setColor(Color.WHITE);
        mPaint.setFakeBoldText(true);//设置粗体文字
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(25);
        if(num>99){
            canvas.drawText("99+",cx,1.25f*cy,mPaint);
        }else {
            canvas.drawText(num+"",cx,1.25f*cy,mPaint);
        }


    }
}