package com.dream.xukuan.xk2stu2;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class FirstHttpUtils {

    public interface OnLoadCompletedListener{
        void OnLoadCompleted(String json);
    }

    private static OnLoadCompletedListener mListener;
    public static void loadJson(final Activity activity, OnLoadCompletedListener listener){
        mListener = listener;
        //1.创建一个客户对象
        OkHttpClient client = new OkHttpClient();
        //2.创建请求对象
        Request request = new Request.Builder().url(FirstContant.bannerUrlString).build();
        //3.通过客户端发起请求:建立会话
        Call call = client.newCall(request);
        //这里相当于有一个线程在进行网络连接，如果要接收结果，必须传入一个回调接口的对象
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Toast.makeText(activity,"下载失败",Toast.LENGTH_SHORT).show();
            }

            //下载成功的时候回调:运行在子线程上，不能进行ui操作
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                final String jsonString = body.toString();
                if(mListener !=null){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mListener.OnLoadCompleted(jsonString);
                        }
                    });
                }
            }
        });
    }
} 