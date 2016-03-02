package com.xunmeng.jpush.ui;

import android.app.Activity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/3/2.
 */
public class BaseActivity extends Activity {


    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
