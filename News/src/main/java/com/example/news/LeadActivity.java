package com.example.news;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.news.Adapter.MyPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/26.
 */

public class LeadActivity extends BaseActivity{

    @Bind(R.id.lead_view_pager)
    ViewPager mvPager;
    List<ImageView> iconViews;
    Timer timer;
    ImageView[] point;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_main);
        ButterKnife.bind(this);

        //判断是否第一次登陆
        preferences = this.getSharedPreferences("first", MODE_PRIVATE);//先创建一个数据库first
        boolean isFirst=preferences.getBoolean("First",false);//参数:第一个数键值,第二个是默认值,自己设置的
        //在数据库first中找First键对应的值;
        //false,为没找到对应键时返回的值:开始的时候数据库里是没有值得,当返回false时,再给他设置值;

        if(isFirst){
            toActivity(LoginActivity.class);
        }else{
            SharedPreferences.Editor sharedit = preferences.edit();
            sharedit.putBoolean("First",true);
            sharedit.commit();//不能忘记提交

            initViewpager();//ViewPager的实现
            bindPoint();//绑定点
            initpagerchange();//点击事件和滑动
        }
    }

    private void bindPoint() {
        //绑定控件,重新给他设置图片
        point = new ImageView[iconViews.size()];
        point[0]= (ImageView) this.findViewById(R.id.pager_img_one);
        point[1]= (ImageView) this.findViewById(R.id.pager_img_to);
        point[2]= (ImageView) this.findViewById(R.id.pager_img_three);
        point[3]= (ImageView) this.findViewById(R.id.pager_img_four);
    }

    private void initViewpager() {
        initData();//viewPager数据
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this, iconViews);
        mvPager.setAdapter(myPagerAdapter);
    }

    private void initpagerchange() {
        mvPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i <point.length ; i++) {
                    point[i].setImageResource(position==i?R.mipmap.adware_style_selected:R.mipmap.adware_style_default);
                }
                    if(position==iconViews.size()-1){
                     initSkip();
                }
            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void initData() {//适配器的数据
        iconViews = new ArrayList<>();
        int [] icons={R.mipmap.welcome,R.mipmap.wy,R.mipmap.bd,R.mipmap.small};
        for (int i = 0; i <icons.length ; i++) {
            ImageView mimgView = new ImageView(this);
            mimgView.setImageResource(icons[i]);
            iconViews.add(mimgView);
        }
    }

    private void initSkip() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toActivity(LoginActivity.class);
                timer.cancel();
            }
        }, 2000);
    }


}
