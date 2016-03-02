package com.xunmeng.jpush.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YCY on 16/1/6.
 */
public class HybridConfig implements Serializable {
    private String id;
    private String version;
    private List<HybridPage> pages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<HybridPage> getPages() {
        return pages;
    }

    public void setPages(List<HybridPage> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "HybridConfig{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", pages=" + pages +
                '}';
    }
}
