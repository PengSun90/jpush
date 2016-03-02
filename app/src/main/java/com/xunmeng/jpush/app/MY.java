package com.xunmeng.jpush.app;

import android.app.Application;

import com.xunmeng.jpush.utils.JPushUtils;

/**
 * Created by Administrator on 2016/2/25.
 */
public class MY extends Application {

    public static int code = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        JPushUtils.JPushInterfaceInit(this);
    }
}
