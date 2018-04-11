package com.dream.xukuan.xk2stu14;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.BounceInterpolator;

/**
 * @author XK
 * @date 2018/4/2.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    Paint mPaint;
    int width,height;
    //定义三个点
    PointF p1,p2,p3;
    //绘制路径
    Path mPath;
    //动画集合
    ObjectAnimator animator;

    public MySurfaceView(Context context) {
        this(context,null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint= new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(25);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
        getHolder().addCallback(this);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            //得到控件的宽高
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        p1 = new PointF(0,height/2);
        p2 = new PointF();
        p3 = new PointF(width,height/2);
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        canvas.drawLine(p1.x,p1.y,p3.x,p3.y,mPaint);
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void draw(PointF p){
        Canvas canvas = getHolder().lockCanvas();
        //初始化画布，设置颜色为白色
        canvas.drawColor(Color.WHITE);
        // 重置路径
        mPath.reset();
        //移动到起点
        mPath.moveTo(p1.x,p1.y);
        //设置绘制路径
        mPath.quadTo(p.x,p.y,p3.x,p3.y);
        //显示出来
        canvas.drawPath(mPath,mPaint);
        //在绘制结束后一定要解锁画布
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                if(animator !=null){
                    animator.cancel();
                }
                p2.set(event.getX(),event.getY());
                draw(p2);
            }
            break;
            case MotionEvent.ACTION_MOVE:{
                p2.set(event.getX(),event.getY());
                draw(p2);
            }
            break;
            case MotionEvent.ACTION_UP:{
                PointF pointF = new PointF(event.getX(),event.getY());
                reset(pointF);
            }
            break;
            default:
        }
        return true;
    }

    public void setPointX(float px){
        p2.x =px;
        draw(p2);
    }

    public float getPointX(){
        return p2.x;
    }

    public void setPointY(float py){
        p2.y = py;
        draw(p2);
    }

    public float getPointY(){
        return p2.y;
    }

    private void reset(PointF pointF) {
        PropertyValuesHolder pvhx = PropertyValuesHolder.ofFloat("PointY",pointF.y,p1.y);
        PropertyValuesHolder pvhy = PropertyValuesHolder.ofFloat("PointX",pointF.x,width/2);
        animator = ObjectAnimator.ofPropertyValuesHolder(this,pvhx,pvhy);
        animator.setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }
}