package com.xunmeng.jpush.entity;

/**
 * Created by YCY on 16/1/6.
 */
public class HybridPage {
    private String name;
    private String file;
    private boolean login;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "HybridPage{" +
                "name='" + name + '\'' +
                ", file='" + file + '\'' +
                ", login=" + login +
                '}';
    }
}
