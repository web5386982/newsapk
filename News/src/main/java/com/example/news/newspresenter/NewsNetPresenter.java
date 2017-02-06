package com.example.news.newspresenter;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Response;
import com.example.news.netview.NewsNetView;
import com.example.news.mode.NewsNetHttp;

import org.json.JSONObject;

/**   MVP架构的P 部分
 * 主要是作为 View和mode的中间桥梁
 * Created by Administrator on 2016/12/25.
 */

public class NewsNetPresenter implements NewsNetView{

    Context mcontext;
    JSONObject mdatas;
    Response.Listener mlistener;
    Response.ErrorListener merrorListener;
    int methond;
    String mpath;

     public NewsNetPresenter(Context context,int methond,String path, JSONObject datas, Response.Listener listener, Response.ErrorListener errorListener){
         //这些参数可以从构造方法传，也可以从被调用的方法传，没什么规定，传过来就行
         this.mpath=path;
         this.methond=methond;
         this.mcontext=context;
         this.mdatas=datas;
         this.mlistener=listener;
         this.merrorListener=errorListener;
    }
      public void netRequest(){
          //显示进度条的地方
          showDialog();
          NewsNetHttp netHttp=new NewsNetHttp();
          netHttp.netRequestHttpNews(mcontext, methond,mpath,mdatas,mlistener,merrorListener);
          //请求方式在Volley框架中已经定义好了，直接调用就好；路径需要在实体类中定义常量；
}

    /**
     * 在这里实现接口是为了显示对话框
     * 设置对话框
     * 关闭对话框是在Activity中，数据加载完成之后；
     */
    ProgressDialog prodialog;
    @Override
    public void showDialog() {
         prodialog=ProgressDialog.show(mcontext,"数据正在加载","请稍等。。。");
    }

    @Override
    public void cancelDialog() {
        prodialog.cancel();
    }
}
