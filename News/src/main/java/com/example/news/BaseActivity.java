package com.example.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/12/26.
 */

public class BaseActivity extends Activity {

    /**
     * 跳转,不带信息
     * @param cla
     */
    public void toActivity (Class cla){
        toActivity(cla,null);
    }

    /**
     * 带信息跳转
     * @param cla
     * @param bundle
     */
    public void toActivity(Class cla, Bundle bundle){

        Intent intent =new Intent(this,cla);//对象不能随便new能调用尽量调用;
        if(bundle!=null){
           intent.putExtra("bundle",bundle);
          }
         startActivity(intent,bundle);
//        finish();
        //继承了这个,所以这个还是属于当前类,,这里写的是跳转前的步骤哦;
    }

}
