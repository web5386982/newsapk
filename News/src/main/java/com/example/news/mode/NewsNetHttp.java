package com.example.news.mode;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


/** 主要用于网络的访问操作
 * 利用的是Volley架构
 * Created by Administrator on 2016/12/25.
 */

public class NewsNetHttp {

//JSONObject导包一定要注意：import org.json.JSONObject;不能导错了
    public void netRequestHttpNews(Context context, int methond, String path, JSONObject datas, Response.Listener listener, Response.ErrorListener errorListener){
        //队列的对象就用Volley的静态方法拿，切记，别忘了
        RequestQueue queue= Volley.newRequestQueue(context);
        JsonObjectRequest request=new JsonObjectRequest(methond,path,datas,listener,errorListener);
          queue.add(request);

    }

}
