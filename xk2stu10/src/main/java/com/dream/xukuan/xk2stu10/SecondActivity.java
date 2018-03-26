package com.dream.xukuan.xk2stu10;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dream.xukuan.xk2stu10.views.PuBuView;

import java.io.IOException;
import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {

    PuBuView puBuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        puBuView = (PuBuView) findViewById(R.id.second_pubu);
        AssetManager manager = getAssets();
        try {
            String[] images = manager.list("images");
            for (int i = 0; i < images.length; i++) {
                InputStream is = manager.open("images/"+images[i]);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                puBuView.addImage(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
