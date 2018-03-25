package com.dream.xukuan.xk2stu8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivity";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        imageView = (ImageView) findViewById(R.id.third_image);
        //二次采样
        sampleBitmap();
    }

    private void sampleBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //第一采样：计算采样率需要：原图的大小（3840*2160）和目标的大小（768*1280）
        //目标————》得到原图大小
        //规定只加载图片的边缘：--（3840+2160）*2
        options.inJustDecodeBounds = true;
        //第一次加载不用接收返回值：得到的图片宽高保存在参数三中
        BitmapFactory.decodeResource(getResources(),R.mipmap.window,options);
        //计算采样率
        int sample = calclateSample(options,getResources().getDisplayMetrics().widthPixels,
                getResources().getDisplayMetrics().heightPixels);
        //设置采样率
        options.inSampleSize = sample;
        //取消只加载边缘的设置
        options.inJustDecodeBounds = false;
        //第二次采样：根据给定的采样率真正加载图片，
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.window,options);
        Log.d(TAG, "sampleBitmap: 采样后："+bitmap.getByteCount()/1024);
        imageView.setImageBitmap(bitmap);
    }

    private int calclateSample(BitmapFactory.Options options, int widthPixels, int heightPixels) {
        Log.d(TAG, "calclateSample: 采样前："+options.outHeight*options.outWidth*4/1024);
        int widthSample = options.outWidth/widthPixels;
        int heightSample = options.outHeight/heightPixels;
        return Math.max(widthSample,heightSample);
    }
}
