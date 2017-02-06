package com.example.news.entity;

/**获取新闻的全部信息，内容类型不定，定义成泛型
 * Created by Administrator on 2016/12/25.
 */

public class News<T> {
    public String message;
    public int status;
    public T data;

    public News(String message, int status, T datas) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "News{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", datas=" + data +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getDatas() {
        return data;
    }

    public void setDatas(T datas) {
        this.data = datas;
    }
}
