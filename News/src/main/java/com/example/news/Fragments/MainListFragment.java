package com.example.news.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.Adapter.MainGageListAdapter;
import com.example.news.FavoriteActivity;
import com.example.news.MainActivity;
import com.example.news.NewsSortActivity;
import com.example.news.R;
import com.example.news.entity.NewsDetail;
import com.example.news.viewlist.XListView;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.key;

/**
 * Created by Administrator on 2016/12/26.
 */

public class MainListFragment extends Fragment {
    MainGageListAdapter mainpageradapter;
    @Bind(R.id.main_context_list)  XListView mxlistview;
//    ListView mListView;
    @Bind(R.id.down_main_mid_society) TextView society;
//    @Bind(R.id.down_main_military) TextView mmilitary;
    MainActivity mactivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_conext_pager, container, false);
        ButterKnife.bind(this, view);
        mactivity = (MainActivity) this.getActivity();

        //getActivity为父类FragmentActivity的方法,通过他拿到的是父类的对象,我们强转可以拿到MainActivity的对象,就可以调用他的成员变量;
        initxListview();
        mXlistItemclick();
        return view;
    }

    /**
     * 初始化XlistView
     */
    private void initxListview() {
        mainpageradapter = new MainGageListAdapter(getActivity());
        mainpageradapter.addData(mactivity.newswholeinfo.data);
        mxlistview.setAdapter(mainpageradapter);
        mxlistview.setPullLoadEnable(true);//设置上啦加载
    }


    /**
     * item点击事件
     * @param
     */
    public void mXlistItemclick(){
        mxlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<NewsDetail> datas = mactivity.newswholeinfo.getDatas();
                NewsDetail newsDetail = datas.get(position-1);//集合存储是从0开始的.地址是从一开始获取的,,,,一定注意-1;;
                String link = newsDetail.getLink();
                Intent intent=new Intent(getActivity(), FavoriteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                bundle.putString("link",link);
                bundle.putSerializable("newsdetail",(Serializable)datas);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    /**
     * 新闻分类页的跳转
     * @param view
     */
    @OnClick(R.id.down_main_right_img)
    public void rigthNewsSort(View view){
//        在这实现页面跳转
        Intent intent=new Intent(mactivity, NewsSortActivity.class);
        startActivity(intent);
        mactivity.netPresenter.cancelDialog();

    }
    /**
     * 军事按钮的点击事件
     * @param view
     */
    @OnClick(R.id.down_main_military)
    public void militaryclick(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new MainListFragment()).commit();
        mactivity.slidmenu.showContent();
    }

    /**
     * 社会按钮的点击事件
     * @param view
     */
    @OnClick(R.id.down_main_mid_society)
    public void setSocietyclick(View view) {
        Toast.makeText(mactivity, "sorry,,,,今天不上班", Toast.LENGTH_SHORT).show();
        mactivity.slidmenu.showContent();
    }

    /**
     * 返回做菜单的点击事件
     * @param view
     */
    @OnClick(R.id.up_main_left_img)
    public void lefthomeclick(View view) {
        mactivity.slidmenu.showMenu();
    }

    /**
     * 返回右菜单的点击事件
     * @param view
     */
    @OnClick(R.id.up_main_right_img)
    public void rightshareclick(View view) {

        mactivity.slidmenu.showSecondaryMenu();
    }
}
