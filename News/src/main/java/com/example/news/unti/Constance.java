package com.example.news.unti;

/**
 * Created by Administrator on 2016/12/25.
 */

public interface Constance {//接口中定义常量不需要加public static final;调用时直接用接口名点
    /**
     * 数据库名
     */
    String DB_FILE_NAME="collection.db";//一个文件
    /**
     * 数据库表名
     */
     String DB_TABLE_NAME="sortnews";
    /**
     * 数据库名版本
     */
    int BD_VERSION=1;
    /**
     * 表的列名
     */
    String TABLE_COLUMN_NID="nid";
    String TABLE_COLUMN_ICON="icon";
    String TABLE_COLUMN_SUMMARY="summary";
    String TABLE_COLUMN_TITLE="title";
    String TABLE_COLUMN_TIME="time";
    String TABLE_COLUMN_LINK="link";
    String TABLE_COLUMN_STAMP="stamp";
    /**
     * 日志的封装
     */
    String TAG="遇到我,是你的错!!!";
    /**
     * 新闻访问路径的封装
     */
    //注意细节，地址一定不能错。。。如果网络返回的结果在错误那边，多半是地址不对
    String PATH_BASE = "http://118.244.212.82:9092/newsClient";
    String PATH_NET_NEWS = PATH_BASE + "/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    String PATH_LOGIN_REGISTER = PATH_BASE + "/user_register?";
    String PATH_LOGIN_SURFACE=PATH_BASE+"/user_login?ver=1&";
    String PATH_LOGIN_USER_CENTER=PATH_BASE+"/user_home?ver=1&imei=0000000000011201&";
    String PATH_NEWS_SORT=PATH_BASE+"/news_sort?ver=1&imei=0000000000011201";
    String PATH_NEWS_COMMIT=PATH_BASE+"/cmt_commit?ver=1&nid=1&token=";
    String PATH_NEWS_VRESION_UPDATA=PATH_BASE+"/newsClient/update?imei=0000000000011201&pkg=";
//    cmt_list ?ver=1&nid=1&type=1&stamp=         yyyyMMdd&cid=1&dir=0&cnt=20
    String PATH_COMMIT_CONTEXT=PATH_BASE+"/cmt_list?ver=1&nid=1&type=1&stamp=";
     /**
     * 正则表达式
     */
    //返回的地址http://118.244.212.82:9092/newsClient/cmt_list ?ver=1&nid=1&type=1&stamp=20160617&cid=1&dir=1&cnt=20
//    正确地址 http://118.244.212.82:9092/newsClient/cmt_list?ver=1&nid=1&type=1&stamp=20150321 &cid=1&dir=1&cnt=20;
    //邮箱判断的正则表达式;
     String  EMAIL_JUDGE="([a-zA-Z0-9])+\\@(([a-zA-Z0-9])+\\.)+([a-zA-Z0-9]){2,5}";
    //用户账号判断的正则表达式
     String USER_ACCOUNT="[a-zA-Z0-9]{3,16}";
    //用户密码判断的正则表达式
     String PWD_JUDGE="[^\\s]{6,}";


}