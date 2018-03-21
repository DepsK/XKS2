package com.dream.xukuan.xk2stu6;

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
            case R.id.first:{
                Intent intent = new Intent(context,FirstActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.second:{
                Intent intent = new Intent(context,SecondActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.third:{
                Intent intent = new Intent(context,ThirdActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.fourth:{
                Intent intent = new Intent(context,FourthActivity.class);
                startActivity(intent);
            }
            break;
            default:
        }
    }
}
