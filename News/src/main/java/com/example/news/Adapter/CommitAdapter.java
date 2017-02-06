package com.example.news.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.entity.ShowCommitContext;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class CommitAdapter extends MyAdapter {
    public CommitAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        CommitHolder mcommitHolder;
        if(convertView==null){
            convertView=minflater.inflate(R.layout.activity_show_commit_lst_item,null);
            mcommitHolder=new CommitHolder(convertView);
            convertView.setTag(mcommitHolder);
        }else {
            mcommitHolder= (CommitHolder) convertView.getTag();
        }
        ShowCommitContext.DataBean data = (ShowCommitContext.DataBean) mdatas.get(position);
        Picasso.with(mcontext).load(data.getPortrait()).into(mcommitHolder.userprotarit);
        mcommitHolder.username.setText(data.getUid());
        mcommitHolder.commitstamp.setText(data.getStamp());
        mcommitHolder.commitcontext.setText(data.getContent());
        return convertView;
    }
    class CommitHolder{
        @Bind(R.id.commit_user_portrait)
        ImageView userprotarit;
        @Bind(R.id.commit_user_name)
        TextView username;
        @Bind(R.id.commit_user_stamp)
        TextView commitstamp;
        @Bind(R.id.commit_user_content)
        TextView commitcontext;

        public CommitHolder(View convertView){
            ButterKnife.bind(this,convertView);

        }
    }
}
