package com.xunmeng.jpush.config;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xunmeng.jpush.app.MY;
import com.xunmeng.jpush.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Psun on 15/10/23.
 */
public class VolleyManager {
    private static RequestQueue requestQueue;

    private final static int TIME_OUT = 3000;

    //使用正则表达式从reponse的头中提取cookie内容的子串
    private static final Pattern pattern = Pattern.compile("Set-Cookie\\d*");

    private VolleyManager() {
    }

    public static RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            synchronized (VolleyManager.class) {
                if (requestQueue == null) {
                    requestQueue = Volley.newRequestQueue(MY.getAppInstance());
                    requestQueue.start();
                }
            }
        }
        return requestQueue;
    }

    private static <T> void addRequest(RequestQueue requestQueue,
                                       Request<T> request) {
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

        File cacheDir = new File(MY.getAppInstance().getCacheDir(), "volley");
        DiskBasedCache cache = new DiskBasedCache(cacheDir);
        // clear all volley caches.
        requestQueue.add(new ClearCacheRequest(cache, null));

    }

    public static void sendJsonRequest(int method, final String url, JSONObject jsonRequest,
                                       final Response.Listener<JSONObject> listener,
                                       final Response.ErrorListener errorListener) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method,
                    url, jsonRequest, listener, errorListener) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    return super.getHeaders();
                }

                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                    try {
                        if (response.data.length > 1024 * 1024) {
                            //大于1m就不缓存了
                            setShouldCache(false);
                        }
                        // get all cookie
                        Map<String, String> headers = response.headers;

//                        for (String key : headers.keySet()) {
//                            Matcher matcher = pattern.matcher(key);
//                            if (matcher.find()) {
//                                String cookieFromResponse = headers.get(key);
//                                LogUtils.d("cookieFromResponse = " + cookieFromResponse);
//                                if (!TextUtils.isEmpty(cookieFromResponse)) {
//                                    CookieStoreManager.getInstance().addCookie(cookieFromResponse);
//                                }
//                            }
//                        }
                    } catch (Exception e) {
                    }

                    return super.parseNetworkResponse(response);
                }
            };
            addRequest(getRequestQueue(), jsonObjectRequest);
        } catch (Exception e) {
        }
    }

    public static void sendStringRequest(int method, final String url, final Map<String, String> headerMap,
                                         final String params,
                                         final Response.Listener<String> listener,
                                         final Response.ErrorListener errorListener) {
        try {
            StringRequest stringRequest = new StringRequest(method, url, listener, errorListener) {
                @Override
                public byte[] getBody() throws AuthFailureError {
                    LogUtils.d("getBody " + params);
                    if (TextUtils.isEmpty(params)) return super.getBody();
                    return params.getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> headers = new HashMap<>();
//                    headers.putAll(super.getHeaders());
//                    headers.putAll(headerMap);
//                    List<HttpCookie> allHttpCookie = CookieStoreManager.getInstance().getAllHttpCookie(url);
//                    StringBuilder sb = new StringBuilder();
//                    for (HttpCookie httpCookie : allHttpCookie) {
//                        sb.append(httpCookie.getValue() + ";");
//                    }
//                    headers.put("Cookie", sb.toString());
                    return super.getHeaders();
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {
                        if (response.data.length > 1024 * 1024) {
                            //大于1m就不缓存了
                            setShouldCache(false);
                        }
                        // get all cookie
                        Map<String, String> headers = response.headers;
//
//                        for (String key : headers.keySet()) {
//                            Matcher matcher = pattern.matcher(key);
//                            if (matcher.find()) {
//                                String cookieFromResponse = headers.get(key);
//                                LogUtils.d("cookieFromResponse = " + cookieFromResponse);
//                                if (!TextUtils.isEmpty(cookieFromResponse)) {
//                                    CookieStoreManager.getInstance().addCookie(cookieFromResponse);
//                                }
//                            }
//                        }
                    } catch (Exception e) {
                    }

                    return super.parseNetworkResponse(response);
                }
            };
            addRequest(getRequestQueue(), stringRequest);
        } catch (Exception e) {
        }

    }

    public static void sendJsonArrayRequest(int method, final String url, JSONObject jsonRequest,
                                            final Response.Listener<JSONArray> listener,
                                            final Response.ErrorListener errorListener) {
        try {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(method,
                    url, jsonRequest, listener, errorListener) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> headers = new HashMap<>();
//                    headers.putAll(super.getHeaders());
//                    List<HttpCookie> allHttpCookie = CookieStoreManager.getInstance().getAllHttpCookie(url);
//                    StringBuilder sb = new StringBuilder();
//                    for (HttpCookie httpCookie : allHttpCookie) {
//                        sb.end(httpCookie.getValue() + ";");
//                    }
//                    headers.put("Cookie", sb.toString());

//                    Map<String, String> headers = new HashMap<>();
//                    headers.put("AccessToken", Api.getAccessToken());
                    return super.getHeaders();

                }

                @Override
                protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                    try {
                        if (response.data.length > 1024 * 1024) {
                            //大于1m就不缓存了
                            setShouldCache(false);
                        }
                        // get all cookie
//                        Map<String, String> headers = response.headers;

//                        for (String key : headers.keySet()) {
//                            Matcher matcher = pattern.matcher(key);
//                            if (matcher.find()) {
//                                String cookieFromResponse = headers.get(key);
////                                LogUtils.d("cookieFromResponse = " + cookieFromResponse);
//                                if (!TextUtils.isEmpty(cookieFromResponse)) {
//                                    CookieStoreManager.getInstance().addCookie(cookieFromResponse);
//                                }
//                            }
//                        }
                    } catch (Exception e) {
                    }

                    return super.parseNetworkResponse(response);
                }
            };
            addRequest(getRequestQueue(), jsonArrayRequest);
        } catch (Exception e) {
        }
    }

}