package com.xunmeng.jpush.app;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.xunmeng.jpush.utils.JPushUtils;

/**
 * Created by Administrator on 2016/2/25.
 */
public class MY extends Application {

    public static int code = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        initUmengAnalysis();
        JPushUtils.JPushInterfaceInit(this);
    }
    private void initUmengAnalysis() {
        MobclickAgent.openActivityDurationTrack(false);
    }
}
