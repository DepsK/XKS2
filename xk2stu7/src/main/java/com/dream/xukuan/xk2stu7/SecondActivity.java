package com.dream.xukuan.xk2stu7;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {


    private static final String TAG = "SecondActivity";
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获得交互的对象：Messenger
            //传入IBinder对象创建Messenger,---这个messenger发出的消息会由创建IBinder的Messenger处理
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    //1.定义客户端的Handler
    Handler clientHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==3){
                Log.i(TAG, "handleMessage: ...收到服务端的数据：" + msg.getData().getString("data"));
            }
        }
    };

    private Messenger mMessenger;
    private Messenger clientMessenger = new Messenger(clientHandler);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void secondBind(View view){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.dream.xukuan.xk2stu7messengerservice",
                "com.dream.xukuan.xk2stu7messengerservice.MessengerService");
        intent.setComponent(componentName);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }
    //传递数据到服务端
    public void secondSend(View view){
        Message msg = Message.obtain();
        msg.arg1 = 100;
        msg.arg2 = 102;
        msg.what = 1;//1：代表传递数据
        Bundle bundle = new Bundle();
        bundle.putString("data","hahaha!Messenger真简单！");
        msg.setData(bundle);
        try {
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    //获取远端数据：客户端需要接收服务端发出消息，从消息中获得数据
    public void secondReceive(View view){
        //3.发出消息，通知服务端，说明需要数据
        Message msg = Message.obtain();
        msg.what = 2;//2代表需要数据
        //4.需要传入客户端 Messenger
        msg.replyTo = clientMessenger;
        try {
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
