package com.example.news.unti;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/28.
 */

public class DataUtils {
    /**
     * 注册时需要拼接的数据;
     */
    public static String registerData(Map<String, String> datas) {
        //    ver=1&uid=用户昵称&email=邮箱地址&pwd=5386982";
        StringBuffer buffer = new StringBuffer();
          buffer.append("ver=1&");
        for (Map.Entry<String, String> entry : datas.entrySet()) {
            buffer.append(entry.getKey()).append("=")
                    .append(entry.getValue()).append("&");
        }
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }

    /**
     * 登陆时get方式访问网络,路径数据的添加
     * @param datas
     * @return
     */
    public static String loginData(Map<String, String> datas) {
        // 需拼接的路径:  uid = 用户名 &   pwd = 密码 &    device=0;
        //device=0表示用手机登陆,,=1表示用电脑登陆
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> entry : datas.entrySet()) {
            buffer.append(entry.getKey()).append("=")
                    .append(entry.getValue()).append("&");
        }
        buffer.append("device=0");
        return buffer.toString();
    }
    public static String userData(Map<String, String> datas) {
        // token=104caf549dcd76e7cdaf22b3a5c27f0e
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> entry : datas.entrySet()) {
            buffer.append(entry.getKey()).append("=")
                    .append(entry.getValue());
        }
        return buffer.toString();
    }



}
