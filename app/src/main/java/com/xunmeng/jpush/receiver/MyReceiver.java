package com.xunmeng.jpush.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.xunmeng.jpush.ui.activity.MainActivity;
import com.xunmeng.jpush.R;
import com.xunmeng.jpush.app.MY;
import com.xunmeng.jpush.entity.NotifyEntity;
import com.xunmeng.jpush.utils.LogUtils;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2016/2/25.
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPushReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            Log.d("MyReceiver", "[PushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
            if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                //打开通知栏
                showNotification(context, bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            }
            else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }


    /**
     * 通知
     *
     * @param context
     * @param bundle
     */
    public void showNotification(Context context, Bundle bundle) {
        try {
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            LogUtils.d(extra);
            NotifyEntity notifyEntity = new Gson().fromJson(extra, NotifyEntity.class);
            LogUtils.d("notifyEntity = " + notifyEntity);
            int type = notifyEntity.getType();
            String title = notifyEntity.getTitle();
            String content = notifyEntity.getContent();
            String message = notifyEntity.getMessage();

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent intent = new Intent(context, MainActivity.class);
            if (1 == type) {
//                intent.putExtra(IntentKeyConstant.activity_url, content);
            }

            Log.e("showNotification",title);
            Log.e("showNotification",content);
            Log.e("showNotification",message);
            Log.e("showNotification",""+type);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, MY.code, intent, PendingIntent.FLAG_ONE_SHOT);
            Notification notification = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {//兼容 4.0
                Notification.Builder builder = new Notification.Builder(context)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setSmallIcon(R.drawable.new_logo)
                        .setContentIntent(pendingIntent)
                        .setWhen(System.currentTimeMillis());
                notification = builder.getNotification();

            } else {//兼容4.1及以上
                notification = new Notification.Builder(context)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setSmallIcon(R.drawable.new_logo)
                        .setContentIntent(pendingIntent)
                        .setWhen(System.currentTimeMillis())
                        .build();

            }
            notification.defaults = Notification.DEFAULT_ALL;
            manager.notify(MY.code + 1, notification);
            MY.code++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}