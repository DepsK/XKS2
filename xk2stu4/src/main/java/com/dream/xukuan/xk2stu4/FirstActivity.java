package com.dream.xukuan.xk2stu4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "FirstActivity";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        toolbar = (Toolbar) findViewById(R.id.first_toolbar);
        toolbar.setTitle("标题");
        toolbar.setSubtitle("子标题");
        //将原来的ActionBar设置成toolbar
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        //设置返回图标：如果自定义了，就不需要setDisplayHomeAsUpEnabled了
        toolbar.setNavigationIcon(R.mipmap.header_btn_more_nor);
        //显示默认的返回图标
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.first_item_pay:
                        Toast.makeText(FirstActivity.this, "自己的监听器：开始支付", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.first_item_add:
                        Toast.makeText(FirstActivity.this, "自己的监听器：开始添加好友", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                return true;
            }
        });
        //设置自己的返回图标点击事件监听器
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "toolbar自己的监听：点击了返回按钮！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.first_search_item).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "onQueryTextChange: ..."+newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.first_item_pay:{
                Toast.makeText(FirstActivity.this, "开始支付", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.first_item_sao:{
                Toast.makeText(FirstActivity.this, "开始扫一扫", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.first_item_add:{
                Toast.makeText(FirstActivity.this, "添加好友", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.first_item_msg:{
                Toast.makeText(FirstActivity.this, "用户信息界面", Toast.LENGTH_SHORT).show();

            }
            break;
            case android.R.id.home:{
                Toast.makeText(FirstActivity.this, "点击了返回键", Toast.LENGTH_SHORT).show();
            }
            break;
            default:
        }
        return true;
    }
}
