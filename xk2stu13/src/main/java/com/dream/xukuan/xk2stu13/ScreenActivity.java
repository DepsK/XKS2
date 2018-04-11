package com.dream.xukuan.xk2stu13;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.util.Locale;

public class ScreenActivity extends AppCompatActivity {

    private static final String TAG = "ScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        getScreenInfo();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

        }
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        Locale[] locales = Locale.getAvailableLocales();
        for (int i = 0; i < locales.length; i++) {
            Log.d(TAG,locales[i].getDisplayCountry() + "="
                    + locales[i].getCountry() + ""
                    + locales[i].getDisplayLanguage() + "="
                    + locales[i].getLanguage());
        }
    }
    public void getScreenInfo(){
        //获取屏幕信息
        DisplayMetrics dm = new DisplayMetrics();
        StringBuilder sb = new StringBuilder();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //屏幕密度
        float density = dm.density;
        //dpi
        int densityDpi = dm.densityDpi;
        //屏宽
        int widthPixels = dm.widthPixels;
        //屏高
        int heightPixels = dm.heightPixels;
        Configuration con = getResources().getConfiguration();
        int smallestScreenWidthDp = con.smallestScreenWidthDp;
        //字体在屏幕中的密度
        float scaledDensity = dm.scaledDensity;
        sb.append("密度=").append(density+"\n");
        sb.append("dpi=").append(densityDpi+"\n");
        sb.append("屏宽=").append(widthPixels+"\n");
        sb.append("屏高=").append(heightPixels+"\n");
        sb.append("字体密度=").append(scaledDensity+"\n");
        sb.append("最短边=").append(smallestScreenWidthDp);
        Log.d(TAG,sb.toString());
    }
}
