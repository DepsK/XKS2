package com.dream.xukuan.xk2stu11.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dream.xukuan.xk2stu11.R;

/**
 * @author XK
 * @date 2018/3/28.
 */
public class SlideSwitch extends View {

    private Bitmap mBgOffBitmap;
    private Bitmap mBgOnBitmap;
    private Bitmap mSlideBitmap;
    private OnSlideStateChangedListener listener;
    private Paint mPaint;
    private boolean state;
    private int mWidth;
    private int mHeight;
    private int currentX;//圆心未知
    private int slideWidth;

    public interface OnSlideStateChangedListener{
        void onSlideStateChanged(boolean state);
    }

    public void setOnSlideStateChangedListener(OnSlideStateChangedListener listener){
        this.listener = listener;
    }

    public SlideSwitch(Context context) {
        this(context,null);
    }

    public SlideSwitch(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mBgOffBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.slide_bg_off);
        mBgOnBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.slide_bg_on);
        mSlideBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.slide_btn);
        mWidth = mBgOffBitmap.getWidth();
        mHeight = mBgOffBitmap.getHeight();
        //得到滑块的宽度
        slideWidth = mSlideBitmap.getWidth();
        if(attrs!=null){
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.myattr);
            state  = array.getBoolean(R.styleable.myattr_slideState, false);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth,mHeight);
        if(state){
            currentX = mWidth;
        }else {
            currentX = 0;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画背景:如果滑块在左侧，画关闭背景，在右侧就画打开背景
        if(currentX<mWidth/2){
            canvas.drawBitmap(mBgOffBitmap,0,0,null);
        }else {
            canvas.drawBitmap(mBgOnBitmap,0,0,null);
        }

        //画滑块
        //如果手指的位置<slideWidth/2,应该让滑块就显示在左侧边缘
        if(currentX<slideWidth/2){
            currentX = slideWidth/2;
        }else if(currentX>mWidth-slideWidth/2){
            currentX = mWidth - slideWidth/2;
        }
        canvas.drawBitmap(mSlideBitmap,currentX-slideWidth/2,0,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean prevState = state;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{

            }
            break;
            case MotionEvent.ACTION_MOVE:{
                currentX = (int) event.getX();
                invalidate();
            }
            break;
            case MotionEvent.ACTION_UP:{
                if(event.getX()<mWidth/2){
                    currentX = 0;
                    state = false;
                }else {
                    currentX = mWidth;
                    state = true;
                }
                if(state!=prevState){
                    if(listener != null){
                        listener.onSlideStateChanged(state);
                    }
                }
                invalidate();
            }
            break;
            default:
        }
        return true;
    }
}