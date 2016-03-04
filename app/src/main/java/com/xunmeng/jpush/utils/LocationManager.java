package com.xunmeng.jpush.utils;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xunmeng.jpush.app.MY;

/**
 * Created by YCY on 2015/11/25
 */
public class LocationManager {
    private static LocationManager locationManager;
    private LocationClient mLocationClient;
    private static boolean hasReLocation = false;
    private static int locationCount = 0;

    private LocationManager() {
        initLocation(1000);
    }

    public static LocationManager getLocationManager() {
        if (locationManager == null) {
            synchronized (LocationManager.class) {
                if (locationManager == null) {
                    locationManager = new LocationManager();
                }
            }
        }
        return locationManager;
    }

    private void initLocation(int scantime) {
        LogUtils.d("initLocation ");
        mLocationClient = new LocationClient(MY.getAppInstance().getApplicationContext());
        LogUtils.d("initLocation @@1");
        mLocationClient.registerLocationListener(new MyBdLocationlistenter());
        LogUtils.d("initLocation @@2");
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        LogUtils.d("initLocation @@3");
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        LogUtils.d("initLocation@@4 ");
        LogUtils.d("initLocation @@5");
        option.setPriority(LocationClientOption.GpsFirst);
        LogUtils.d("initLocation@@6 ");
        option.setScanSpan(scantime);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        LogUtils.d("initLocation @@7");
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        LogUtils.d("initLocation@@8 ");
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        LogUtils.d("initLocation@@9 ");
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        LogUtils.d("initLocation @@10");
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        LogUtils.d("initLocation@@11 ");
        option.disableCache(false);//禁止启用缓存定位
        LogUtils.d("initLocation@@12 ");
        mLocationClient.setLocOption(option);
        LogUtils.d("initLocation@@13 ");
    }

    public void startLocation() {
        if (null != mLocationClient)
            mLocationClient.start();

        LogUtils.d("baidu startLocation ");
    }

    public void stopLocation() {
        if (null != mLocationClient)
            mLocationClient.stop();

        LogUtils.d("baidu stopLocation ");
    }

    public class MyBdLocationlistenter implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            PreferencesUtils phhPreferences = PreferencesUtils.shareInstance();
            String longitude = "", latitude = "", province = "", city = "", district = "", addr = "";
            float radius = 0.0f;
            if (null != location) {
                radius = location.getRadius();
                longitude = String.valueOf(location.getLongitude());
                latitude = String.valueOf(location.getLatitude());
                province = location.getProvince();
                city = location.getCity();
                district = location.getDistrict();
                addr = location.getAddrStr();

                phhPreferences.writeLocation(longitude, latitude, province, city, district, addr);
            }

            if (locationCount < 2) {
                locationCount++;
            }

            if (locationCount >= 2 && !hasReLocation) {
                hasReLocation = true;
                stopLocation();
                //之后每3分钟定一次
                initLocation(180000);
                startLocation();
            }

            LogUtils.d("baidu location longitude = " + longitude + " , latitude = " + latitude
                    + " , province = " + province + " , city = " + city + " , district = " + district
                    + " location = " + location + " , radius = " + radius + " ,locationCount = " + locationCount);

        }
    }
}
