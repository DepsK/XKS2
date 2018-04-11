package com.dream.xukuan.xk2stu12;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/3/28.
 */
public class SlideFragment extends ListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <50 ; i++) {
            list.add(getArguments().getString("title")+"----"+i);
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,list);
        setListAdapter(adapter);
    }
}