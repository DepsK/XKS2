package com.dream.xukuan.xk2stu12;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asus on 2016/12/23.
 */
public class MyImageTask extends AsyncTask<String,Void,Bitmap>{
    private final OnLoadCompletedListener mOnLoadCompletedListener;

    public interface OnLoadCompletedListener{
        void  onLoadCompleted(Bitmap bitmap);
    }


    public MyImageTask(OnLoadCompletedListener listener) {
        mOnLoadCompletedListener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        SystemClock.sleep(3000);
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(params[0]).openConnection();
            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
     /*   if (bitmap!= null)
            imageView.setImageBitmap(bitmap);
        else
            imageView.setImageResource(R.mipmap.ic_launcher);*/
        if (mOnLoadCompletedListener != null){
            mOnLoadCompletedListener.onLoadCompleted(bitmap);
        }

    }
}
