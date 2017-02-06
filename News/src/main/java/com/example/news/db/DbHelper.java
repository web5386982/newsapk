package com.example.news.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import com.example.news.unti.Constance;

/**
 * Created by Administrator on 2017/1/3.
 */

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(Context context) {
        //在这创建数据库,,,父类的里面下面输入的参数不用传,就直留context了
        super(context,Constance.DB_FILE_NAME, null, Constance.BD_VERSION);
    }

    /**
     * 创建表格
     * @param db
     * db就是创建好的数据库文件对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Constance.DB_TABLE_NAME+"("+Constance.TABLE_COLUMN_NID
                +" integer,"+Constance.TABLE_COLUMN_ICON+" text,"+Constance.TABLE_COLUMN_SUMMARY+" text,"
                +Constance.TABLE_COLUMN_TITLE+" text,"+Constance.TABLE_COLUMN_STAMP+" text,"+Constance.TABLE_COLUMN_LINK
                +" text,"+Constance.TABLE_COLUMN_TIME+" text"+")");
    }

    /**
     * 更新版本
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
