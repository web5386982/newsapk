package com.example.news;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.Fragments.LeftSlidMenuFragment;
import com.example.news.Fragments.MainListFragment;
import com.example.news.Fragments.RightMenuFragment;
import com.example.news.unti.Constance;
import com.example.news.newspresenter.NewsNetPresenter;
import com.example.news.entity.News;
import com.example.news.entity.NewsDetail;
import com.example.news.unti.SharePreUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import edu.zx.slidingmenu.SlidingMenu;

public class MainActivity extends AppCompatActivity {
    private static  MainActivity mainActivity;
    public MainListFragment mainListFragment;
    //网络访问mvp模式中的P对象;
    public NewsNetPresenter netPresenter;
    //POST方式访问网络时需要传输的数据;
    protected JSONObject mdatas;
    public  SlidingMenu slidmenu;
    //获取新闻的内容存储的对象;
    public News<List<NewsDetail>> newswholeinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         mainListFragment = new MainListFragment();
         initSlidingMenu();//左滑菜单
         initNetVisit();//网络访问

    }




    /**
     * datas 是传输给服务器的数据，可以为空,这里先让他为空，稍后再补；
     * 这个方法是网络访问初始化
     */

    public void initNetVisit() {
        netPresenter = new NewsNetPresenter(this, Request.Method.GET, Constance.PATH_NET_NEWS, mdatas, listener, errorListener);
        netPresenter.netRequest();//千万不要忘记调用访问网络的这个方法；

    }

    Response.Listener listener = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            //这里网络访问就结束了，服务器返回的数据传到这。
            //对返回结果进行解析；
            Gson gson = new Gson();
            TypeToken type = new TypeToken<News<List<NewsDetail>>>(){};//他的类型就是下边返回值类型
            //这个数据类型和结构有关，一定要看着原文分析
            //如果调用一个方法，报错说是不公开的，那就给这个方法后边加{}；这表示用匿名内部类new对象；
            //用构造方法new对象就是没有{}；这两个很相似不要忽略了；
             newswholeinfo= gson.fromJson(object.toString(), type.getType());
            //在这结束对话框
            netPresenter.cancelDialog();
            MainActivity.this.initMainlistFragment();//跳转到内容页,也就是MainlistFragment(),显示的视图页;
            //  将listview显示在fragment中
        }
    };
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            //执行到这说明访问出错，出错时服务器返回的信息；
        }
    };

    /**
     * 左右滑动菜单的设置
     */
    public void initSlidingMenu() {
        slidmenu = new SlidingMenu(this);
        slidmenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);//将Acitivity与Slinding关联
        slidmenu.setMenu(R.layout.onteher);
        slidmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidmenu.setBehindOffset(280);
        this.getSupportFragmentManager().beginTransaction().add(R.id.onther_layout, new LeftSlidMenuFragment()).commit();
        //绑定这个视图如果出错，那肯定是导包错了,肯定是Frangment的包错了。前提是，ID没错!!!!!!,ID绑定的视图，不能和后边new的这个绑定的视图一样。。一样就把数据加载不上去了。
        slidmenu.setSecondaryMenu(R.layout.rightmenu_layout);
        slidmenu.setBehindOffset(100);
        this.getSupportFragmentManager().beginTransaction().add(R.id.right_menu_sild_context,  new RightMenuFragment()).commit();
        slidmenu.setMode(SlidingMenu.LEFT_RIGHT);
        //这是设置左右滑动的，默认是只能左边滑，SlidingMenu.LEFT_RIGHT;这是左右两边都能滑动

    }

    /**
     * 返回键的设置
     */
    @Override
    public void onBackPressed() {//这个方法是设置返回键的;
        if(slidmenu.isMenuShowing()){
            slidmenu.showContent();
        }else{
//            super.onBackPressed();
        }
    }

    /**
     * 对MainlistFragment()对象进行封装
     */
    public void initMainlistFragment(){
        this.getSupportFragmentManager().beginTransaction().add(R.id.activity_main,mainListFragment).commit();
    }
    public static MainActivity instanceActivity() {
        if (mainActivity == null) {
            synchronized (MainActivity.class) {
                if (mainActivity == null) {
                    mainActivity = new MainActivity();
                }
            }
        }
        return mainActivity;
    }

}
