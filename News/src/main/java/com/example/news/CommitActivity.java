package com.example.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.Adapter.CommitAdapter;
import com.example.news.entity.CommitContext;
import com.example.news.entity.ShowCommitContext;
import com.example.news.newspresenter.NewsNetPresenter;
import com.example.news.unti.Constance;
import com.example.news.unti.SharePreUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommitActivity extends AppCompatActivity {

    @Bind(R.id.up_main_left_img)
    ImageView leftimg;
    @Bind(R.id.up_main_mid_text)
    TextView midtext;
    @Bind(R.id.up_main_right_img)
    ImageView rightimg;
    @Bind(R.id.commit_lst_context) ListView mlstcommit;
    NewsNetPresenter newsNetPresenter;
    NewsNetPresenter newsPresenter;
    @Bind(R.id.commit_context_edt) EditText edtframe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);
        ButterKnife.bind(this);
        leftimg.setImageResource(R.mipmap.back);
        midtext.setText("评论");
        rightimg.setVisibility(View.INVISIBLE);
        getcommimt();
  }

    private void getcommimt() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String stamp = bundle.getString("stamp");
        StringBuffer stringBuffer = new StringBuffer();
//        2016-06-17 02:31:20.0
        String substamp = stamp.substring(0, 11).trim();
        String[] splitstamp = substamp.split("-");
        stringBuffer.append(Constance.PATH_COMMIT_CONTEXT);
        stringBuffer.append(splitstamp[0].trim()).append(splitstamp[1].trim()).append(splitstamp[2].trim());
        Log.e("stringBuffer", "getcommimt: "+stringBuffer.toString() );
        String path = stringBuffer.append("&cid=1&dir=1&cnt=20").toString();
        newsPresenter = new NewsNetPresenter(this, Request.Method.GET, path, null, contextListener, contextErrorListener);
        newsPresenter.netRequest();
    }

    private void sendCommitNetVisit() {
        StringBuffer stringBuffer = new StringBuffer();
        SharePreUtils sharePreUtils = SharePreUtils.instanceShare(this);
        //token=  104caf549dcd76e7cdaf22b3a5c27f0e  &imei=0000000000011201&ctx="neirong";新闻评论访问地址;
        stringBuffer.append(Constance.PATH_NEWS_COMMIT).append(sharePreUtils.getToken("token"))
                .append("&imei=0000000000011201&ctx=").append(edtframe.getText().toString());
        newsNetPresenter = new NewsNetPresenter(this, Request.Method.GET, stringBuffer.toString(), null, listener, errorlistener);
        newsNetPresenter.netRequest();//启动网络访问

    }
    /**
     * 发送按钮的点击事件
     */
    @OnClick(R.id.commit_send_img)
    public void sendbtnclick(View view){
        sendCommitNetVisit();//点击事件发生后调用
    }

    Response.Listener listener=new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            Log.e("object", "onResponse: "+object.toString());
            edtframe.setText("");
            Gson gson=new Gson();
            CommitContext comimt = gson.fromJson(object.toString(), new TypeToken<CommitContext>() {
            }.getType());
            newsNetPresenter.cancelDialog();
            if(comimt!=null){
            String explain = comimt.getData().getExplain();
            Toast.makeText(CommitActivity.this, ""+explain, Toast.LENGTH_SHORT).show();
            }
        }
    };
    Response.ErrorListener errorlistener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(CommitActivity.this, "错了,发现了没,估计是没登陆", Toast.LENGTH_SHORT).show();
        }
    };
    Response.Listener contextListener=new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            Log.e("commimtobject", "onResponse: "+object.toString() );
            Gson gson=new Gson();
            TypeToken type=new TypeToken<ShowCommitContext>(){};
            ShowCommitContext commitcontext= gson.fromJson(object.toString(),type.getType());
            CommitAdapter comadapter=new CommitAdapter(CommitActivity.this);
            comadapter.addData(commitcontext.getData());
            mlstcommit.setAdapter(comadapter);
            newsPresenter.cancelDialog();

        }
    };
    Response.ErrorListener contextErrorListener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(CommitActivity.this, "又错了吧", Toast.LENGTH_SHORT).show();
        }
    };
}
