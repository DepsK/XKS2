package com.dream.xukuan.xk2stu11.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author XK
 * @date 2018/3/27.
 */
public class CanvasTestView extends View {

    public CanvasTestView(Context context) {
        super(context);
    }

    public CanvasTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(25);
        //绘制路径
        Path path = new Path();
        path.moveTo(100,100);
        path.lineTo(400,100);
        path.lineTo(400,400);
        path.lineTo(100,400);
        path.close();//关闭路径，回到原点
        paint.setStyle(Paint.Style.STROKE);
        //设置线段连接处的风格
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path,paint);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        //画弧
        RectF rectF = new RectF((getWidth()/2-400/2),(getHeight()/2-300/2),(getWidth()/2+400/2),(getHeight()/2+300/2));
        canvas.drawArc(rectF,0,90,true,paint);

        //绘制圆形
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200,200,195,paint);
        canvas.save();
        //根据给定的点作为圆心旋转画布
        canvas.rotate(30,getWidth()/2,getHeight());
        //绘制圆角矩形
        paint.setColor(Color.YELLOW);
        canvas.drawRoundRect(rectF,10,10,paint);
        //恢复旋转之前的状态
        canvas.restore();
        paint.setStrokeWidth(25);
        canvas.drawPoint(300,300,paint);
        //绘制文字
        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(2);
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);
        String text = "今天天气不太好，貌似深圳也有雾霾了！";
        canvas.drawText(text,0,text.length(),300,300,paint);
        //绘制文字在路径上
        canvas.drawTextOnPath(text,path,0,0,paint);
    }
}