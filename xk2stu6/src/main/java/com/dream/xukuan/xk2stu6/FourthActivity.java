package com.dream.xukuan.xk2stu6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FourthActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    MyProgressReceiver receiver;
    LocalBroadcastManager manager;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        seekBar = (SeekBar) findViewById(R.id.fourth_seek_bar);
        textView = (TextView) findViewById(R.id.fourth_time_tv);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setTimeTv(progress,seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTouch = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //定位播放位置
                int seek = seekBar.getProgress();
                Intent seekIntent = new Intent("seek.broad");
                seekIntent.putExtra("seek",seek);
                manager.sendBroadcast(seekIntent);
                isTouch = false;
            }
        });
        intent = new Intent(this,FourthService.class);
        manager = LocalBroadcastManager.getInstance(this);
        receiver = new MyProgressReceiver();
        manager.registerReceiver(receiver,new IntentFilter("progress.broad"));
    }

    boolean isTouch = false;

    private void setTimeTv(int currentPosition, int duration) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        String currentTime = format.format(new Date(currentPosition));
        String totalTime = format.format(new Date(duration));
        textView.setText(currentTime+"/"+totalTime);
    }

    public void clickStart(View view){
        startService(intent);
    }

    public void clickStop(View view){
        stopService(intent);
        setMediaProgress(0,0);
    }

    private void setMediaProgress(int currentPosition,  int duration)  {
        seekBar.setMax(duration);
        seekBar.setProgress(currentPosition);
        setTimeTv(currentPosition,duration);
    }

    private class MyProgressReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int currentPosition = intent.getIntExtra("currentPosition", 0);
            int duration = intent.getIntExtra("duration", 0);
            if(!isTouch){
                setMediaProgress(currentPosition,duration);
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver);
    }
}
