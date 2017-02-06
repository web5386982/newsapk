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
 * Created by Administrator on 2017/1/2.
 */

public class FavoriteNewsAdapter extends   MyAdapter {
    public FavoriteNewsAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        FavoriteHolder holder;
        if(convertView==null){
             convertView = minflater.inflate(R.layout.fragment_favorite_lst_item, null);
             holder=new FavoriteHolder(convertView);
             convertView.setTag(holder);
        }else {
            holder= (FavoriteHolder) convertView.getTag();
        }
        NewsDetail favoriteinfo = (NewsDetail) mdatas.get(position);
        holder.summary.setText(favoriteinfo.getSummary());
        holder.stamp.setText(favoriteinfo.getStamp());
        holder.link.setText(favoriteinfo.getLink());
        Picasso.with(mcontext).load(favoriteinfo.getIcon()).into(holder.icon);
        return convertView;
    }
    class FavoriteHolder{
        @Bind(R.id.favorite_lst_ietm_txt_summary)
        TextView summary;
        @Bind(R.id.favorite_lst_ietm_txt_stamp)
        TextView stamp;
        @Bind(R.id.favorite_lst_ietm_txt_link)
        TextView link;
        @Bind(R.id.favorite_lst_ietm_img)
        ImageView icon;


        public FavoriteHolder(View convertView){
            ButterKnife.bind(this,convertView);
        }

    }
}
