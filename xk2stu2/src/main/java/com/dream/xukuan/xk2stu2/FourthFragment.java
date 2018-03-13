package com.dream.xukuan.xk2stu2;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class FourthFragment extends ListFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            datas.add(url+"-------"+i);
        }
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,datas);
        setListAdapter(adapter);
    }
}