package com.dream.xukuan.xk2stu7;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FirstActivity extends AppCompatActivity {


    private static final String TAG = "FirstActivity";
    Intent mIntent;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: ...."+Integer.toHexString(service.hashCode()));
            //向下转型
            //当服务连接成功回调:参数二就是服务端的onBind方法的返回值
            mBinder = (FirstService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private FirstService.MyBinder mBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mIntent = new Intent(this,FirstService.class);
    }

    public void firstStart(View view){
        startService(mIntent);
    }

    public void firstBind(View view){
        bindService(mIntent,connection,BIND_AUTO_CREATE);
    }

    public void firstGet(View view){
        //调服务中的方法：需要服务对象
        if(mBinder != null){
            FirstService service = mBinder.getServiceInstance();
            Log.i(TAG, "clickGet: "+service.getMsg());
        }
    }

    public void firstSend(View view){
        if(mBinder!=null){
            FirstService service = mBinder.getServiceInstance();
            service.setData("今天下班去KTV,今天打折！");
        }
    }

    public void firstStop(View view){
        stopService(mIntent);
    }

    public void firstUnBind(View view){
        unbindService(connection);

    }
}
