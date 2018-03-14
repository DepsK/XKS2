package com.dream.xukuan.xk2stu4;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/3/14.
 */
public class ThirdListFragment extends ListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        List<String> datas = new ArrayList<>();
        String title = getArguments().getString("title");
        for (int i = 0; i < 100 ; i++) {
            datas.add(title+"-------"+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,datas);
        setListAdapter(adapter);
    }
}