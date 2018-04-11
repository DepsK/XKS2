package com.dream.xukuan.xk2stu11.views;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * @author XK
 * @date 2018/3/27.
 */
public class MyTextView extends TextView {

    private static final String TAG = "MyTextView";
    private Paint mPaint;
    private Paint mCPaint;
    private float cx,cy,cr;
    private boolean flag = false ;



    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);

        //画圆的画笔
        mCPaint = new Paint();
        mCPaint.setAntiAlias(true);
        mCPaint.setDither(true);
        mCPaint.setStyle(Paint.Style.STROKE);
        mCPaint.setColor(Color.LTGRAY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画圆：
        if(flag){
            canvas.drawCircle(cx,cy,cr,mCPaint);
        }
        //保留:里面有绘制textView原本的内容
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        super.onDraw(canvas);
    }
    //触摸控件的时候会回调：按下：开始绘制圆形  松手：停止绘制
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                Log.i(TAG, "onTouchEvent: 按下");
                //按下：开始绘制圆形:按下的地方作为圆心
                cx = event.getX();
                cy = event.getY();
                cr = 0;
                flag = true;
                startDrawCircle();
            }
            break;
            case MotionEvent.ACTION_MOVE:{
                Log.i(TAG, "onTouchEvent: 移动");
                //得到手指移动的距离
                int mx = (int) (event.getX() - cx);
                int my = (int) (event.getY() -cy);
                //重新放置控件
                layout(getLeft()+mx,getTop()+my,getRight()+mx,getBottom()+my);
            }
            break;
            case MotionEvent.ACTION_UP:{
                Log.i(TAG, "onTouchEvent: 松开");
                //松手：停止绘制
                flag = false;
                invalidate();
            }
            break;
            default:
        }
        return true;
    }

    private void startDrawCircle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    postInvalidate();
                    cr+=10;
                    SystemClock.sleep(200);
                    //如果园半径超过对角线的长度，就不用再画了
                    if(cr>Math.sqrt(getWidth()*getWidth()+getHeight()*getHeight())){
                        break;
                    }
                }
            }
        }).start();
    }
}