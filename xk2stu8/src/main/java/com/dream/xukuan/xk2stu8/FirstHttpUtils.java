package com.dream.xukuan.xk2stu8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author XK
 * @date 2018/3/22.
 */
public class FirstHttpUtils  {

    private OnLoadCompletedListener listener;

    public void load(String urlString, OnLoadCompletedListener listener){
        this.listener = listener;
        MyLoadTask task = new MyLoadTask();
        task.execute(urlString);
    }

    public interface OnLoadCompletedListener{
        void onLoadComplete(Bitmap bitmap);
    }

    private class MyLoadTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            String urlString = params[0];
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(listener != null){
                listener.onLoadComplete(bitmap);
            }
        }
    }
}