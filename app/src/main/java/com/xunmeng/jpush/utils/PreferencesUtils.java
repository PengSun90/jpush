package com.xunmeng.jpush.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by YCY on 15/6/10.
 */


public class PreferencesUtils {

    public interface Key {
        String applicationLaunched = "applicationLaunched";
        String shop = "shop";
        String loginType = "logintype";
        String payTypeLastest = "pay_type_lastest";

        String accessToken = "accessToken";
        String appId = "appId";
        String cookies = "cookies";
        String longitude = "longitude";
        String latitude = "latitude";

        String webviewWidth = "webviewWidth";
        String webviewHeight = "webviewHeight";
        String deviceId = "deviceId";
        String macAddress = "macAddress";
        String simSerialNumber = "simSerialNumber";
        String serialNumber = "serialNumber";
        String androidId = "androidId";

        //js key prefix
        String jsCommonPrefix = "jsCommonKey_";
        String jsSecurePrefix = "jsSecureKey_";
        String app_info = "app_info";
        String uuid = "device_uuid";

        String province = "province";
        String city = "city";
        String district = "district";
        String addrStr = "addrStr";
        String userEntity = "userEntity";
        String cur_hybrid_version = "cur_hybrid_version";
        String pre_hybrid_version = "pre_hybrid_version";
        String device_token = "device_token";
    }

    private static PreferencesUtils mInstance;
    private SharedPreferences mSp;

    private PreferencesUtils() {
    }

    public static synchronized PreferencesUtils shareInstance(Context context) {
        if (null == mInstance) {
            mInstance = new PreferencesUtils();
            try {
                mInstance.mSp = context.getSharedPreferences(
                        context.getPackageName(), Context.MODE_PRIVATE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mInstance;
    }

    /**
     * 记录app是否第一次安装打开
     */
    public void writeApplicationHaveLaunched() {
        SharedPreferences.Editor editor = this.mSp.edit();
        editor.putInt(Key.applicationLaunched, 1024);
        editor.commit();
    }

    public boolean readIsApplicationFirstLaunched() {
        int is1024 = this.mSp.getInt(Key.applicationLaunched, 0);
        if (1024 == is1024) {
            return false;
        } else {
            return true;
        }
    }

    // 1 alipay 2 wxpay
    public int readLastPayType() {
        return this.mSp.getInt(Key.payTypeLastest, 2);
    }

    public void writeLastPaytype(int payType) {
        SharedPreferences.Editor editor = this.mSp.edit();
        editor.putInt(Key.payTypeLastest, payType);
        editor.commit();
    }

    public void writeLoginType(int type) {
        SharedPreferences.Editor editor = this.mSp.edit();
        editor.putInt(Key.loginType, type);
        editor.commit();
    }


    public void writeUserEntity(String userEntityStr) {
        SharedPreferences.Editor editor = this.mSp.edit();
        LogUtils.d("writeUserEntity = " + userEntityStr);
        editor.putString(Key.userEntity, userEntityStr);
        editor.commit();
    }

    public void writeAccessToken(String token) {
        SharedPreferences.Editor editor = this.mSp.edit();
        editor.putString(Key.accessToken, token);
        editor.commit();
    }

    public String readAccessToken() {
        return this.mSp.getString(Key.accessToken, "");
    }

    public String readAppId() {
        return this.mSp.getString(Key.appId, "");
    }

    public void writeAppId(String id) {
        SharedPreferences.Editor editor = this.mSp.edit();
        editor.putString(Key.appId, id);
        editor.commit();
    }

    public void writeCookies(String cookies) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.cookies, cookies);
        editor.commit();
    }

    public String getCookies() {
        return mSp.getString(Key.cookies, "");
    }

    public void writeLocation(String longitude, String latitude,
                              String province, String city, String district, String addrStr) {
        SharedPreferences.Editor edit = mSp.edit();
        edit.putString(Key.longitude, longitude);
        edit.putString(Key.latitude, latitude);
        edit.putString(Key.province, province);
        edit.putString(Key.city, city);
        edit.putString(Key.district, district);
        edit.putString(Key.addrStr, addrStr);
        edit.commit();
    }

    public String readLongitude() {
        return mSp.getString(Key.longitude, "");
    }

    public String readLatitude() {
        return mSp.getString(Key.latitude, "");
    }

    public String readProvince() {
        return mSp.getString(Key.province, "");
    }

    public String readCity() {
        return mSp.getString(Key.city, "");
    }

    public String readDistrict() {
        return mSp.getString(Key.district, "");
    }

    public String readAddr() {
        return mSp.getString(Key.addrStr, "");
    }

    public String readJSCommonParams(String key) {
        return mSp.getString(key, "");
    }

    public void writeJSCommonParams(String jsKey, String jsValue) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(jsKey, jsValue);
        editor.commit();
    }

    public void writeJSAccesstoken(String access_token) {
        writeJSCommonParams(Key.jsSecurePrefix + "access_token", access_token);
    }

    public String readJSAccesstoken() {
        return readJSCommonParams(Key.jsSecurePrefix + "access_token");
    }


    public void writeAppInfo(String app_info) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.app_info, app_info);
        editor.commit();
    }

    public String readAppInfo() {
        return mSp.getString(Key.app_info, "");
    }

    public String readUUID() {
        return mSp.getString(Key.uuid, "");
    }

    public void writeUUID(String muuid) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.uuid, muuid);
        editor.commit();
    }

    public int readWebviewWidth() {
        return mSp.getInt(Key.webviewWidth, -1);
    }

    public int readWebviewHeight() {
        return mSp.getInt(Key.webviewHeight, -1);
    }

    public void writeWebviewMetrics(int width, int height) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putInt(Key.webviewWidth, width);
        editor.putInt(Key.webviewHeight, height);
        editor.commit();
    }

    public String readDeviceId() {
        return mSp.getString(Key.deviceId, "");
    }

    public void writeDeviceId(String deviceId) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.deviceId, deviceId);
        editor.commit();
    }

    public String readMacAddress() {
        return mSp.getString(Key.macAddress, "");
    }

    public void writeMacAddress(String macAddress) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.macAddress, macAddress);
        editor.commit();
    }

    public String readSimSerialNumber() {
        return mSp.getString(Key.simSerialNumber, "");
    }

    public void writeSimSerialNumber(String simSerialNumber) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.simSerialNumber, simSerialNumber);
        editor.commit();
    }

    public String readSerialNumber() {
        return mSp.getString(Key.serialNumber, "");
    }

    public void writeSerialNumber(String serialNumber) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.serialNumber, serialNumber);
        editor.commit();
    }

    public String readAndroidId() {
        return mSp.getString(Key.androidId, "");
    }

    public void writeAndroidId(String androidId) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.androidId, androidId);
        editor.commit();
    }

    public void writeCurHybridVersion(String version) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.cur_hybrid_version, version);
        editor.commit();
    }

    public String readCurHybridVersion() {
        return mSp.getString(Key.cur_hybrid_version, "");
    }

    public void writePreHybridVersion(String version) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.pre_hybrid_version, version);
        editor.commit();
    }

    public String readPreHybridVersion() {
        return mSp.getString(Key.pre_hybrid_version, "");
    }

    public void writeDeviceToken(String version) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.device_token, version);
        editor.commit();
    }

    public String readDeviceToken() {
        return mSp.getString(Key.device_token, "");
    }
}
