package com.example.news.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.entity.UserCenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/** usercenter`s adapter of listview ;
 * Created by Administrator on 2016/12/29.
 */

public class UserCenterAdapter extends MyAdapter {

    public UserCenterAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        UserHolder userHolder ;
        if(convertView==null){
            convertView=minflater.inflate(R.layout.user_list_item_center,null);
            userHolder = new UserHolder(convertView);
            convertView.setTag(userHolder);
        }else {
            userHolder= (UserHolder) convertView.getTag();
        }
        UserCenter.DataBean.LoginlogBean logininfo = (UserCenter.DataBean.LoginlogBean) mdatas.get(position);
        userHolder.timeone.setText(logininfo.getTime());
        userHolder.address.setText(logininfo.getAddress());
        userHolder.device.setText(Integer.toString(logininfo.getDevice()));
        return convertView;
    }
    class UserHolder{
        @Bind(R.id.user_lst_item_one)
        TextView timeone;
        @Bind(R.id.user_lst_item_to)
        TextView address;
        @Bind(R.id.user_lst_item_three)
        TextView device;

        public UserHolder(View convertView){
            ButterKnife.bind(this,convertView);

        }

    }

}
