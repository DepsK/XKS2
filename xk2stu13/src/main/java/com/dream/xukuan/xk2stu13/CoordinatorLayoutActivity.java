package com.dream.xukuan.xk2stu13;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LayoutInflater inflater;
    List<String> data = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.coor_toolbar);
        toolbar.setTitle("这是测试标题");
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        inflater = LayoutInflater.from(this);
        for (int i = 0; i < 100; i++) {
            data.add("这是第" + i + "条数据");
        }
        recyclerView = (RecyclerView) findViewById(R.id.coor_recycle);
        adapter = new MyAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    //获得状态栏的高度
    public static int getStatusHeight(Context context){
        int statusHeight = 0;
        try {
            Class clazz= Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.setText(android.R.id.text1,data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            View view;
            public MyViewHolder(View itemView) {
                super(itemView);
                view = itemView;
            }
            public View getViewById(int id){
                return view.findViewById(id);
            }
            public void setText(int id,String text){
                TextView textView = (TextView) getViewById(id);
                textView.setText(text);
            }
        }
    }


}
