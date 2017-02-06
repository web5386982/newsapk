package com.example.news.unti;

import android.content.Context;
import android.content.SharedPreferences;

import edu.zx.slidingmenu.SlidingMenu;

/**用户令牌的收藏类
 * Created by Administrator on 2016/12/29.
 */

public class SharePreUtils {
  //用户令牌
  private  SharedPreferences sharePre;
    public static final String LOGIN="login";
    private String mputvalue;
    String mputkey;
    private  Context mcontext;
    private static SharePreUtils msharepte;
    private SharePreUtils(Context context){
        this.mcontext=context;
        sharePre = mcontext.getSharedPreferences(LOGIN, Context.CONTEXT_INCLUDE_CODE);
    }
    public static SharePreUtils instanceShare(Context context){
        if(msharepte==null){
            synchronized (SharePreUtils.class){
                if (msharepte==null){
                    msharepte=new SharePreUtils(context);

                }
            }
        }
        return msharepte;
    }

    public  void putToken(String putkey,String putvalue ){//登陆成功之后调用
        this.mputvalue=putvalue;
        this.mputkey=putkey;
        sharePre.edit().putString(putkey,putvalue).commit();
    }
    public String getToken(String skey){
        return sharePre.getString(skey,null);
    }
    /**
     * 删除数据库中的数据,在退出登录的点击事件中调用;
     */
    public void concelToken(String mputkey){
        sharePre.edit().putString(mputkey,null).commit();//删不了数据
    }

}
