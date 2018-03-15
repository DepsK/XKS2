package com.dream.xukuan.xk2stu5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author XK
 * @date 2018/3/15.
 */
public class FifthBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //开启activity
        Intent mIntent = new Intent(context,FifthActivity.class);
        //必须加：不加自启动的时候会抛异常
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d("print", "onReceive: ...开机了！");
        context.startActivity(mIntent);
    }
}