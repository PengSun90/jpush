package com.xunmeng.jpush.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.app.MY;
import com.xunmeng.jpush.utils.LogUtils;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.d("");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
//        MY.getAppInstance().addActivity(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,
                        MainActivity.class));
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
