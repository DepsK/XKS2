package com.dream.xukuan.xk2stu12;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class PullToRefreshActivity extends AppCompatActivity {

    PtrClassicFrameLayout refresh;
    ImageView imageView;
    String[] urlStrings = {"http://img5.duitang.com/uploads/item/201410/04/20141004141720_vr23M.jpeg",
            "http://img1.imgtn.bdimg.com/it/u=3724834980,466973059&fm=11&gp=0.jpg",
            "http://i2.hexunimg.cn/2015-04-07/174742774.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2525689499,526305536&fm=21&gp=0.jpg"};
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        refresh = (PtrClassicFrameLayout) findViewById(R.id.pull_view);
        imageView = (ImageView) findViewById(R.id.pull_image_view);
//        initStoreHouseHeader();
        initMDHeader();
        //设置事件处理回调函数
        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //当用户释放控件，开始刷新的时候回调，一般在这重新加载数据
                startLoadImage();
                index = (index + 1) % urlStrings.length;
            }
        });
    }

    //设置Md风格的头部
    private void initMDHeader() {
        MaterialHeader header = new MaterialHeader(this);
        header.setColorSchemeColors(new int[]{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN});
        header.setPtrFrameLayout(refresh);
        //设置头部处理的Handler
        refresh.setHeaderView(header);
        refresh.addPtrUIHandler(header);
    }

    //设置闪光字风格的头部
    private void initStoreHouseHeader() {
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.initWithString("load...");
        header.setTextColor(Color.BLUE);
        header.setBackgroundColor(Color.GRAY);
        refresh.setHeaderView(header);
        refresh.addPtrUIHandler(header);
    }

    private void startLoadImage() {
        MyImageTask task = new MyImageTask(new MyImageTask.OnLoadCompletedListener() {
            @Override
            public void onLoadCompleted(Bitmap bitmap) {
                if(bitmap!=null){
                    imageView.setImageBitmap(bitmap);
                }else {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                //让刷新头部隐藏
                refresh.refreshComplete();
            }
        });
        task.execute(urlStrings[index]);
    }
}
