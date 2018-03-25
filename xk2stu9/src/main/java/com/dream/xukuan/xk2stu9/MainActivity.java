package com.dream.xukuan.xk2stu9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        String[] datas = {"基础地图和覆盖物","POI检索","路线规划","定位"};
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:{
                Intent intent1 = new Intent(this,BaseMapActivity.class);
                startActivity(intent1);
            }
            break;
            case 1:{
                Intent intent2 = new Intent(this,POISearchActivity.class);
                startActivity(intent2);
            }
            break;
            case 2:{
                Intent intent3 = new Intent(this,RoutePlanActivity.class);
                startActivity(intent3);
            }
            break;
            case 3:{
                Intent intent4 = new Intent(this,LocationActivity.class);
                startActivity(intent4);
            }
            break;
            default:
        }
    }
}
