package com.dream.xukuan.xk2stu14;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

public class MediaPlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        mediaPlayer = new MediaPlayer();
        surfaceView = (SurfaceView) findViewById(R.id.media_surface);
        holder = surfaceView.getHolder();
        holder.addCallback(this);

        //播放完成监听
       mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
                mediaPlayer.reset();

               //准备播放
               try {
                   Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"
                           + R.raw.wildlife);
                   mediaPlayer.setDataSource(MediaPlayerActivity.this,uri);
                   mediaPlayer.prepare();
               } catch (IOException e) {

               }
           }
       });
    }


    public void play(View view){

    }

    public void stop(View view){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer.setDisplay(holder);
        //设置播放器的数据源
        try {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.wildlife);
            mediaPlayer.setDataSource(MediaPlayerActivity.this,uri);
            if(mediaPlayer!=null){
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //设置视频的大小===============
                        //得到视频的大小
                        float videoWidth = mediaPlayer.getVideoWidth();
                        float videoHeight = mediaPlayer.getVideoHeight();
                        //得到SurfaceView的布局属性
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) surfaceView.getLayoutParams();
                        params.height = (int) (surfaceView.getMeasuredWidth()*videoHeight/videoWidth);
                        surfaceView.setLayoutParams(params);
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.prepare();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
