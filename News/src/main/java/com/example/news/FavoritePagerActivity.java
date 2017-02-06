package com.example.news;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.news.Adapter.FavoriteNewsAdapter;
import com.example.news.db.Sqldatebase;
import com.example.news.entity.NewsDetail;
import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/2.
 */

public class FavoritePagerActivity extends BaseActivity {
    @Bind(R.id.news_favorite_pager_lst)
    ListView mlstview;
    @Bind(R.id.up_main_mid_text)
    TextView mtitle;
    List<NewsDetail> details;
    Sqldatebase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_favorite_pager);
        ButterKnife.bind(this);
        mtitle.setText("新闻收藏");
        initdata();
        initevent();
    }
    public void initdata(){
        FavoriteNewsAdapter  newsAdapter = new FavoriteNewsAdapter(this);
         database = Sqldatebase.getdatabase(this);
         details = database.favoritednewsinfo();
        newsAdapter.addData(details);
        mlstview.setAdapter(newsAdapter);

    }
    public void  initevent(){
        mlstview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsDetail newsDetail = details.get(position);
                String link= newsDetail.getLink();
                Intent intent=new Intent(FavoritePagerActivity.this, FavoriteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("link",link);
                bundle.putInt("position",position);
                bundle.putSerializable("newsdetail",(Serializable)details);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        mlstview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final NewsDetail newsDetail = details.get(position);
                AlertDialog.Builder builder=new AlertDialog.Builder(FavoritePagerActivity.this);
                builder.setItems(new String[]{"取消收藏"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//只有一个子按钮,which的取值就只有0
                         switch (which){
                             case 0:
                                 database.deletenews(newsDetail.getNid());
                                 initdata();//作用,刷新适配器
                             break;
                         }
                    }
                });
                builder.show();
                return true;//true表示显示自己设置的,false表示调用父类的
            }
        });

    }
    @OnClick({R.id.up_main_left_img,R.id.up_main_right_img})
    public void titleimage(View view){
        switch (view.getId()){
            case  R.id.up_main_left_img:
                toActivity(MainActivity.class);
                break;
            case R.id.up_main_right_img:
                toActivity(MainActivity.class);
        }
    }


}
