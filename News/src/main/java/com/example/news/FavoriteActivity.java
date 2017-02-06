package com.example.news;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.example.news.db.Sqldatebase;
import com.example.news.entity.NewsDetail;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**这个类的内容主要设置的是,点击主界面自条目之后,
 * 跳转到网页是的收藏界面,不是左菜单收藏对应的界面
 * Created by Administrator on 2016/12/30.
 */

public class FavoriteActivity extends BaseActivity{

    @Bind(R.id.web_view) WebView mwebView;

    int position;
    MainActivity mainActivity;
    List<NewsDetail> datas;//从mainlistfragment类中传过来的数据
    //我需要在被收藏页拿到这个数据,添加给适配器就行了

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foavority);
       ButterKnife.bind(this);
        mainActivity = MainActivity.instanceActivity();
        initNetData();

}

    /**
     * mwebView的初始化设置
     */
    private void initNetData() {
       //WebView是个视图,必须在布局中加标签,然后绑定
        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        String link = bundle.getString("link");
        Log.e("link", "initNetData: "+link );
        position = bundle.getInt("position");
        datas = (List<NewsDetail>) bundle.getSerializable("newsdetail");//List<object>在页面跳转时的传递
        setWebView(link);
    }

    /**
     * 设置WebView
     * @param link
     */
    private void setWebView(String link) {
        WebSettings settings = mwebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.supportZoom();//支持手动缩放
        settings.setLoadWithOverviewMode(true);
        mwebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mwebView.loadUrl(url);
                return true;//返回True意思是要用自己设置的屏幕大小
            }
        });
        mwebView.loadUrl(link);//这里需要link数据;
    }

    /**
     * 点击收藏的按钮
     */
    @OnClick(R.id.up_btn_right_img)
    public void rightclick(View view){
      //设置下边出来的框,就加入收藏的那个
        Button btn = setPopuWindow(view);
        btnOnclick(btn);
    }

    /**
     * 设置PopuWindow
     * @param view
     * @return
     */
    @NonNull
    private Button setPopuWindow(View view) {
        Button btn=new Button(this);
        btn.setText("加入收藏");
        btn.setHeight(60);
        btn.setWidth(100);
        PopupWindow popupWindow = new PopupWindow(btn,100,60);//btn窗口上要显示的视图
        popupWindow.setFocusable(true);//设置点自身没事,点别的地方隐藏,和下面一句同时使用
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.showAsDropDown(view); //这个view表示点谁放在谁的下边
        return btn;
    }

    /**
     * 收藏按钮的点击事件
     * @param btn
     */
    private void btnOnclick(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//在页面跳转时就已经拿到了数据datas
                NewsDetail newsDetail = datas.get(position - 1);//点击的是0.拿到的数据是1的.所以要position-1
                    //因为自条目下标是从1开始.数据是从0开始的
                Sqldatebase database = Sqldatebase.getdatabase(FavoriteActivity.this);
                //在运行一次,不出值就换掉List中的对象
                if(!database.searchnid(newsDetail.getNid())){//返回值结果为Boolean
                NewsDetail newsDetail1 = new NewsDetail(newsDetail.getSummary(), newsDetail.getStamp(), newsDetail.getLink(), newsDetail.getIcon(), newsDetail.getTitle(), newsDetail.getNid());
                database.addnews(newsDetail1);
                Toast.makeText(FavoriteActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(FavoriteActivity.this, "此新闻已收藏,请在收藏界面点击查看", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    /**
     * 返回主界面
     * @param view
     */


    @OnClick((R.id.up_home_left_img))
    public void leftimgclick(View view){
       toActivity(MainActivity.class);
    }
    /**
     *跟帖的点击事件
     */
    @OnClick(R.id.commit_acount_btn)
    public void commitclick(View view){
        Intent intent=new Intent(FavoriteActivity.this,CommitActivity.class);
        Bundle bundle = new Bundle();
        NewsDetail newsDetail = datas.get(position);
        bundle.putString("stamp",newsDetail.getStamp());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
