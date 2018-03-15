package com.dream.xukuan.xk2stu5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author XK
 * @date 2018/3/15.
 */
public class FifthSystemReceiver extends BroadcastReceiver {

    private static final String TAG = "FifthSystemReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
            parseSms(intent);
        }else if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            Log.w(TAG, "onReceive: 有外拨电话:" + intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER));
        }else if (action.equals("android.intent.action.PHONE_STATE")){
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //得到电话状态
            int callState = manager.getCallState();
            if(callState == TelephonyManager.CALL_STATE_IDLE){
                Log.w(TAG, "onReceive: 电话状态改变:静止" +intent.getExtras().getString("incoming_number"));
            }else if (callState == TelephonyManager.CALL_STATE_OFFHOOK){
                Log.w(TAG, "onReceive: 电话状态改变:OFFHOOK摘机" +intent.getExtras().getString("incoming_number") );
            }else if(callState == TelephonyManager.CALL_STATE_RINGING){
                Log.w(TAG,"onReceive:响铃"+intent.getExtras().getString("incoming_number"));
            }
        }
    }

    private void parseSms(Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int i = 0; i < messages.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        }
        String address = messages[0].getOriginatingAddress();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = format.format(new Date(messages[0].getTimestampMillis()));
        String body = messages[0].getMessageBody();
        Log.i(TAG, "onReceive: " + address + "--->" + body + "--->" + time);

    }
}