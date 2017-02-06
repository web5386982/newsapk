package com.example.news.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.news.Adapter.FavoriteNewsAdapter;
import com.example.news.Adapter.MainGageListAdapter;
import com.example.news.FavoriteActivity;
import com.example.news.MainActivity;
import com.example.news.R;
import com.example.news.entity.NewsDetail;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**这是左菜单收藏空间对应的类
 * Created by Administrator on 2017/1/2.
 */

public class FavoritePagerFragment extends Fragment {
//    @Bind(R.id.news_favorite_pager_lst) ListView mlstview;
//    @Bind(R.id.up_main_mid_text) TextView mtitle;
//    MainActivity mactivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_pager, container, false);
        ButterKnife.bind(this,view);
//        mtitle.setText("新闻收藏");
//        mactivity = (MainActivity) getActivity();
//        initdata();

        return view;
    }

//    private void initdata() {
//        FavoriteActivity favoriteActivity = new FavoriteActivity();
//        List<NewsDetail> favoriteNews = favoriteActivity.favoriteNews;//可以直接把这句放在添加数据的地方
//        FavoriteNewsAdapter newsAdapter = new FavoriteNewsAdapter(mactivity);
//        newsAdapter.addData(favoriteNews);
//        mlstview.setAdapter(newsAdapter);
//
//    }
}
