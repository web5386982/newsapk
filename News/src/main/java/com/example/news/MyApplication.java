package com.example.news;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**这个类在应用程序创建的时候加载,比Activity更早加载
 * 这个必须在Application标签中添加name属性,名称为 MyApplication;
 * Created by Administrator on 2017/1/5 0005.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(getApplicationContext());
        //初始化完成,getApplicationContext()应该用程序的上下文对象

    }
}
