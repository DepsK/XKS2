package com.dream.xukuan.xk2stu14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        List<String> data = new ArrayList<>();
        for (int i = 0; i <50 ; i++) {
            data.add("数据------"+i);
        }
        rc = (RecyclerView) findViewById(R.id.first_recycle);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        GridLayoutManager glm = new GridLayoutManager(this,4);
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        FirstAdapter adapter = new FirstAdapter(this,data);
        llm.setSmoothScrollbarEnabled(true);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(llm);
        rc.setAdapter(adapter);
        rc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
    }
}
