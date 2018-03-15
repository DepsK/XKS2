package com.dream.xukuan.xk2stu5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * @author XK
 * @date 2018/3/15.
 */
public class FourthReceiver extends BroadcastReceiver {

    private static final String TAG = "FourthReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive----------");
    }
}