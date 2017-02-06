package com.example.news.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */

public abstract class MyAdapter<T> extends BaseAdapter {
    protected Context mcontext;
    protected List<T> mdatas;
    protected LayoutInflater minflater;

    public MyAdapter(Context context) {

        this.mcontext = context;
        minflater= LayoutInflater.from(mcontext);
    }

    /**
     *
     */
    public void addData(List<T> datas) {
        this.mdatas = datas;

    }

    @Override
    public int getCount() {
        return null != mdatas?mdatas.size() : 0;
    }

    @Override
    public T getItem(int position) {
        //回传的就是实体类的对象，T就是定义的实体类的泛型。
        return mdatas.get(position);
    }

    /**
     * 更新适配器
     */
     public void upDate(){
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //抽象方法的返回值要和这个方法的返回值一样，因为他把抽象方法的结果返回去了。两个返回的是同一个结果。所以类型就必须一样

        return getMyView(position, convertView,parent);
    }

    public abstract View getMyView(int position, View convertView, ViewGroup parent);
}
