package com.dream.xukuan.xk2stu8;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "FirstActivity";
    ImageView imageView;
    String urlString = "http://d.hiphotos.baidu.com/image/h%3D200/sign=4241e02c86025aafcc3279cbcbecab8d/562c11dfa9ec8a13f075f10cf303918fa1ecc0eb.jpg";
    LruCache<String,Bitmap> lruCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        imageView = (ImageView) findViewById(R.id.first_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始加载图片：
                //从内存缓存中查找，如果找到，直接使用，
                Bitmap bitmap = lruCache.get(urlString);
                if(bitmap !=null){
                    Log.d(TAG, "onClick: ...从内存缓存中获得数据。。。");
                    imageView.setImageBitmap(bitmap);
                }else {
                    //如果没有找到，联网下载，存入内存缓存以便下次使用
                    Log.d(TAG, "onClick: ...需要联网下载。。。");
                    new FirstHttpUtils().load(urlString, new FirstHttpUtils.OnLoadCompletedListener() {
                        @Override
                        public void onLoadComplete(Bitmap bitmap) {
                            if(bitmap !=null){
                                imageView.setImageBitmap(bitmap);
                                lruCache.put(urlString,bitmap);
                            }
                        }
                    });
                }
            }
        });
        // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //空闲空间
        int freeMemory = (int) Runtime.getRuntime().freeMemory();
        Log.d(TAG, "onCreate: ...." + maxMemory / 1024 / 1024 + ":" + freeMemory / 1024 / 1024);
        // 一般情况，我们使用最大可用内存值的1/8作为缓存的大小即可。
        int cacheSize = maxMemory/8;
        // LruCache通过构造函数传入缓存值，以KB为单位。
        lruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int sizeOf = value.getByteCount();
                return sizeOf;
            }
        };
    }
}
