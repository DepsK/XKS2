package com.dream.xukuan.xk2stu11.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author XK
 * @date 2018/3/28.
 */
public class CircleImage extends ImageView {


    private Paint mPaint;

    public CircleImage(Context context) {
        this(context,null);
    }

    public CircleImage(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //得到用户设置的图片
        Drawable drawable = getDrawable();
        if(drawable!=null&&drawable instanceof BitmapDrawable){
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            //把用户设置的图片变成圆形图片
            Bitmap dstBitmap = getCircleBitmap(bitmap);
            //再绘制到画布：如果要完全显示圆形图像，需要压缩
            //srcRect代表图片要绘制的范围：圆形图形需要整体绘制
            Rect srcRect= new Rect(0,0,dstBitmap.getWidth(),dstBitmap.getHeight());
            Rect dstRect ;
            if(getWidth()>getHeight()){
                dstRect = new Rect(getWidth()/2-getHeight()/2,0,getWidth()+getHeight(),getHeight());
            }else {
                dstRect = new Rect(0,getHeight()/2-getWidth()/2,
                        getWidth(),getHeight()/2+getWidth()/2);
            }
            canvas.drawBitmap(dstBitmap,srcRect,dstRect,mPaint);
        }else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        //需要一张新画布:只需要内切圆的部分，新画布的大小应该就是内切圆的大小,
        int srcWidth = bitmap.getWidth();
        int srcHeight = bitmap.getHeight();
        int width = srcWidth<srcHeight?srcWidth:srcHeight;
        Bitmap dstBitmap = Bitmap.createBitmap(width,width, Bitmap.Config.ARGB_8888);
        //画布的构造方法传入了一张空白的Bitmap,将来在这张画布上绘制的图案最终其实就是绘制在Bitmap上了
        Canvas canvas = new Canvas(dstBitmap);
        //在画布上绘制圆形，---dst
        mPaint.setColor(Color.RED);
        float cx,cy,cr;
        cx = width/2;
        cy = width/2;
        cr = cx;
        canvas.drawCircle(cx,cy,cr,mPaint);
        //设置画笔的混合模式为SrcIn
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //在画布上绘制原图:原图压缩后绘制
        Rect srcRect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        Rect dstRect = new Rect(0,0,width,width);
        canvas.drawBitmap(bitmap,srcRect,dstRect,mPaint);
        return dstBitmap;
    }
}