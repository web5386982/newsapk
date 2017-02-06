package com.example.news.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.MainActivity;
import com.example.news.R;
import com.example.news.UserCoreActivity;
import com.example.news.entity.VersionInfo;
import com.example.news.newspresenter.NewsNetPresenter;
import com.example.news.unti.Constance;
import com.example.news.unti.SharePreUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**数据不好传的时候,就在当前类处理完之后,把对象传过去
 * Created by Administrator on 2016/12/25.
 */

public class RightMenuFragment extends Fragment {
    MainActivity mactivity;
    @Bind(R.id.right_menu_circle_img) ImageView muserRightMenuPhoto;//用户右边菜单头像
    @Bind(R.id.right_menu_text_up) TextView muserRightMenuName;//右边菜单用户名
    @Bind(R.id.right_fun_share_weibo)  ImageView muserweibo;
    NewsNetPresenter newsNetPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_menu_context, container, false);
        ButterKnife.bind(this,view);
        mactivity = (MainActivity) getActivity();
        setrightmenuphoto();
        muserweibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnekeyShare onekey=new OnekeyShare();
                onekey.show(getContext());
            }
        });


        return view;
    }

    /**
     * 右边菜单中,头像
     */
    private void setrightmenuphoto() {
        SharePreUtils sharePreUtils = SharePreUtils.instanceShare(getActivity());
        if(sharePreUtils.getToken("portrait")!=null){
            Picasso.with(getActivity()).load(sharePreUtils.getToken("portrait")).into(muserRightMenuPhoto);//用户右边菜单头像
        }
        if(sharePreUtils.getToken("uid")!=null){
            muserRightMenuName.setText(sharePreUtils.getToken("uid"));
        }
    }

    /**
     * 右边菜单中,头像,及下边登录的点击事件
     * @param view
     */
    @OnClick({R.id.right_menu_circle_img,R.id.right_menu_text_up})
    public void loginclick(View view){
        SharePreUtils sharePreUtils = SharePreUtils.instanceShare(getActivity());
        if(sharePreUtils.getToken("token")==null){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,new LoginFragment()).commit();
            sharePreUtils.concelToken("token");
            sharePreUtils.concelToken("uid");
            sharePreUtils.concelToken("portrait");
            //出现错误时采用这几行代码清除数据库,重新登录
            mactivity.slidmenu.showContent();
        }else{
            Intent intent=new Intent(getActivity(), UserCoreActivity.class);
            startActivity(intent);
        }

    }

    /**
     * 文本,版本更新的点击事件
     * @param view
     */
    @OnClick(R.id.version_update_txt)
    public void versionclick(View view){
        getNetVisit();
    }

    /**
     * 访问网络
     */

    public void getNetVisit(){
        //http://118.244.212.82:9092/newsClient/update?imei=0000000000011201&pkg=  包名&ver=1
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Constance.PATH_NEWS_VRESION_UPDATA).append(getActivity().getPackageName()).append("&ver=1");
        newsNetPresenter = new NewsNetPresenter(getActivity(), Request.Method.GET, stringBuffer.toString(), null, listener, errorlistener);
        newsNetPresenter.netRequest();
    }

    /**
     * 获取当前版本号
     */
    public int getlocalversion(){
        int versionCode=0;
        try {
          versionCode = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
         return versionCode;
    }
  Response.Listener listener=new Response.Listener() {
      @Override
      public void onResponse(Object object) {
          newsNetPresenter.cancelDialog();
          Log.e("Object", "onResponse: "+object.toString() );
          Gson gson = new Gson();
          VersionInfo versioninfo=gson.fromJson(object.toString(),new TypeToken<VersionInfo>(){}.getType());
          isUpdata(versioninfo);
      }
  };
    Response.ErrorListener errorlistener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(mactivity, "别再犯错,,是你的固执让我难过", Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * 是否更新版本
     * @param versioninfo
     */
    private void isUpdata(VersionInfo versioninfo) {
        if(Integer.parseInt(versioninfo.getVersion())==getlocalversion()){//这里改成了==是错误的,只是为了测试,别给忘记改回来了
                //设置下载新的APK
//
        final AlertDialog.Builder builder=new AlertDialog.Builder(mactivity);
        builder.setMessage("请选择");
        builder.setTitle("提示");
            builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }else {
            Toast.makeText(mactivity, "版本已是最新", Toast.LENGTH_SHORT).show();
        }
    }






}
