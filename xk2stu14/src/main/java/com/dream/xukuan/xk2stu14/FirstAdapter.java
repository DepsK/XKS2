package com.dream.xukuan.xk2stu14;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * @author XK
 * @date 2018/4/2.
 */
public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.MyHolder> {


    private  List<String> data;
    private  Context context;

    public FirstAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.setText(android.R.id.text1,data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        View view;
        public MyHolder(View itemView) {
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