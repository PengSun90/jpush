package com.xunmeng.jpush.entity;

import java.io.Serializable;

/**
 * Created by YCY on 2015/7/9.
 */
public class ShareData implements Serializable {
    private String title;
    private String desc;
    private String thumbnail;
    private String share_url;
    private String thumbnail_width;
    private String image;
    private ShareOptions options;

    public ShareData(String title, String desc, String thumbnail, String share_url) {
        this.title = title;
        this.desc = desc;
        this.thumbnail = thumbnail;
        this.share_url = share_url;
    }

    public ShareData(String title, String desc, String thumbnail, String share_url, String thumbnail_width) {
        this.title = title;
        this.desc = desc;
        this.thumbnail = thumbnail;
        this.share_url = share_url;
        this.thumbnail_width = thumbnail_width;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail_width() {
        return thumbnail_width;
    }

    public void setThumbnail_width(String thumbnail_width) {
        this.thumbnail_width = thumbnail_width;
    }

    public ShareOptions getOptions() {
        return options;
    }

    public void setOptions(ShareOptions options) {
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    @Override
    public String toString() {
        return "ShareData{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", share_url='" + share_url + '\'' +
                ", thumbnail_width='" + thumbnail_width + '\'' +
                ", image='" + image + '\'' +
                ", options=" + options +
                '}';
    }
}
