package com.example.administrator.newas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
   WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        View view = this.findViewById(R.id.main_web_view);
//        webview=new WebView(this);
//        webview.loadUrl("https://www.taobao.com/");//访问网络
////       webview.loadUrl("file:///android_assets/login.html");//访问本地html
//        WebSettings settings = webview.getSettings();//想要设置webview得先拿到设置得对象
//        settings.setJavaScriptEnabled(true);//这是设置支持JavaScript
//        settings.setLoadWithOverviewMode(true);//设置自适应,网页图片的自适应
//        settings.setUseWideViewPort(true);//设置屏幕自适应,整个网页适应屏幕
////        WebChromeClient,这个类是用来处理网络加载进度及对话框处理等操作
////        WebViewClient  处理网页加载事件的类
          String path=null;



    }
}
