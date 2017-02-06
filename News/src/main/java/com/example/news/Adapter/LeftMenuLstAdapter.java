package com.example.news.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.entity.LeftMenu;

import butterknife.Bind;
import butterknife.ButterKnife;


/**左滑菜单中，ListView的Adapter
 * Created by Administrator on 2016/12/25.
 */

public class LeftMenuLstAdapter extends MyAdapter{
       //继承之后他会提示你重写抽象方法，完了之后会继续提示你重写构造方法、
       ImageView itemimg;
    public LeftMenuLstAdapter(Context context) {
        super(context);//这是在调用父类的构造方法，即Layoutinflater的对象在此创建
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        LeftListViewHolder mleftlist=null;
        if(convertView==null){
            //必须把渲染的结果赋值给convertView；不然传过去就是空的
            convertView= minflater.inflate(R.layout.fragment_left_menu_lst_sub,null);
            mleftlist=new LeftListViewHolder(convertView);
//            itemimg= (ImageView) convertView.findViewById(R.id.left_lst_item_img);
            convertView.setTag(mleftlist);
        }else{
            mleftlist= (LeftListViewHolder) convertView.getTag();
        }
        LeftMenu detail= (LeftMenu) mdatas.get(position);
        mleftlist.txtname.setText(detail.getName());
        mleftlist.txtenglish.setText(detail.getEnglish());
//         Picasso picasso=Picasso.with(mcontext);//注意用静态方法调用
//         RequestCreator request=picasso.load(detail.getIcon());
//           request.into(itemimg);

        mleftlist.itemimg.setImageResource(detail.getIcon());

        return convertView;
    }

    class LeftListViewHolder{
        //切记，class C不能大写，大写了就是类，不是关键字了
        @Bind(R.id.left_lst_item_txt_name)
        TextView txtname;
        @Bind(R.id.left_lst_item_txt_english)
        TextView txtenglish;
        @Bind(R.id.left_lst_item_img)
        ImageView itemimg;
        //Bind这种方式绑定的，不用就先注释掉，不然报错

        public LeftListViewHolder(View convertView ){
            ButterKnife.bind(this,convertView);
        }

    }
}
