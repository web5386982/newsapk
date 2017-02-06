package com.example.news.entity;

/**
 * Created by Administrator on 2016/12/28.
 */

public class LoginInfo {

    /**
     * data : {"explain":"登录成功","token":"104caf549dcd76e7cdaf22b3a5c27f0e","result":0}
     * message : OK
     * status : 0
     */

    private DataBean data;
    private String message;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * explain : 登录成功
         * token : 104caf549dcd76e7cdaf22b3a5c27f0e
         * result : 0
         */

        private String explain;
        private String token;
        private int result;

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
