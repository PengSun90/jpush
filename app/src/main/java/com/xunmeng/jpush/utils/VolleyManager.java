package com.xunmeng.jpush.utils;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * Created by YCY on 15/10/23.
 */
public class VolleyManager {
    private static RequestQueue requestQueue;

    private final static int TIME_OUT = 3000;

    private VolleyManager() {
    }

    public static RequestQueue getRequestQueue(Context context) {
        if (requestQueue == null) {
            synchronized (VolleyManager.class) {
                if (requestQueue == null) {
                    try {
                        requestQueue = Volley.newRequestQueue(context);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    requestQueue.start();
                }
            }
        }
        return requestQueue;
    }

    private static <T> void addRequest(Context context, RequestQueue requestQueue,
                                       Request<T> request) {
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

        File cacheDir = null;
        try {
            cacheDir = new File(context.getCacheDir(), "volley");
        } catch (Exception e) {
            e.printStackTrace();
        }
        DiskBasedCache cache = new DiskBasedCache(cacheDir);
        // clear all volley caches.
        requestQueue.add(new ClearCacheRequest(cache, null));

    }

    public static void sendJsonRequest(final Context context, int method, String url, JSONObject jsonRequest,
                                       final Response.Listener<JSONObject> listener,
                                       final Response.ErrorListener errorListener) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method,
                    url, jsonRequest, listener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = CommonUtils.getHeaderAccesstoken(context);
                    header.putAll(CommonUtils.getHeaderUid(context));
                    LogUtils.d("getHeaders = " + header.toString());
                    return header;
                }

                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                    try {
                        if (response.data.length > 1024 * 1024) {
                            //大于1m就不缓存了
                            setShouldCache(false);
                        }
                        Map<String, String> responseHeaders = response.headers;
                        String rawCookies = responseHeaders.get("Set-Cookie");
                        LogUtils.d("rawCookies = " + rawCookies);
                        if (!TextUtils.isEmpty(rawCookies))
                            PreferencesUtils.shareInstance(context).writeCookies(rawCookies);

                        LogUtils.d("response " + response.toString());
                    } catch (Exception e) {
                    }

                    return super.parseNetworkResponse(response);
                }
            };
            addRequest(context, getRequestQueue(context), jsonObjectRequest);
        } catch (Exception e) {
        }
    }

    public static void sendJsonRequest(final Context context, int method, String url, final Map<String, String> headerMap, JSONObject jsonRequest,
                                       final Response.Listener<JSONObject> listener,
                                       final Response.ErrorListener errorListener) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method,
                    url, jsonRequest, listener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = CommonUtils.getHeaderAccesstoken(context);
                    header.putAll(CommonUtils.getHeaderUid(context));
                    header.putAll(headerMap);
                    LogUtils.d("getHeaders = " + header.toString());
                    return header;
                }

                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                    try {
                        if (response.data.length > 1024 * 1024) {
                            //大于1m就不缓存了
                            setShouldCache(false);
                        }
                        Map<String, String> responseHeaders = response.headers;
                        String rawCookies = responseHeaders.get("Set-Cookie");
                        LogUtils.d("rawCookies = " + rawCookies);
                        if (!TextUtils.isEmpty(rawCookies))
                            PreferencesUtils.shareInstance(context).writeCookies(rawCookies);

                        LogUtils.d("response " + response.toString());
                    } catch (Exception e) {
                    }

                    return super.parseNetworkResponse(response);
                }
            };
            addRequest(context, getRequestQueue(context), jsonObjectRequest);
        } catch (Exception e) {
        }
    }

    public static void sendStringRequest(final Context context, int method, String url, final Map<String, String> headerMap,
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
                    Map<String, String> header = CommonUtils.getHeaderAccesstoken(context);
                    header.putAll(CommonUtils.getHeaderUid(context));
                    if (headerMap != null)
                        header.putAll(headerMap);
                    LogUtils.d("getHeaders = " + header.toString());
                    return header;
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {
                        if (response.data.length > 1024 * 1024) {
                            //大于1m就不缓存了
                            setShouldCache(false);
                        }
                        Map<String, String> responseHeaders = response.headers;
                        String rawCookies = responseHeaders.get("Set-Cookie");
                        LogUtils.d("rawCookies = " + rawCookies);
                        if (!TextUtils.isEmpty(rawCookies))
                            PreferencesUtils.shareInstance(context).writeCookies(rawCookies);

                        LogUtils.d("response " + response.toString());
                    } catch (Exception e) {
                    }

                    return super.parseNetworkResponse(response);
                }
            };
            addRequest(context, getRequestQueue(context), stringRequest);
        } catch (Exception e) {
        }

    }
}