package com.xunmeng.jpush.utils;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by YCY on 2015/7/14.
 */
public class JPushUtils {

    private static final String TAG = "JPushUtils";

    public static void JPushInterfaceInit(Context context) {

        try {
            JPushInterface.init(context.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * RegistrationID
     */
    public static void sendDeviceToken(Context context, String jpushAddress) {
        try {
//            final String jpushAddress = PDDApi.getJpushAddress();
            LogUtils.d("sendDeviceToken , " + jpushAddress);
            if (TextUtils.isEmpty(jpushAddress)) {
                return;
            }
            String deviceToken = JPushUtils.getDeviceToken(context);
            if (TextUtils.isEmpty(deviceToken)) {
                return;
            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("registration_id", deviceToken);
            jsonObject.put("uuid", CommonUtils.getUUID(context));
            jsonObject.put("platform", "android");

            VolleyManager.sendJsonRequest(context, Request.Method.POST,
                    jpushAddress, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            LogUtils.d(TAG, "sendDeviceToken onResponse " + jsonObject.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            volleyError.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * RegistrationID
     *
     * @return
     */
    public static String getDeviceToken(Context context) {
        String device_token = JPushInterface.getRegistrationID(context);
        PreferencesUtils.shareInstance(context).writeDeviceToken(device_token);
        LogUtils.d(TAG, "device_token = " + device_token);
        return device_token;
    }
}
