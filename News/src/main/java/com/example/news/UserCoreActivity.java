package com.example.news;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.Adapter.UserCenterAdapter;
import com.example.news.unti.Constance;
import com.example.news.unti.DataUtils;
import com.example.news.unti.SharePreUtils;
import com.example.news.newspresenter.NewsNetPresenter;
import com.example.news.entity.UserCenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**用户中心的主页面
 * Created by Administrator on 2016/12/29.
 */

public class UserCoreActivity extends BaseActivity{
    NewsNetPresenter netPresenter;
    @Bind(R.id.user_core_img_photo) ImageView userPhoto;//用户头像
    @Bind(R.id.user_core_user_name) TextView muserName;//用户名绑定
    @Bind(R.id.user_core_intergrate_count) TextView mintergrate;//积分数绑定
    @Bind(R.id.user_context_census_count) TextView mcontextConut;//跟帖数绑定
    @Bind(R.id.user_core_lst_main) ListView mloginLogList;//ListView绑定

    SharePreUtils sharePreUtils;
    ImageView imgleft;//左边标题图标
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_core);
        ButterKnife.bind(this);
        initUsercoreNet( );//不能忘记调用
    }
    /**
     * 用于网络访问
     */
     public void initUsercoreNet(){
         StringBuffer buffer = new StringBuffer();
         Map<String,String> userdata=new HashMap<String,String>();
         sharePreUtils = SharePreUtils.instanceShare(this);
         Log.e("sharePreUtils", "initUsercoreNet: "+sharePreUtils.getToken("token") );
         userdata.put("token",sharePreUtils.getToken("token"));
         String tokenstring= DataUtils.userData(userdata);
         //网络访问
         String path= buffer.append(Constance.PATH_LOGIN_USER_CENTER).append(tokenstring).toString();
         netPresenter = new NewsNetPresenter(this, Request.Method.GET, path, null, listener, errorlistner);
         netPresenter.netRequest();

     }

    /**
     * 初始化用户信息
     */
    public void inituserinfo(UserCenter usercenter){//网络数据解析完成后调用
        String portrait = usercenter.getData().getPortrait();
        RequestCreator request = Picasso.with(this).load(portrait);
        request.into( userPhoto);//用户头像设置

        String uid = usercenter.getData().getUid();
        muserName.setText(uid);//设置用户名
        mintergrate.setText(Integer.toString(usercenter.getData().getIntegration()));//积分
        mcontextConut.setText(Integer.toString(usercenter.getData().getComnum()));//跟帖数
    }

    /**
     * 点击之后可获得本地相册图片
     * @param view
     */
     @OnClick(R.id.user_core_img_photo)
     public void userPhotoclick(View view){
         Intent i = new Intent(
                 Intent.ACTION_PICK,
                 android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         startActivityForResult(i,1);
         //获取头像文件
         // 访问网络
     }
    /**
     * 退出登录的点击事件
     */
    @OnClick(R.id.user_core_btn_quit)
    public void userQuitClick(){
        //删除数据库shareper中的token数据
        sharePreUtils.concelToken("token");
        sharePreUtils.concelToken("portrait");
        sharePreUtils.concelToken("uid");
        toActivity(MainActivity.class);//回到主界面;

    }
    //标题栏左上角
    @OnClick(R.id.user_title_img_left)
    public void lefthomeclick(View title) {
        toActivity(MainActivity.class);
    }

    Response.Listener listener=new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            //访问网络成功
            Gson gson = new Gson();
            UserCenter usercenter= gson.fromJson(object.toString(), new TypeToken<UserCenter>() {
            }.getType());//数据解析结束
            if(usercenter!=null){//如果获取有先后,要多加判断
            SharePreUtils sharePreUtils = SharePreUtils.instanceShare(UserCoreActivity.this);
            sharePreUtils.putToken("portrait",usercenter.getData().getPortrait());
            sharePreUtils.putToken("uid",usercenter.getData().getUid());

            }
            inituserinfo(usercenter);//将要初始化的数据对象传过去
            netPresenter.cancelDialog();
            //在这设置ListView
            UserCenterAdapter userAdapter = new UserCenterAdapter(UserCoreActivity.this);
            userAdapter.addData(usercenter.getData().getLoginlog());
            mloginLogList.setAdapter(userAdapter);
        }
    };
    Response.ErrorListener errorlistner=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(UserCoreActivity.this, "看到我你,该知道自己错了吧!", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 获取相册,必须重写这个方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);//图片路径
            Log.e(picturePath, "onActivityResult: "+picturePath );
            Log.e("portrait", "onActivityResult: "+sharePreUtils.getToken("portrait").toString()+"________________________"+sharePreUtils.getToken("token")+"----------------------------------------------"+picturePath.toString() );

            cursor.close();
            //从这把图片传给服务器.图片的使用和显示
//            ImageView imageView = (ImageView) findViewById(R.id.imgView);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//            从这里上传头像到服务器
//            http://118.244.212.82:9092/newsClient/user_image?token=sharePreUtils.getToken("token")&portrait =头像
//            sharePreUtils.getToken("portrait");

        }

    }

}
