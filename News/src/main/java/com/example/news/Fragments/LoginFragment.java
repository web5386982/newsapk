package com.example.news.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.MainActivity;
import com.example.news.R;
import com.example.news.UserCoreActivity;
import com.example.news.unti.Constance;
import com.example.news.unti.DataUtils;
import com.example.news.unti.SharePreUtils;
import com.example.news.newspresenter.NewsNetPresenter;
import com.example.news.entity.LoginInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/26.
 */

public class LoginFragment extends Fragment {
    MainActivity mactivity;
    @Bind(R.id.up_main_mid_text) TextView titletext;//标题文本
    @Bind(R.id.login_user_edt) EditText musername;//获取账号
    @Bind(R.id.login_pwd_edt) EditText muserpwd;//获取密码
    NewsNetPresenter newsNetPresenter;
    LoginInfo logininfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_register_pager, container, false);
        ButterKnife.bind(this, view);
        mactivity = (MainActivity) this.getActivity();
        titletext.setText("用户登录");
        return view;
    }
    //标题栏左上角
    @OnClick(R.id.up_main_left_img)
    public void lefthomeclick(View view) {
        mactivity.slidmenu.showMenu();
    }

    //标题栏右上角
    @OnClick(R.id.up_main_right_img)
    public void rightshareclick(View view) {
        mactivity.slidmenu.showSecondaryMenu();
    }

    //注册界面点击事件
    @OnClick(R.id.login_on_user_install)
    public void userInstallClick(View view) {
        mactivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new RegisterFragment()).commit();
    }

    //修改密码点击事件
    @OnClick(R.id.login_on_user_forget_pwd)
    public void forgetPwdClick(View view) {
        mactivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new ForgetPwdFragment()).commit();
    }

    //登陆界面点击事件
    @OnClick(R.id.login_login_face)
    public void loginFaceClick(View view) {
        if (Pattern.matches(Constance.USER_ACCOUNT,musername.getText())) {

            if(Pattern.matches(Constance.PWD_JUDGE,muserpwd.getText())){
                initLoginData();//访问网络
                //到这,说明登陆成功了
            }else {
                Toast.makeText(mactivity, "请输入6位以上的密码", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(mactivity, "您输入的昵称不合法,请重新输入", Toast.LENGTH_SHORT).show();
        }

    }
    public void initLoginData(){
//         uid = 用户名 &   pwd = 密码 &    device=0;
        StringBuffer buffer = new StringBuffer();
        buffer.append(Constance.PATH_LOGIN_SURFACE);//拼接基本路径
        String username = musername.getText().toString();
        String userpwd = muserpwd.getText().toString();
        Map<String, String> datas = new HashMap<String, String>();
        datas.put("uid",username);
        datas.put("pwd",userpwd);
        String buff = DataUtils.loginData(datas);//用户信息的拼接
        String path = buffer.append(buff).toString();//到此路径添加完成
        newsNetPresenter = new NewsNetPresenter(mactivity, Request.Method.GET, path, null, listener, errorlistener);
        newsNetPresenter.netRequest();//访问网络开始
    }
    Response.Listener listener=new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            Gson gson = new Gson();
            TypeToken type=new TypeToken<LoginInfo>(){};
            logininfo  = gson.fromJson(object.toString(), type.getType());
            if("OK".equals(logininfo.getMessage())){
                //将服务器返回的用户令牌传给数据库保存起来;这个需要在数据解析后调用
                SharePreUtils sharePreUtils = SharePreUtils.instanceShare(getActivity());
                sharePreUtils.putToken("token",logininfo.getData().getToken());
                newsNetPresenter.cancelDialog();
                Intent intent=new Intent(getContext(), UserCoreActivity.class);
                startActivity(intent);
                newsNetPresenter.cancelDialog();
            }else {
                Toast.makeText(mactivity, "-仔细看,哪里好像不对劲"+logininfo.getData().getExplain(), Toast.LENGTH_SHORT).show();
            }
        }
    };
    Response.ErrorListener errorlistener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(mactivity, "-_-到这里就是你的不对了-.-", Toast.LENGTH_SHORT).show();
        }
    };



}
