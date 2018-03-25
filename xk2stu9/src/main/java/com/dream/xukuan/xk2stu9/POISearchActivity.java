package com.dream.xukuan.xk2stu9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.dream.xukuan.xk2stu9.baidapi.PoiOverlay;

import java.util.ArrayList;
import java.util.List;

public class POISearchActivity extends AppCompatActivity {

    private static final String TAG = "POISearchActivity";
    TextureMapView mapView;
    BaiduMap mBaiduMap;
    PoiSearch poiSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisearch);
        mapView = (TextureMapView) findViewById(R.id.poi_map_view);
        mBaiduMap = mapView.getMap();
        //1.创建检索对象
        poiSearch = PoiSearch.newInstance();

        //2.注册检索结果监听器
        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            //当获得poi检索结果时回调：发起poi检索后查询到的结果会传回
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                //将检索结果显示在地图：用覆盖物
                if(poiResult==null||poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND){
                    Toast.makeText(POISearchActivity.this, "poi检索失败！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(poiResult.error == SearchResult.ERRORNO.NO_ERROR){
                    //检索成功
                    //添加覆盖物
                    PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
                    //点击标注，显示具体信息，对地图设置标注点击事件监听器
                    mBaiduMap.setOnMarkerClickListener(overlay);
                    //将检索结果传入覆盖物
                    overlay.setData(poiResult);
                    //将检索结果覆盖物加入地图
                    overlay.addToMap();
                    //将地图的中心点调整到poi检索结果的位置，缩放地图以全部显示检索结果
                    overlay.zoomToSpan();
                }
            }
            //searchPoiDetail：当获得poi的详情信息的时候回调：
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                if(poiDetailResult == null||poiDetailResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND){
                    Toast.makeText(POISearchActivity.this, "详情检索失败", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(poiDetailResult.error == SearchResult.ERRORNO.NO_ERROR){
                    poiDetailResult.getPrice();
                    //得到详情页面的url
                    String url = poiDetailResult.getDetailUrl();
                    Log.d(TAG,"onGetPoiDetailResult: --"+url);
                }
            }
            //当获得室内检索结果的时候回调
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });
    }

    public void clickSearch(View view){
        PoiCitySearchOption option = new PoiCitySearchOption()
                .city("深圳")
                .keyword("麦当劳")
                .pageNum(0)
                .pageCapacity(10);
        poiSearch.searchInCity(option);
    }

    private class MyPoiOverlay extends PoiOverlay {
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int i) {
            //得到本页的所有结果的集合
            List<PoiInfo> allPoi = getPoiResult().getAllPoi();
            //得到当前被点击的poi的信息
            PoiInfo poiInfo = allPoi.get(i);
            String address = poiInfo.address;
            String phoneNum = poiInfo.phoneNum;
            Toast.makeText(POISearchActivity.this, "点击了："+address+"---"+phoneNum, Toast.LENGTH_SHORT).show();
            //根据uid发起详情检索：获得更多信息
            String uid = poiInfo.uid;
            PoiDetailSearchOption option = new PoiDetailSearchOption()
                    .poiUid(uid);
            //发起详情检索：检索结果会传回监听器的onGetPoiDetailResult方法
            poiSearch.searchPoiDetail(option);
            return true;
        }
    }
}
