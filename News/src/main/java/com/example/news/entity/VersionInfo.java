package com.example.news.entity;

/**
 * Created by Administrator on 2017/1/4.
 */

public class VersionInfo {


    /**
     * md5 : 9770ad0a9dc59fc51ec2db3079697536
     * pkgName : com.ys.android
     * version : 1
     * link : http://118.244.212.82:9092/Images/test.apk
     */

    private String md5;
    private String pkgName;
    private String version;
    private String link;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
