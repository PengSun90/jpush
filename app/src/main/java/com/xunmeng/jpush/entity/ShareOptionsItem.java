package com.xunmeng.jpush.entity;

import java.io.Serializable;

/**
 * Created by YCY on 15/12/29.
 */
public class ShareOptionsItem implements Serializable {
    private double x;
    private double y;
    private double width;//比例
    private double height;//比例
    /**
     * item类型，支持rectangle/text/logo/qrcode(容错30）/share_title/
     * share_description/share_image/share_thumbnail，share_image从分享参数"image"取得URL
     */
    private String source;
    private int fill; // 用于rectangle类型，表示填充颜色
    private int font_size;// 文字大小
    private int font_color;
    private boolean bold_font;// 是否加粗
    private String data;//"长按二维码识别即可参团！"  text类型使用，指定文字
    private boolean alignment_center;// 是否水平居中，true则居中，false则靠左,垂直始终居中

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getFill() {
        return fill;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public int getFont_size() {
        return font_size;
    }

    public void setFont_size(int font_size) {
        this.font_size = font_size;
    }

    public int getFont_color() {
        return font_color;
    }

    public void setFont_color(int font_color) {
        this.font_color = font_color;
    }

    public boolean isBold_font() {
        return bold_font;
    }

    public void setBold_font(boolean bold_font) {
        this.bold_font = bold_font;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isAlignment_center() {
        return alignment_center;
    }

    public void setAlignment_center(boolean alignment_center) {
        this.alignment_center = alignment_center;
    }

    @Override
    public String toString() {
        return "ShareOptionsItem{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", source='" + source + '\'' +
                ", fill='" + fill + '\'' +
                ", font_size=" + font_size +
                ", font_color='" + font_color + '\'' +
                ", bold_font=" + bold_font +
                ", data='" + data + '\'' +
                ", alignment_center=" + alignment_center +
                '}';
    }
}
