package com.dream.xukuan.xk2stu2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
    }
    public void click(View view){
        switch (view.getId()){
            case R.id.activity_first:{
                Intent intent = new Intent(context,FirstActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.activity_second:{
                Intent intent = new Intent(context,SecondActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.activity_third:{
                Intent intent = new Intent(context,ThirdActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.activity_fourth:{
                Intent intent = new Intent(context,FourthActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.activity_hw:{
                Intent intent = new Intent(context,HWActivity.class);
                startActivity(intent);
            }
            break;
            default:

        }
    }
}
