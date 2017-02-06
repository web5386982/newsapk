package com.example.news;

import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/12/26.
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_main);
        alphoAnimation();
        delayTimeSkip();
    }

    /**
     * 延时跳转
     */
         public void delayTimeSkip(){
             Timer timer=new Timer();
             timer.schedule(new TimerTask() {
                 @Override
                 public void run() {

                    toActivity(MainActivity.class);
                 }
             }, 2300);
         }
/**
 *  渐变动画
 */

    public void alphoAnimation(){

    }
}
