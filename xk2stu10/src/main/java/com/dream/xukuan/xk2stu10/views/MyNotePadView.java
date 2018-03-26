package com.dream.xukuan.xk2stu10.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.dream.xukuan.xk2stu10.R;

/**
 * @author XK
 * @date 2018/3/26.
 */
public class MyNotePadView  extends EditText{

    private static final String TAG = "MyNotePadView";
    private Paint mPaint;
    private int paintColor = Color.RED;
    private float paintWidth;

    public MyNotePadView(Context context) {
        super(context);
        init(context,null,0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //如果有自定义属性，获取自定义属性的值
        if(attrs !=null){
            //获取自定义属性的值：---将属性集中的所有自定义属性提取出来
            //返回的是自定义属性组成的数组对象
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.myattr);
            //获取自定义属性线条颜色的值
            paintColor = array.getColor(R.styleable.myattr_lineColor, Color.RED);
            paintWidth = array.getDimension(R.styleable.myattr_lineWidth,1);
        }
        //将获得自定义属性值设置给画笔
        mPaint = new Paint();
        mPaint.setColor(paintColor);
        mPaint.setStrokeWidth(paintWidth);
        //去锯齿
        mPaint.setAntiAlias(true);
        //去抖动
        mPaint.setDither(true);
    }

    public MyNotePadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public MyNotePadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int lineHeight = getLineHeight();
        int width = getWidth();
        //一页能够显示行数
        int count = getHeight()/lineHeight;
        //getLineCount已经输入的行数
        count = Math.max(getLineCount(),count);
        //循环画线
        for (int i = 0; i <count ; i++) {
            canvas.drawLine(0,lineHeight*(i+1)+getPaddingTop(),width,lineHeight*(i+1)+getPaddingTop(),mPaint);
        }
        super.onDraw(canvas);

    }

    public void setLineColor(int color){
        this.paintColor = color;
        mPaint.setColor(color);
        //尽快重新绘图:视图管理器会尽快回调onDraw方法重新绘图
        invalidate();
    }
}