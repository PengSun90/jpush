package com.xunmeng.jpush.entity;

import java.util.List;

/**
 * Created by YCY on 15/6/23.
 */
public class Shop {
    private String shop_id;
    private String shop_selector; // 华南，华东
    private List<ShopApp> apps;
    private String shop_domain;
    private String api_domain;
    private String shop_desc;

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_id() {
        return this.shop_id;
    }

    public void setShop_selector(String shop_selector) {
        this.shop_selector = shop_selector;
    }

    public String getShop_selector() {
        return this.shop_selector;
    }

    public String getShop_domain() {
        return this.shop_domain;
    }

    public void setShop_domain(String shop_domain) {
        this.shop_domain = shop_domain;
    }

    public String getApi_domain() {
        return this.api_domain;
    }

    public void setApi_domain(String api_domain) {
        this.api_domain = api_domain;
    }

    public String getShop_desc() {
        return this.shop_desc;
    }

    public void setShop_desc(String desc) {
        this.shop_desc = desc;
    }
    public void setApps(List<ShopApp> apps) {
        this.apps = apps;
    }

    public List<ShopApp> getApps() {
        return this.apps;
    }

    @Override
    public String toString() {
        return "{\"shop_id\":\"" + this.shop_id + "\", \"shop_selector\":\"" + this.shop_selector
                + "\", \"apps\":" + this.apps + "}";
    }
}
