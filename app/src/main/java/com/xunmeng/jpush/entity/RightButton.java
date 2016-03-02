package com.xunmeng.jpush.entity;

/**
 * Created by Eddie on 16/1/16.
 */
public class RightButton {

    private int icon;
    private String text;
    private String action;

    public RightButton(){

    }
    public RightButton(int icon, String text, String action) {
        this.icon = icon;
        this.text = text;
        this.action = action;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "RightButton{" +
                "icon=" + icon +
                ", text='" + text + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
