package com.xunmeng.jpush.ui;

import android.support.v4.app.FragmentActivity;

import com.umeng.analytics.MobclickAgent;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/3/2.
 */
public class BaseActivity extends FragmentActivity {


    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
        MobclickAgent.onPause(this);
    }
}
