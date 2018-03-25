package com.dream.xukuan.xk2stu9;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * @author XK
 * @date 2018/3/23.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
    }
}