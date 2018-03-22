package com.dream.xukuan.xk2stu7;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dream.xukuan.xk2stu7aidlservice.Car;
import com.dream.xukuan.xk2stu7aidlservice.IMyAidlInterface;

public class ThirdActivity extends AppCompatActivity {

    private Intent mIntent;
    private IMyAidlInterface mInter;

    private static final String TAG = "ThirdActivity";
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ...");
            mInter = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mIntent = new Intent();
        ComponentName name = new ComponentName("com.dream.xukuan.xk2stu7aidlservice", "com.dream.xukuan.xk2stu7aidlservice.MyService");
        mIntent.setComponent(name);
    }

    //绑定服务
    public void clickBind(View view) {
        bindService(mIntent, connection, BIND_AUTO_CREATE);
    }

    //获得远端服务中的数据
    public void clickGet(View view) {
        try {
            if (mInter != null) {
                mInter.getMsg();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    //传递数据到远端
    public void clickSend(View view){
        try {
            if(mInter!=null){
                mInter.setData("hahaha");
            }
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //传递对象到服务端
    public void clickSendObj(View view){
        Car car = new Car("宝马x7",100);
        try {
            if(mInter!=null){
               mInter.sendObject(car);
            }
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**开启服务
     * @param view
     */
    public void clickStart(View view){
        //服务只能用显式意图来跳转
        startService(mIntent);
    }
    //停止服务
    public void clickStop(View view){
        stopService(mIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(mIntent);
    }
}
