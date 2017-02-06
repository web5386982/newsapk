package com.example.news.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.MainActivity;
import com.example.news.R;
import com.example.news.unti.Constance;
import com.example.news.unti.DataUtils;
import com.example.news.newspresenter.NewsNetPresenter;
import com.example.news.entity.Registers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/27.
 */

public class RegisterFragment extends Fragment {
    MainActivity mactivity;
    @Bind(R.id.up_main_mid_text) TextView titletext;//标题
    @Bind(R.id.user_number_edt) EditText memailaccount;//邮箱
    @Bind(R.id.login_on_username) EditText mregistname;//昵称
    @Bind(R.id.login_on_pwd) EditText mregistpwd;//密码

    Registers registerinfo;
    NewsNetPresenter newsPresenter;
    //复选框的绑定
    @Bind(R.id.login_on_chb)
    CheckBox mchbclick;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_on_user, container, false);
        ButterKnife.bind(this,view);
        mactivity = (MainActivity) getActivity();
        titletext.setText("用户注册");
        return view;
    }
    /**
     * 从编辑框获取用户的详细信息
     */
    public void registerUserInfo(){
//        ver=1&uid=用户昵称&email=邮箱地址&pwd=5386982";

        String useremail=memailaccount.getText().toString();
        String registname=mregistname.getText().toString();
        String registpwd = mregistpwd.getText().toString();
        Map<String, String> datas = new HashMap<>();
        datas.put("uid",registname);
        datas.put("email",useremail);
        datas.put("pwd",registpwd);
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append(Constance.PATH_LOGIN_REGISTER);//拼接注册访问基本路径
        String buffer = DataUtils.registerData(datas);
        String path=sBuffer.append(buffer).toString();//拼接注册访问全路径
        newsPresenter = new NewsNetPresenter(mactivity, Request.Method.GET,path
                , null, listener, errorlistener);//网络访问方法的调用
        newsPresenter.netRequest();

    }
  //标题栏左上角图片点击事件
    @OnClick(R.id.up_main_left_img)
    public void lefthomeclick(View view) {
        mactivity.slidmenu.showMenu();
    }
    //标题栏右上角图片点击事件
    @OnClick(R.id.up_main_right_img)
    public void rightshareclick(View view) {
        mactivity.slidmenu.showSecondaryMenu();
    }

    /**
     * 注册按钮的点击事件
     * @param view
     */
    @OnClick(R.id.log_on_txt_install)
    public void userRegisterClick(View view) {
        if(mchbclick.isChecked()) {//复选框状态的判断
          if(Pattern.matches(Constance.EMAIL_JUDGE,memailaccount.getText())){//邮箱判断
              if(Pattern.matches(Constance.USER_ACCOUNT,mregistname.getText())){//昵称的判断
                  if(Pattern.matches(Constance.USER_ACCOUNT,mregistname.getText())){//密码的判断
                      registerUserInfo();//注册全过程;
                  }else {
                      Toast.makeText(mactivity, "请输入6位以上的密码", Toast.LENGTH_SHORT).show();
                  }
              }else {
                  Toast.makeText(mactivity, "您输入的昵称不合法,请重新输入", Toast.LENGTH_SHORT).show();
              }
          }else {
              Toast.makeText(mactivity, "请输入正确的邮箱地址", Toast.LENGTH_SHORT).show();
          }
        }else{
            Toast.makeText(mactivity, "请阅读协议条款", Toast.LENGTH_SHORT).show();
        }
    }


    public Response.Listener listener=new Response.Listener() {
        @Override
        public void onResponse(Object object) {//现在已经成功
            Log.e("TAG", ""+object.toString());
            Gson gson=new Gson();
            TypeToken type=new TypeToken<Registers>(){};//一定不能忘了{},他的参数类型就是下边的返回值类型,主要看返回数据定
            registerinfo=gson.fromJson(object.toString(),type.getType());
            //判断登陆是否成功
            if( "OK".equals( registerinfo.message)){
                Toast.makeText(mactivity, registerinfo.message+"----------------"+registerinfo.status, Toast.LENGTH_SHORT).show();
                mactivity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.activity_main, new LoginFragment()).commit();
                newsPresenter.cancelDialog();

            }else{
                Toast.makeText(mactivity, "注册失败"+registerinfo.getData().getExplain(), Toast.LENGTH_SHORT).show();
            }
        }
    };
    Response.ErrorListener errorlistener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

        }
    };
}
