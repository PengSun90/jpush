package com.xunmeng.jpush.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.app.MY;
import com.xunmeng.jpush.utils.LogUtils;
import com.xunmeng.jpush.utils.StatusBarCompat;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.d("");
        super.onCreate(savedInstanceState);
//       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
//        MY.getAppInstance().addActivity(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,
                        testactivity.class));
//                finish();
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {

        MY.getAppInstance().exit();
        super.onBackPressed();
    }
}
