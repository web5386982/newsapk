package com.example.news.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/1.
 */

public class NewsSort {


    /**
     * message : OK
     * status : 0
     * data : [{"subgrp":[{"subgroup":"军事","subid":1},{"subgroup":"社会","subid":2}],"gid":1,"group":"新闻"},{"subgrp":[{"subgroup":"股票","subid":3},{"subgroup":"基金","subid":4}],"gid":2,"group":"财经"},{"subgrp":[{"subgroup":"手机","subid":5}],"gid":3,"group":"科技"},{"subgrp":[{"subgroup":"NBA","subid":8},{"subgroup":"英超","subid":7}],"gid":4,"group":"体育"}]
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
         * subgrp : [{"subgroup":"军事","subid":1},{"subgroup":"社会","subid":2}]
         * gid : 1
         * group : 新闻
         */

        private int gid;
        private String group;//group分类名
        private List<SubgrpBean> subgrp;

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public List<SubgrpBean> getSubgrp() {
            return subgrp;
        }

        public void setSubgrp(List<SubgrpBean> subgrp) {
            this.subgrp = subgrp;
        }

        public static class SubgrpBean {
            /**
             * subgroup : 军事
             * subid : 1
             */

            private String subgroup;//子类名称
            private int subid;

            public String getSubgroup() {
                return subgroup;
            }

            public void setSubgroup(String subgroup) {
                this.subgroup = subgroup;
            }

            public int getSubid() {
                return subid;
            }

            public void setSubid(int subid) {
                this.subid = subid;
            }
        }
    }
}
