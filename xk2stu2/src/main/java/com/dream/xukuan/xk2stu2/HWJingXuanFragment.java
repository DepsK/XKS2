package com.dream.xukuan.xk2stu2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class HWJingXuanFragment extends Fragment {

    String[] titles = {"推荐", "饮食", "问答", "励志", "技巧"};
    String[] urls = {HWContant.URLTUIJIAN, HWContant.URLYINSHI, HWContant.URLWENDA, HWContant.URLLIZHI, HWContant.URLJIQIAO};
    TabLayout tabLayout;
    ViewPager viewPager;

    Fragment[] fragments;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hw_jingxuan_layout,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        //初始化控件
        tabLayout = (TabLayout) view.findViewById(R.id.hw_jingxuan_tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.hw_jingxuan_viewPager);
        //初始化数据
        initData();
        //设置adapter
        HWHttpUtils.loadJson(getActivity(), new ThirdHttpUtils.OnLoadCompletedListener() {
            @Override
            public void OnLoadCompleted(String json) {
                List<HWContentBean.InfoEntity> entityList = parseJson(json);
                setAdapter();
            }
        });

    }

    private void setAdapter() {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(Color.DKGRAY, Color.BLUE);
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
        tabLayout.setupWithViewPager(viewPager);
    }

    private List<HWContentBean.InfoEntity> parseJson(String json) {
        List<HWContentBean.InfoEntity> list = new ArrayList<>();
        JSONObject object = null;
        try {
            object = new JSONObject(json);
            JSONArray array = object.getJSONArray("infos");
            for (int i = 0; i < array.length(); i++) {
                JSONObject contentJson = array.getJSONObject(i);
                HWContentBean.InfoEntity bean = new HWContentBean.InfoEntity();
                bean.setTitle(contentJson.optString("title"));
                bean.setContent(contentJson.optString("content"));
                bean.setPhoto(contentJson.optString("photo"));
                bean.setCommentCount(contentJson.optInt("commentCount")+"");
                bean.setIpc(contentJson.optInt("ipc")+"");
                bean.setIfc(contentJson.optInt("ifc")+"");
                list.add(bean);
            }
            Log.d("TAG", "解析成功：" + list.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    private void initData() {
        fragments = new Fragment[titles.length];
        for (int i = 0; i < titles.length; i++) {
            fragments[i] = new HWContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url",urls[i]);
            fragments[i].setArguments(bundle);
        }
    }
}