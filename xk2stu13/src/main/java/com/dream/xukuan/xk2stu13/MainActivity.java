package com.dream.xukuan.xk2stu13;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        String[] data = {"屏幕适配","新控件","协调者布局1","协调者布局2"};
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        Intent intent = new Intent(MainActivity.this,ScreenActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 1:{
                        Intent intent = new Intent(MainActivity.this,NewLayoutActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 2:{
                        Intent intent = new Intent(MainActivity.this,CoordinatorLayoutActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 3:{
                        Intent intent = new Intent(MainActivity.this,Coordinaor2Activity.class);
                        startActivity(intent);
                    }
                    break;
                    default:
                }
            }
        });
    }
}
