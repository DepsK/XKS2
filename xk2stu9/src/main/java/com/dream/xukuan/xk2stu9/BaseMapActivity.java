package com.dream.xukuan.xk2stu9;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;

public class BaseMapActivity extends AppCompatActivity {

    TextureMapView mapView;
    private BaiduMap mBaiduMap;
    private Marker mMarkA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_map);
        mapView = (TextureMapView) findViewById(R.id.base_map_view);
        mBaiduMap = mapView.getMap();

        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //修改地图的中心点
        LatLng latLng = new LatLng(22.65856,113.916787);
        MapStatusUpdate center = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.setMapStatus(center);
        //放大地图的等级
        MapStatusUpdate zoom = MapStatusUpdateFactory.zoomTo(15);
        mBaiduMap.setMapStatus(zoom);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    //点击添加标注覆盖物
    public void clickMark(View view){
        //创建坐标对象---覆盖物出现的位置
        final LatLng latLng = new LatLng(22.65856,113.916787);
        //图片描述对象---覆盖物的图标
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.icon_marka);
        //OverlayOptions对象只是覆盖物的配置信息
        OverlayOptions markOpts = new MarkerOptions()
                .icon(icon)
                .position(latLng);
        //在地图中按照配置信息创建覆盖物并返回
        mMarkA = (Marker) mBaiduMap.addOverlay(markOpts);
        //点击标注，弹出弹出窗
        //对地图注册覆盖物的点击监听器

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //当覆盖物被点击的时候回调：参数就是被点击的覆盖物的对象
                if(mMarkA !=null){
                    Button button = new Button(BaseMapActivity.this);
                    button.setText("双翼欢迎你！");
                    button.setPadding(10,5,10,5);
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(button);
                    LatLng lat = mMarkA.getPosition();
                    InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick() {
                            mBaiduMap.hideInfoWindow();
                        }
                    };
                    InfoWindow infoWindow = new InfoWindow(bitmap,lat,0,listener);
                    mBaiduMap.showInfoWindow(infoWindow);
                    return true;
                }
                return false;
            }
        });
    }

    public void clickText(View view){
        LatLng pos = new LatLng(22.64598,113.947719);
        OverlayOptions textOpts = new TextOptions()
                .text("到此一游！")
                .fontColor(Color.RED)
                .fontSize(40)
                .rotate(45)
                .bgColor(Color.BLUE)
                .position(pos);
        mBaiduMap.addOverlay(textOpts);
    }

    //点击出现圆形覆盖物
    public void clickCircle(View view){
        LatLng pos = new LatLng(22.60598,113.977719);
        OverlayOptions circleOpts = new CircleOptions()
                .center(pos)//设置中心坐标
                .fillColor(0x33ff0000)
                .radius(500)
                .stroke(new Stroke(5,0xffff0000));
        mBaiduMap.addOverlay(circleOpts);
    }

    public void clickPop(View view){
        Button button = new Button(this);
        button.setBackgroundResource(R.mipmap.popup);
        button.setText("这里是双翼，欢迎光临！");
        button.setPadding(10,5,10,5);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(button);
        LatLng pos = new LatLng(22.586594,//纬度
                113.968772 );//经度;
        InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick() {
                Toast.makeText(BaseMapActivity.this, "点击了弹出覆盖物！", Toast.LENGTH_SHORT).show();
                mBaiduMap.hideInfoWindow();
            }
        };

        InfoWindow infoWindow = new InfoWindow(bitmap,pos,0,listener);
        mBaiduMap.showInfoWindow(infoWindow);
    }

    public void clickNormal(View view){
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }

    public void clickSatellite(View view){
        //卫星图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
    }

    public void clickNone(View view){
        //空白地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
    }

    public void clickTraffic(View view){
        //开启实时交通图
        mBaiduMap.setTrafficEnabled(true);
    }

    public void clickHeat(View view){
        //开启城市热力图
        mBaiduMap.setBaiduHeatMapEnabled(true);
    }

}
