package com.dream.xukuan.stu1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * @author XK
 * @date 2018/3/12.
 */
public class ThirdMessageFragment extends Fragment {

    EditText editText;
    String msg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_third_messagefragment,null);
        editText= (EditText) view.findViewById(R.id.third_message_edit);
        if (!TextUtils.isEmpty(msg)){
            editText.setText(msg);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //保存用户数据
        msg = editText.getText().toString();
    }
}