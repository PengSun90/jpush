package com.xunmeng.jpush.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YCY on 15/12/29.
 */
public class ShareOptions implements Serializable {
    private long max_size;// 分享出去图片的最大数据大小(byte)
    private int width;// 长宽计量单位使用像素
    private int height;
    private List<ShareOptionsItem> items;

    public long getMax_size() {
        return max_size;
    }

    public void setMax_size(long max_size) {
        this.max_size = max_size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<ShareOptionsItem> getItems() {
        return items;
    }

    public void setItems(List<ShareOptionsItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShareOptions{" +
                "max_size=" + max_size +
                ", width=" + width +
                ", height=" + height +
                ", items=" + items +
                '}';
    }
}
