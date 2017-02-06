package com.example.news.Fragments;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.news.Adapter.LeftMenuLstAdapter;
import com.example.news.FavoritePagerActivity;
import com.example.news.MainActivity;
import com.example.news.R;
import com.example.news.entity.LeftMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/25.
 */

public class LeftSlidMenuFragment extends Fragment {
    List<LeftMenu> datas;
    LeftMenu leftmenu;
    //以前用的ListView实现的做菜单,现在那个数据库的String丢了用不了了
//    @Bind(R.id.lsft_menu_lst_mainface)
//    ListView listview;//ListView的绑定；
    MainActivity mactivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_left_menu_tradi,container,false);//用ListView做的
         ButterKnife.bind(LeftSlidMenuFragment.this, view);//这里只能获取视图，获取Acticity就空指针
         mactivity = (MainActivity) getActivity();
        return view;
    }

    private View initListView(LayoutInflater inflater, ViewGroup container) {//这个方法暂时没调用
        View view = inflater.inflate(R.layout.fragment_left_menu_lst, container, false);
        //拿到这个布局的目的是为了返回视图；
        initData();
        LeftMenuLstAdapter adapter = new LeftMenuLstAdapter(getActivity());
        adapter.addData(datas);
//        listview.setAdapter(adapter);
        return view;
    }
// ButterKnife绑定的不用必须注释掉.切记!!!!
//    @OnItemClick(R.id.lsft_menu_lst_mainface)
//    public void itemclick(View view){//ListView的点击事件绑定的ID是ListView的不是子条目的:
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,new MainListFragment()).commit();
//    }



    //这些点击事件是给传统方式的每个控件设置的;
    @OnClick(R.id.lsft_news)
    public void newsclick(View view){
        mactivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,new MainListFragment()).commit();//跳转到主页,即内容页
        mactivity.slidmenu.showContent();
   }
    @OnClick(R.id.lsft_favorite)//这是这个模块所在布局的id
    public void favoriteclick(View view){
        Toast.makeText(getActivity(), "favoriteclick,你点击了收藏 ", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getActivity(), FavoritePagerActivity.class);
        startActivity(intent);

    }
    @OnClick(R.id.lsft_local)
    public void localclick(View view){
        Toast.makeText(getActivity(), "localclick,你点击了本地 ", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.lsft_comment)
    public void commentclick(View view){
        Toast.makeText(getActivity(), "commentclick,你点击了跟帖 ", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.lsft_photo)
    public void photoclick(View view){
        Toast.makeText(getActivity(), "photoclick,你点击了图片 ", Toast.LENGTH_SHORT).show();
    }

    public void initData() {
        datas = new ArrayList<>();

        String[] name = this.getResources().getStringArray(R.array.left_menu_name);//切记不要忘了数组的调用方法；
        Log.e("_____________", "name" + name.length);
        String[] english = this.getResources().getStringArray(R.array.left_menu_english);
        int[] image = {R.mipmap.defaultpic, R.mipmap.biz_navigation_tab_read, R.mipmap.biz_navigation_tab_local_news, R.mipmap.biz_navigation_tab_ties, R.mipmap.biz_navigation_tab_pics};
        for (int i = 0; i < name.length; i++) {
            String menuname = name[i];
            String menuenglish = english[i];
            int icon = image[i];
            leftmenu = new LeftMenu(menuname, menuenglish, icon);
            datas.add(leftmenu);
        }

    }

}