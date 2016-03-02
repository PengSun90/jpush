package com.xunmeng.jpush.entity;

/**
 * Created by YCY on 2015/8/7.
 */
public class AppInfo {
    private String api_domain;
    private String app_domain;
    private String cur_version;
    private String min_version;
    private String web_app_version;

    public AppInfo(String api_domain, String app_domain, String cur_version, String min_version, String web_app_version) {
        this.api_domain = api_domain;
        this.app_domain = app_domain;
        this.cur_version = cur_version;
        this.min_version = min_version;
        this.web_app_version = web_app_version;
    }

    public String getWeb_app_version() {
        return web_app_version;
    }

    public void setWeb_app_version(String web_app_version) {
        this.web_app_version = web_app_version;
    }

    public String getApi_domain() {
        return api_domain;
    }

    public void setApi_domain(String api_domain) {
        this.api_domain = api_domain;
    }

    public String getApp_domain() {
        return app_domain;
    }

    public void setApp_domain(String app_domain) {
        this.app_domain = app_domain;
    }

    public String getCur_version() {
        return cur_version;
    }

    public void setCur_version(String cur_version) {
        this.cur_version = cur_version;
    }

    public String getMin_version() {
        return min_version;
    }

    public void setMin_version(String min_version) {
        this.min_version = min_version;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "api_domain='" + api_domain + '\'' +
                ", app_domain='" + app_domain + '\'' +
                ", cur_version='" + cur_version + '\'' +
                ", min_version='" + min_version + '\'' +
                ", web_app_version='" + web_app_version + '\'' +
                '}';
    }
}
