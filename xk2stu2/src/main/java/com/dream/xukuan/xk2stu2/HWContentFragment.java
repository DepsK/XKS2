package com.dream.xukuan.xk2stu2;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class HWContentFragment extends ListFragment {

    List<HWContentBean.InfoEntity> entityList = new ArrayList<>();
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        HWJsonTask task = new HWJsonTask(new HWJsonTask.CallBack() {
            @Override
            public void getData(List<HWContentBean.InfoEntity> result) {
                entityList.addAll(result);
                HWAdapter adapter = new HWAdapter(getContext(),entityList);
                setListAdapter(adapter);
            }
        });
        task.execute(url);
    }
}