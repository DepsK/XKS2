package com.dream.xukuan.xk2stu9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

public class RoutePlanActivity extends AppCompatActivity implements OnGetRoutePlanResultListener {

    TextureMapView mapView;
    BaiduMap mBaiduMap;
    RoutePlanSearch planSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_plan);
        mapView = (TextureMapView) findViewById(R.id.route_map_view);
        mBaiduMap = mapView.getMap();
        //1.得到线路规划实例
        planSearch = RoutePlanSearch.newInstance();
        //2.注册规划结果监听器
        planSearch.setOnGetRoutePlanResultListener(this);
    }

    public void clickTraffic(View view){
        PlanNode startNode = PlanNode.withCityNameAndPlaceName("深圳","大学城地铁站");
        PlanNode endNode = PlanNode.withCityNameAndPlaceName("东莞","松山湖");
//        MassTransitRouteLine
    }

    public void clickDrive(View view){

    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }
}
