package com.dream.xukuan.xk2stu12;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;

public class WebViewActivity extends AppCompatActivity {


    WebView webView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.web_view);
        toolbar = (Toolbar) findViewById(R.id.web_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置点击返回按钮，返回上一次浏览的网页
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoBack()){
                    webView.goBack();
                }else {
                    finish();
                }
            }
        });
        //返回适合手机端浏览的页面：支持JS
        webView.getSettings().setJavaScriptEnabled(true);
        //默认情况下：webView加载的网页会用默认的浏览打开，如果需要用自己的应用打开，需要设置WebClient
        webView.setWebViewClient(new WebViewClient(){
            ProgressDialog dialog;
            //页面开始加载的时候回调
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view,url,favicon);
                toolbar.setTitle("正在加载。。。");
                if(dialog ==null){
                    dialog = new ProgressDialog(WebViewActivity.this);
                    dialog.setTitle("正在加载");
                    dialog.setMessage("请稍候！");
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getSupportActionBar().setTitle(view.getTitle());
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
            }
            //将网页加载到自己应用的WebView中

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }



        });
        webView.loadUrl("http://www.baidu.com");

    }
}
