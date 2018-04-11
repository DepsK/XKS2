package com.dream.xukuan.xk2stu11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dream.xukuan.xk2stu11.views.MsgView;

public class HWActivity extends AppCompatActivity {

    MsgView msgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw);
        msgView = (MsgView) findViewById(R.id.msgview);
        msgView.setNum(100);
    }
}
