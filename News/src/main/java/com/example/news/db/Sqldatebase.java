package com.example.news.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.news.entity.NewsDetail;
import com.example.news.unti.Constance;

import java.util.ArrayList;
import java.util.List;

/**对数据库进行操作
 * Created by Administrator on 2017/1/3.
 */

public class Sqldatebase {
    public DbHelper dbHelper;//帮助类对象
    public static Sqldatebase msqldata;
    public SQLiteDatabase db;//数据库对象
    private Sqldatebase(Context context){
        dbHelper = new DbHelper(context);//拿到帮助类的对象
    }
    /**
     * 单利模式
     */
   public static Sqldatebase getdatabase(Context context){
       if(msqldata==null){
           synchronized (Sqldatebase.class){
               if(msqldata==null){
                   msqldata=new Sqldatebase(context);//拿到操作类的对象
               }
           }

       }
      return msqldata;
   }
    /**
     * 添加数据
     */
    public void addnews(NewsDetail newsDetail){
        db = dbHelper.getWritableDatabase();//拿到数据库对象
        ContentValues values = new ContentValues();//这就是一个hashmapd的封装类.意思就是创建一个集合
        values.put(Constance.TABLE_COLUMN_ICON,newsDetail.getIcon());
        values.put(Constance.TABLE_COLUMN_SUMMARY,newsDetail.getSummary());
        values.put(Constance.TABLE_COLUMN_LINK,newsDetail.getLink());
        values.put(Constance.TABLE_COLUMN_STAMP,newsDetail.getStamp());
        values.put(Constance.TABLE_COLUMN_NID,newsDetail.getNid());
        values.put(Constance.TABLE_COLUMN_TITLE,newsDetail.getTitle());
        db.insert(Constance.DB_TABLE_NAME,null,values);//数据插入数据库中
        db.close();

    }
    /**
     * 删除,根据ID删整行
     */
    public void deletenews(int nid){
        db = dbHelper.getWritableDatabase();//拿到数据库对象
        db.delete(Constance.DB_TABLE_NAME,Constance.TABLE_COLUMN_NID + "=?",
                new String[]{nid + ""});//"=?"这个表示可变参数,new String[]{nid + ""}这个是他所有的值的数组.""是因为前面的值是不确定的.
        db.close();
    }
    /**
     * 查询所有数据,相当于遍历,给收藏界面的listView提供数据
     */
    /**
     * 表名
     * 需要查询的列的名称 null:所有的列
     * 查询的条件 _id=？
     * 条件的参数
     * 分组
     * 分组条件
     * 排序 asc :升序 desc:降序eg："age asc"
     */
   public List<NewsDetail> favoritednewsinfo(){//这是一个遍历的过程,从数据库中遍历出来,添加到集合,其实数据库的存储方式就是集合
       List<NewsDetail> newsDetails=new ArrayList<NewsDetail>();
       db = dbHelper.getWritableDatabase();
       Cursor cursor = db.query(Constance.DB_TABLE_NAME, null, null, null, null, null, null);//数据库查询,相当于遍历
       while(cursor.moveToNext()){//cursor.moveToNext(),返回值是布尔值,右下一个返回true
           int nid = cursor.getInt(cursor.getColumnIndex(Constance.TABLE_COLUMN_NID));
           String summary = cursor.getString(cursor.getColumnIndex(Constance.TABLE_COLUMN_SUMMARY));//解释:获取字符串,根据行的下标,最后的括号中下标名
           String icon = cursor.getString(cursor.getColumnIndex(Constance.TABLE_COLUMN_ICON));
           String stamp = cursor.getString(cursor.getColumnIndex(Constance.TABLE_COLUMN_STAMP));
           String link = cursor.getString(cursor.getColumnIndex(Constance.TABLE_COLUMN_LINK));
           String title = cursor.getString(cursor.getColumnIndex(Constance.TABLE_COLUMN_TITLE));
           NewsDetail newsDetail = new NewsDetail(summary, stamp, link, icon, title,nid);
           newsDetails.add(newsDetail);//将这个数据添加给收藏得适配器就好了

       }
            cursor.close();
       return newsDetails;
   }

    /**
     * 单一数据查询,用来判断是否已收藏
     */
    public boolean searchnid(int nid){
        db = dbHelper.getWritableDatabase();
       Cursor cursor=db.query(Constance.DB_TABLE_NAME,null,null,null,null,null,null);
       while (cursor.moveToNext()){
           int newsnid =cursor.getInt(cursor.getColumnIndex(Constance.TABLE_COLUMN_NID));
           if(newsnid==nid){
               cursor.close();//存在返回true
               return true;
           }
       }
        cursor.close();
        return false;
    }

}
