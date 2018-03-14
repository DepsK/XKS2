package com.dream.xukuan.xk2stu4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/3/14.
 */
public class ThirdRecyclerFragment extends Fragment {

    private List<String> datas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_third_fragment,null);
        initData();
        RecyclerView rl = (RecyclerView) view.findViewById(R.id.third_recyclerview);
        LinearLayoutManager ll = new LinearLayoutManager(getContext());
        rl.setLayoutManager(ll);
        ThirdRecyclerAdapter adapter = new ThirdRecyclerAdapter();
        rl.setAdapter(adapter);
        return view;
    }

    private void initData() {
        datas = new ArrayList<>();
        String title = getArguments().getString("title");
        for (int i = 0; i <100 ; i++) {
            datas.add(title+"-----"+i);
        }
    }

    private  class ThirdRecyclerAdapter extends RecyclerView.Adapter<ThirdRecyclerAdapter.ThirdViewHolder> {

        @Override
        public ThirdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1,null);
            ThirdViewHolder holder = new ThirdViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(ThirdViewHolder holder, int position) {
            holder.textView.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class ThirdViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            public ThirdViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }


}