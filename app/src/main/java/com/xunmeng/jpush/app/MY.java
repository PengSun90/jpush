package com.xunmeng.jpush.app;

import android.app.Activity;
import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.xunmeng.jpush.utils.JPushUtils;
import com.xunmeng.jpush.utils.LocationManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/25.
 */
public class MY extends Application {

    public static int code = 0;
    private static MY my;
    private LocationManager locationManager;
    private List<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();

        my = this;

        startLocation();

        initUmengAnalysis();

        JPushUtils.JPushInterfaceInit(this);

    }


    private void initUmengAnalysis() {
        MobclickAgent.openActivityDurationTrack(false);
    }

    public static synchronized MY getAppInstance() {
        return my;
    }

    public void startLocation() {
        locationManager = LocationManager.getLocationManager();
        locationManager.startLocation();
    }


    /**
     * 添加Activity 到容器中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 关闭所有的activity
     */
    public void exit() {
        try {
            for (Activity activity : activityList) {
                activity.finish();
            }
            locationManager.stopLocation();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
