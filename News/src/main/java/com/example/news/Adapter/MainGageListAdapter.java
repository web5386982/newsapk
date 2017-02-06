package com.example.news.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.entity.NewsDetail;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/25.
 */

public class MainGageListAdapter extends MyAdapter{
    NewsDetail detail;
    public MainGageListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        MainListHolder listHolder;
        if(null==convertView){
            convertView=minflater.inflate(R.layout.fragment_news_menu_lst_item,parent,false);
            listHolder=new MainListHolder(convertView);
          ImageView icon= (ImageView) convertView.findViewById(R.id.main_lst_ietm_img);//传统方式添加数据;
            convertView.setTag(listHolder);
        }else{
            listHolder= (MainListHolder) convertView.getTag();
        }
        detail= (NewsDetail) mdatas.get(position);
        listHolder.summary.setText(detail.getSummary().trim());
        listHolder.stamp.setText(detail.getStamp().trim());
        listHolder.link.setText(detail.getLink().trim());
        //下边这三步是添加图片的，不只是网络的，本地的也可以这样添加，就是暂时不会用Butterknife绑定，这个绑定会报错。只能手动绑定。
//        Picasso picasso=Picasso.with(mcontext);
//        RequestCreator requestcreator=picasso.load(detail.getIcon());
//        requestcreator.into(icon);//这里参数要用下边类的对象点属性,再别忘了!!!!
          Picasso.with(mcontext).load(detail.getIcon()).into(listHolder.icon);

        return convertView;
    }
//    将新闻地址传回去
       public String initLink(){
        String link= detail.getLink().trim();
        return link;
    }
    class MainListHolder{
        @Bind(R.id.main_lst_ietm_txt_summary)
        TextView summary;
        @Bind(R.id.main_lst_ietm_txt_stamp)
        TextView stamp;
        @Bind(R.id.main_lst_ietm_txt_link)
        TextView link;
        @Bind(R.id.main_lst_ietm_img)
        ImageView icon;


        public MainListHolder(View convertView){
            ButterKnife.bind(this,convertView);

        }
    }
}
