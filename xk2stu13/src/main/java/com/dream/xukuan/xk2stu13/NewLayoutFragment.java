package com.dream.xukuan.xk2stu13;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author XK
 * @date 2018/3/29.
 */
public class NewLayoutFragment extends Fragment{

    static int[] imgs = {
            R.mipmap.img0,
            R.mipmap.img1,
            R.mipmap.img2,
            R.mipmap.img3,
            R.mipmap.img4,
            R.mipmap.img5,
            R.mipmap.img6
    };

    public static int getImgId(int position){
        return imgs[position];
    }

    public static NewLayoutFragment getInstance(int position){
        NewLayoutFragment fragment = new NewLayoutFragment();
        Bundle bundle =new Bundle();
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_new_layout_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        ImageView imageView = (ImageView) view.findViewById(R.id.new__frag_image);
        imageView.setImageResource(imgs[getArguments().getInt("position")]);
    }
}