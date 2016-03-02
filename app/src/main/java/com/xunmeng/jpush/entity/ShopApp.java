package com.xunmeng.jpush.entity;

/**
 * Created by YCY on 15/6/23.
 */
public class ShopApp {
    private String shop_app_id;
    private String app_id;
    private String provider;
    private String type;

    public void setShop_app_id(String shop_app_id) {
        this.shop_app_id = shop_app_id;
    }

    public String getShop_app_id() {
        return this.shop_app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_id() {
        return this.app_id;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "{\"shop_app_id\":\"" + this.shop_app_id
                + "\", \"app_id\":\"" + this.app_id + "\", \"provider\":\"" + this.provider + "\", \"type\":\"" + this.type + "\"}";
    }
}
