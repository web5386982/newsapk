package com.example.news.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.entity.LeftMenu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**这个类是为了查找Fragment绑定的视图，和那个ID相同时不出现数据的问题是复制的传统适配器
 * Created by Administrator on 2016/12/25.
 */

public class NewsAdapter extends BaseAdapter {
    Context mcontext;
    List<LeftMenu> mdatas;

    public NewsAdapter(Context context, List<LeftMenu> datas){
        this.mcontext=context;
        this.mdatas=datas;
    }
    @Override
    public int getCount() {
        return mdatas!=null?mdatas.size():0;
    }

    @Override
        public LeftMenu getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftViewHolder mleftlist;
        if(convertView==null){
            LayoutInflater minflater= LayoutInflater.from(mcontext);
            //必须把渲染的结果赋值给convertView；不然传过去就是空的
            convertView= minflater.inflate(R.layout.fragment_left_menu_lst_sub,null);
            mleftlist=new LeftViewHolder(convertView);
            convertView.setTag(mleftlist);
        }else{
            mleftlist= (LeftViewHolder) convertView.getTag();
        }
        LeftMenu detail=mdatas.get(position);
          mleftlist.txtname.setText(detail.getName());
        mleftlist.txtenglish.setText(detail.getEnglish());

        return convertView;
    }
    class LeftViewHolder{
        //切记，class C不能大写，大写了就是类，不是关键字了
        @Bind(R.id.left_lst_item_txt_name)
        TextView txtname;
        @Bind(R.id.left_lst_item_txt_english)
        TextView txtenglish;
        @Bind(R.id.left_lst_item_img)
        ImageView itemimg;

        public LeftViewHolder(View convertView ){

            ButterKnife.bind(this,convertView);
        }

    }
}
