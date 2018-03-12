package com.dream.xukuan.stu1;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/3/12.
 */
public class ThirdListFragment extends ListFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        List<String> datas = new ArrayList<>();
        for (int i = 0; i <99 ; i++) {
            datas.add(title+"-------"+i);
        }
        ArrayAdapter<String> adpter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,datas);
        setListAdapter(adpter);
    }
}