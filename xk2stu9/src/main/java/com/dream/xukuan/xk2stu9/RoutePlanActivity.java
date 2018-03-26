package com.dream.xukuan.xk2stu9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.dream.xukuan.xk2stu9.baidapi.DrivingRouteOverlay;
import com.dream.xukuan.xk2stu9.baidapi.MassTransitRouteOverlay;

import java.util.List;

public class RoutePlanActivity extends AppCompatActivity implements OnGetRoutePlanResultListener {

    private static final String TAG = "RoutePlanActivity";
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
        MassTransitRoutePlanOption option = new MassTransitRoutePlanOption()
                .from(startNode)
                .to(endNode);
        planSearch.masstransitSearch(option);
    }

    public void clickDrive(View view){
        PlanNode startNode = PlanNode.withCityNameAndPlaceName("深圳","大学城地铁站");
        PlanNode endNode = PlanNode.withCityNameAndPlaceName("东莞","松山湖");
        DrivingRoutePlanOption option = new DrivingRoutePlanOption()
                .from(startNode)
                .to(endNode);
        planSearch.drivingSearch(option);
    }

    //步行
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    //市内公交
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }
    //当接收到跨城市公交线路规划结果的时候回调
    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
        if(massTransitRouteResult == null || massTransitRouteResult.error != SearchResult.ERRORNO.NO_ERROR ){
            Toast.makeText(RoutePlanActivity.this, "公交路线规划失败！", Toast.LENGTH_SHORT).show();
            return;
        }else if(massTransitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            Toast.makeText(RoutePlanActivity.this, "起点或者终点有歧义", Toast.LENGTH_SHORT).show();
            return;
        }
        MassTransitRouteOverlay overlay = new MassTransitRouteOverlay(mBaiduMap);
        //得到规划结果中的第一种方案的路线
        Log.i(TAG, "onGetMassTransitRouteResult:方案数量： "+massTransitRouteResult.getRouteLines().size());
        MassTransitRouteLine routeLine = massTransitRouteResult.getRouteLines().get(0);
        overlay.setData(routeLine);
        overlay.addToMap();
        overlay.zoomToSpan();
        List<List<MassTransitRouteLine.TransitStep>> newSteps = routeLine.getNewSteps();
        for (int i = 0; i <newSteps.size() ; i++) {
            Log.i(TAG, "onGetMassTransitRouteResult: ..."+i);
            List<MassTransitRouteLine.TransitStep> steps = newSteps.get(i);
            for (int j = 0; j < steps.size() ; j++) {
                String instructions = steps.get(j).getInstructions();
                Log.i(TAG, "onGetMassTransitRouteResult: "+instructions);
            }
        }
    }

    //驾车
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
        if(drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR ){
            Toast.makeText(RoutePlanActivity.this, "驾车路线规划失败！", Toast.LENGTH_SHORT).show();
            return;
        }else if(drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            Toast.makeText(RoutePlanActivity.this, "起点或者终点有歧义", Toast.LENGTH_SHORT).show();
            return;
        }
        DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
        DrivingRouteLine routeLine = drivingRouteResult.getRouteLines().get(0);
        overlay.setData(routeLine);
        overlay.addToMap();
        overlay.zoomToSpan();
        List<DrivingRouteLine.DrivingStep> allStep = routeLine.getAllStep();
        for (int i = 0; i <allStep.size() ; i++) {
            Log.d(TAG, "onGetDrivingRouteResult: ..."+allStep.get(i).getInstructions());
        }
    }

    //室内路线
    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    //骑行
    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }
}
