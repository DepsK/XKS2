package com.dream.xukuan.xk2stu5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * @author XK
 * @date 2018/3/15.
 */
public class ThirdReceiver1 extends BroadcastReceiver{

    private final String TAG = "ThirdReceiver1";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "静态注册的：onReceive: ...收到了广播---600"+intent.getStringExtra("msg"));
    }
}