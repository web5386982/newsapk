package com.example.news.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class ShowCommitContext {

    /**
     * message : OK
     * status : 0
     * data : [{"uid":"web123","content":"sdsfdddddddddsd","stamp":"2017-01-03 23:58:51","cid":8464,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"web123","content":"SSSS","stamp":"2017-01-03 22:25:47","cid":8463,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"a??(??￡a????￡\")a?-","stamp":"2016-12-25 13:49:06","cid":8388,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"I just at home now because no one ....e..wo te me shuo bu xia qu le ","stamp":"2016-12-25 13:48:40","cid":8387,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"Merry Christmas !","stamp":"2016-12-25 13:46:07","cid":8386,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"so what are you doing now","stamp":"2016-12-25 13:45:38","cid":8385,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"b2b","stamp":"2016-12-25 13:44:52","cid":8384,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"betty08","content":"sfs","stamp":"2016-12-24 14:19:49","cid":8372,"portrait":"http://118.244.212.82:9092/Images/20161224042358.jpg"},{"uid":"asdf007","content":"gjkkhdd","stamp":"2016-12-23 16:12:34","cid":8365,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"eq","stamp":"2016-12-21 13:09:49","cid":8350,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"keke","stamp":"2016-12-21 12:44:24","cid":8349,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"hello,I am HanMeiMei.I am from China,Nice to meet you","stamp":"2016-12-19 11:18:02","cid":8326,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"??-???","stamp":"2016-12-12 11:26:21","cid":8291,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"??????????????","stamp":"2016-12-09 23:32:33","cid":8281,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"????????","stamp":"2016-12-09 23:29:48","cid":8280,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"????????","stamp":"2016-12-09 23:23:04","cid":8279,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"?????","stamp":"2016-12-09 23:13:56","cid":8278,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"ok","stamp":"2016-12-09 23:11:37","cid":8277,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"good_night","stamp":"2016-12-09 23:01:39","cid":8276,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"asdf007","content":"canyou????","stamp":"2016-12-09 22:53:05","cid":8275,"portrait":"http://118.244.212.82:9092/Images/image.png"}]
     */

    private String message;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : web123
         * content : sdsfdddddddddsd
         * stamp : 2017-01-03 23:58:51
         * cid : 8464
         * portrait : http://118.244.212.82:9092/Images/image.png
         */

        private String uid;
        private String content;
        private String stamp;
        private int cid;
        private String portrait;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStamp() {
            return stamp;
        }

        public void setStamp(String stamp) {
            this.stamp = stamp;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }
}
