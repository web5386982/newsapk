package com.example.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.entity.NewsChild;
import com.example.news.entity.NewsGroup;
import com.example.news.entity.NewsSort;
import com.example.news.newspresenter.NewsNetPresenter;
import com.example.news.unti.Constance;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsSortActivity extends AppCompatActivity {
    @Bind(R.id.news_sort_enable_lst)
    ExpandableListView enablelistview;
    List<NewsGroup> groupnames;
    List<List<NewsChild>> chilenames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_sort);
        ButterKnife.bind(this);
        initNetSort();
//        initdata(newssort);//不在这调用,在数据解析之后调用;
    }

    private void initNetSort() {
        NewsNetPresenter newsNetPresenter = new NewsNetPresenter(this, Request.Method.GET, Constance.PATH_NEWS_SORT, null, listener, errorlistener);
        newsNetPresenter.netRequest();
    }

    /**
     * 初始化数据
     */
    private void initdata(NewsSort newssort) {

        //访问之后传到这
        groupnames=new ArrayList<NewsGroup>();

        chilenames=new ArrayList<List<NewsChild>>();
    }
    Response.Listener listener=new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            Log.e("object", "onResponse: "+object.toString() );
        }
    };
    Response.ErrorListener errorlistener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(NewsSortActivity.this, "", Toast.LENGTH_SHORT).show();

        }
    };
}
