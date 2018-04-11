package com.dream.xukuan.xk2stu12;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        String[] data = {"下拉刷新","下拉刷新","网页布局","侧滑布局","抽屉布局","导航布局"};
        ListView listView = (ListView) findViewById(R.id.list_item);
        ArrayAdapter adapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        Intent intent = new Intent(context,PullToRefreshActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 1:{
                        Intent intent = new Intent(context,SwipeRefreshActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 2:{
                        Intent intent = new Intent(context,WebViewActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 3:{
                        Intent intent = new Intent(context,SlidePaneActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 4:{
                        Intent intent = new Intent(context,DrawerLayoutActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 5:{
                        Intent intent = new Intent(context,NavigationViewActivity.class);
                        startActivity(intent);
                    }
                    break;
                    default:
                }
            }
        });
    }
}
