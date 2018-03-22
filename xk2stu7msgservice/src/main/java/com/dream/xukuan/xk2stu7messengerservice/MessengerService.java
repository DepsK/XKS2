package com.dream.xukuan.xk2stu7messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author XK
 * @date 2018/3/22.
 */
public class MessengerService extends Service {

    private static final String TAG = "MessengerService";
    private  String data ="今天天气不错，就是挺冷的！";

    public MessengerService(){

    }

    Handler handler = new Handler(){
        //收到信使发出的消息时回调
        @Override
        public void handleMessage(Message msg) {
            if(msg.what ==1){
                Bundle bundle = msg.getData();
                Log.i(TAG, "handleMessage: 收到数据了！" + msg.arg1 + "--->" + msg.arg2 + "---->" + bundle.getString("data"));
            }else if (msg.what == 2){
                //需要传回数据给客户端:必须获得客户端的Messenger
                Messenger clientMessenger = msg.replyTo;
                Message replyMsg = Message.obtain();
                replyMsg.what = 3;//3.代表回复的信息
                Bundle bundle = new Bundle();
                bundle.putString("data",data);
                replyMsg.setData(bundle);
                try {
                    clientMessenger.send(replyMsg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Messenger messenger = new Messenger(handler);
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ....");
        return messenger.getBinder();
    }
}