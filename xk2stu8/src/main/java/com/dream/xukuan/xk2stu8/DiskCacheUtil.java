package com.dream.xukuan.xk2stu8;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/3/25.
 */

public class DiskCacheUtil {

    private static final String TAG = "print";
    private static DiskCacheUtil diskCacheUtil;
    private DiskLruCache mDiskLruCache;//磁盘缓存对象

    public DiskCacheUtil(Context context){
        try {
            mDiskLruCache = DiskLruCache.open(getCatchDir(context)//缓存目录
                                     , getAppVersion(context)//软件版本
                                    ,1
                                    , 10*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //得到缓存目录:如果有sd卡，缓存到sd卡，没有，就缓存到内部存储
    private File getCatchDir(Context context) {
        File dir ;
        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            //私有的外部缓存路径
            dir = context.getExternalCacheDir();
        }else {
            //内部的私有缓存路径
            dir = context.getCacheDir();
        }
        return new File(dir,"my_cache");
    }

    private int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static DiskCacheUtil getInstance(Context context) {
        if(diskCacheUtil == null){
            diskCacheUtil = new DiskCacheUtil(context);
        }
        return diskCacheUtil;
    }
    //从磁盘缓存查
    public Bitmap get(String urlString) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(getKey(urlString));
            if(snapshot == null){
                return null;
            }else {
                //获得输入流，流指向缓存文件
                InputStream inputStream = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //得到缓存文件的文件名：文件会被存入缓存路径
    //缓存文件的文件名应该是唯一并且不含特殊字符的，根据url计算文件名
    private String getKey(String urlString) {
        try {
            MessageDigest md = MessageDigest.getInstance("MDS");
            //传入需要加密的字节数组
            md.update(urlString.getBytes());
            //加密之后的字节数组：一个key对应一个唯一的
            byte[] digest = md.digest();
            //把字节数组转成十六进制字符串:一个字节8byte,刚好是2位16进制（0-F）
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < digest.length ; i++) {
                buffer.append(Integer.toHexString(0xFF & digest[i]));
            }
            return buffer.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return urlString.hashCode()+"";
    }

    //将数据放入磁盘缓存
    public void put(String urlString, Bitmap bitmap) {
        //得到一个Editor对象：edit方法传入的字符串就是文件名
        Log.d(TAG, "put: ...文件名："+getKey(urlString));
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(getKey(urlString));
            //得到输出流对象---指向文件，文件名就是getKey(urlString)的结果：参数是索引,一般填0
            OutputStream outputStream = editor.newOutputStream(0);
            //得数据存入输出流
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
