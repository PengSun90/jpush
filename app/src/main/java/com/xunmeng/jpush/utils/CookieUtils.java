package com.xunmeng.jpush.utils;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;


/**
 * Created by YCY on 2015/7/14.
 */
public class CookieUtils {
    /**
     * 同步一下cookie
     */
    public static void synCookies(Context context, String url) {
        LogUtils.d("CookieUtils synCookies");
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        String cookies = PreferencesUtils.shareInstance().getCookies();
        LogUtils.d("synCookies cookies = " + cookies);
//        cookieManager.setCookie(PDDConfig.getApiDomain(), cookies);
        cookieManager.setCookie(url, cookies);
        CookieSyncManager.getInstance().sync();
    }

    public static void clearAllCookies(Context context) {
        LogUtils.d("CookieUtils clearAllCookies");
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public static void clearCookies(Context context, String url) {
        LogUtils.d("CookieUtils clearCookies");
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        String cookies = "";
        LogUtils.d("synCookies cookies = " + cookies);
//        cookieManager.setCookie(PDDConfig.getApiDomain(), cookies);
        cookieManager.setCookie(url, cookies);
        CookieSyncManager.getInstance().sync();
    }

    public static String getAPICookies(Context context) {
        String apiCookies = PreferencesUtils.shareInstance().getCookies();
        LogUtils.d("apiCookies = " + apiCookies);
        return apiCookies;
    }
}
