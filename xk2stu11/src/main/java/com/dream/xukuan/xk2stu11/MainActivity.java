package com.dream.xukuan.xk2stu11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        String[] data ={"画笔和画布","五环","进度","波纹反馈的TextView","开关按钮","圆形头像","作业"};
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        Intent intent = new Intent(MainActivity.this,CanvasTestActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 1:{
                        Intent intent = new Intent(MainActivity.this,CircleActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 2:{
                        Intent intent = new Intent(MainActivity.this,ProgressActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 3:{
                        Intent intent = new Intent(MainActivity.this,TextViewActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 4:{
                        Intent intent = new Intent(MainActivity.this,SlideViewActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 5:{
                        Intent intent = new Intent(MainActivity.this,CircleImageActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 6:{
                        Intent intent = new Intent(MainActivity.this,HWActivity.class);
                        startActivity(intent);
                    }
                    break;
                    default:
                }
            }
        });
    }
}
