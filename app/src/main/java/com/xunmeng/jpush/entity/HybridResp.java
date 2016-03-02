package com.xunmeng.jpush.entity;

import java.io.Serializable;

/**
 * Created by YCY on 16/1/7.
 */
public class HybridResp implements Serializable {
    private long id;
    private String appKey;
    private String appName;
    private String version;//组件包版本
    private String url;//组件包地址
    private long down;//下载次数
    private String md5;//压缩包大小md5值
    private boolean isForce;//是否强更

    public HybridResp() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }

    public boolean isForce() {
        return isForce;
    }

    public void setIsForce(boolean isForce) {
        this.isForce = isForce;
    }

    @Override
    public String toString() {
        return "HybridResp{" +
                "id=" + id +
                ", appKey='" + appKey + '\'' +
                ", appName='" + appName + '\'' +
                ", version='" + version + '\'' +
                ", url='" + url + '\'' +
                ", down=" + down +
                ", md5='" + md5 + '\'' +
                ", isForce=" + isForce +
                '}';
    }
}
