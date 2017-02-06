package com.example.news.entity;

/**
 * Created by Administrator on 2016/12/28.
 */

public class Registers {

    /**
     * data : {"explain":"注册成功","token":"f3de9a0378f5d6a26b8ba9bb3444fca2","result":0}
     * message : OK
     * status : 0
     */

    public  DataBean data;
    public String message;
    public String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * explain : 注册成功
         * token : f3de9a0378f5d6a26b8ba9bb3444fca2
         * result : 0
         */

        public static String explain;
        public static String token;
        public static int result;

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }
}
