package com.xunmeng.jpush.entity;

/**
 * Created by YCY on 2015/7/14.
 */
public class NotifyEntity {
    private String title;
    private String content;
    private int type;
    private String message;

    public NotifyEntity(String title, String content, int type, String message) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotifyEntity{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}
