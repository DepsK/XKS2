package com.dream.xukuan.xk2stu5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author XK
 * @date 2018/3/15.
 */
public class ThirdReceiver2 extends BroadcastReceiver{

    private static final String TAG = "ThirdReceiver2";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "动态注册：onReceive: ....+收到广播---200"+intent.getStringExtra("msg"));
        String msg = getResultData();
        if (msg!=null){

            Log.d(TAG,"onReceive: "+msg+":"+getResultCode()+":"+getResultExtras(true).getString("msg"));
        }
    }
}