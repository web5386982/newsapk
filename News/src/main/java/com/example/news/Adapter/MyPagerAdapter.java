package com.example.news.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */

public class MyPagerAdapter extends PagerAdapter {
    protected Context mcontext;
    protected List<ImageView> mdatas;//这里传的必须是视图,ImageView,,别的不行,Image都不行;

    public MyPagerAdapter(Context context, List<ImageView> datas){
       this.mcontext=context;
       this.mdatas=datas;
    }
    @Override
    public int getCount() {
        return mdatas!=null?mdatas.size():0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mdatas.get(position));//这个方法主要是添加视图,必须是视图,不然报错
        return mdatas.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(mdatas.get(position));

    }
}
