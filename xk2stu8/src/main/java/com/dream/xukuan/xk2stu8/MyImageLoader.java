package com.dream.xukuan.xk2stu8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Administrator on 2018/3/25.
 */

public class MyImageLoader {

    public static final String TAG = "MyImageLoader";
    private static MyImageLoader loader;
    private static LruCache<String, Bitmap> sLruCache;
    private ImageView imageView;
    private static DiskCacheUtil sDiskCacheUtil;
    public MyImageLoader(){

    }
    //返回一个单例模式的图片加载对象
    public static MyImageLoader with(Context context){
        if(loader == null){
            loader = new MyImageLoader();
            sLruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()/8)){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };
            //创建磁盘缓存对象
            sDiskCacheUtil = DiskCacheUtil.getInstance(context);
        }
        return loader;
    }

    public MyImageLoader load(String urlString) {
        //从内存缓存中查找，
        Bitmap bitmap = sLruCache.get(urlString);
        if (bitmap != null) {
            //找到，直接使用，
            Log.d(TAG, "load: 从内存缓存中找到！");
            if (imageView != null)
                imageView.setImageBitmap(bitmap);
        } else {
            // 没有找到，到磁盘缓存中找
            bitmap = sDiskCacheUtil.get(urlString);
            if(bitmap !=null){
                Log.d(TAG, "load: 从磁盘缓存中找到图片"+imageView);
                //加入内存缓存
                sDiskCacheUtil.put(urlString,bitmap);
                imageView.setImageBitmap(bitmap);
            }else {
                    //磁盘中没找到，再到网络下载,下载后，存入内存缓存
                    Log.d(TAG, "load: ....需要联网下载");
                    downLoadBitmap(urlString);
            }
        }
        return this;
    }

    private void downLoadBitmap(final String urlString) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection urlConnection = (HttpURLConnection) new URL(urlString).openConnection();
                    final Bitmap bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());

                    //让主线程更新ui
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                            sLruCache.put(urlString,bitmap);
                            //放入磁盘缓存
                            sDiskCacheUtil.put(urlString,bitmap);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public MyImageLoader into(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }
}
