package com.xunmeng.jpush.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取版本名称
     *
     * @return
     */
    public static String getVersionName(Context context) {
        PackageInfo packInfo = null;
        try {
            context = context.getApplicationContext();
            PackageManager packageManager = context.getPackageManager();
            packInfo = packageManager.getPackageInfo(context
                    .getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机系统版本
     *
     * @return
     */
    public static String getSystemName() {
        return "Android " + android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取应用包名
     *
     * @param context
     * @return
     */
    public static String getAppPackName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取渠道名
     *
     * @param ctx 此处习惯性的设置为activity，实际上context就可以
     * @return 如果没有获取成功，那么返回值为空
     */
    public static String getChannelName(Context ctx) {
        if (ctx == null) {
            return null;
        }
        String channelName = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData.getString("UMENG_CHANNEL");
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        LogUtils.d("c = " + channelName);
        return channelName;
    }

    /**
     * 检查网络
     *
     * @param context
     * @return
     */
    public static boolean checkNetState(Context context) {

        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {

                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }

    public static boolean checkHasInstalledApp(Context context, String packname) {
        boolean hasInstalled = false;
        List<ApplicationInfo> appList = context.getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo info : appList) {
            if (info.packageName.equals(packname)) {
                hasInstalled = true;
                break;
            }
        }
        return hasInstalled;
    }


    public static Map<String, String> getHeaderAccesstoken(Context context) {
        Map<String, String> headerAccesstoken = new HashMap<String, String>();
        String token = PreferencesUtils.shareInstance(context).readJSAccesstoken();
        headerAccesstoken.put("cookie", "AccessToken=" + token);
        LogUtils.d("getHeaderAccesstoken = " + token);
        return headerAccesstoken;
    }


    public static Map<String, String> getHeaderUid(Context context) {
        Map<String, String> headerUid = new HashMap<String, String>();
        headerUid.put("UID", CookieUtils.getAPICookies(context));
        return headerUid;
    }

    public static String getSDPath() {
        String sdPath = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return sdPath;
    }


    public static String getUUID(Context context) {
        PreferencesUtils preferencesUtils = PreferencesUtils.shareInstance(context);
        String mUUID = preferencesUtils.readUUID();
        if (TextUtils.isEmpty(mUUID)) {
            mUUID = UUID.randomUUID().toString();
            preferencesUtils.writeUUID(mUUID);
        }
        LogUtils.d("uuid = " + mUUID);
        return mUUID;
    }

    /**
     * 1.对文本进行32位小写MD5加密
     *
     * @param plainText 要进行加密的文本
     * @return 加密后的内容
     */
    public static String md5L32(String plainText) {
        String result = null;
        //首先判断是否为空
        if (TextUtils.isEmpty(plainText)) {
            return null;
        }
        try {
            //首先进行实例化和初始化
            MessageDigest md = MessageDigest.getInstance("MD5");
            //得到一个操作系统默认的字节编码格式的字节数组
            byte[] btInput = plainText.getBytes();
            //对得到的字节数组进行处理
            md.update(btInput);
            //进行哈希计算并返回结果
            byte[] btResult = md.digest();
            //进行哈希计算后得到的数据的长度
            StringBuffer sb = new StringBuffer();
            for (byte b : btResult) {
                int bt = b & 0xff;
                if (bt < 16) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(bt));
            }
            result = sb.toString();
            LogUtils.d("ziplen md5 " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 2.对文本进行32位MD5大写加密
     *
     * @param plainText 要进行加密的文本
     * @return 加密后的内容
     */
    public static String md5U32(String plainText) {
        if (TextUtils.isEmpty(plainText)) {
            return null;
        }
        String result = md5L32(plainText);
        return result.toUpperCase();
    }

    /**
     * 获取设备屏幕宽度(pixels)
     *
     * @return
     */
    public static int getDisplayWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备屏幕高度(pixels)
     *
     * @return
     */
    public static int getDisplayHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取WebView的宽度(pixels)
     *
     * @return
     */
    public static int getWebviewWidth(Context context) {
        return PreferencesUtils.shareInstance(context).readWebviewWidth();
    }

    /**
     * 获取WebView的高度(pixels)
     *
     * @return
     */
    public static int getWebviewHeight(Context context) {
        return PreferencesUtils.shareInstance(context).readWebviewHeight();
    }

    /**
     * 获取手机的设备唯一标识
     *
     * @return
     */
    public static String getDeviceId(Context context) {
        String deviceId = PreferencesUtils.shareInstance(context).readDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            TelephonyManager tm = null;
            tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
            PreferencesUtils.shareInstance(context).writeDeviceId(deviceId);
        }
        return deviceId;
    }

    /**
     * 获取本机Mac地址
     *
     * @return
     */
    public static String getMacAddress(Context context) {
        String macAddress = PreferencesUtils.shareInstance(context).readMacAddress();
        if (TextUtils.isEmpty(macAddress)) {
            WifiManager wifi = null;
            wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            macAddress = info.getMacAddress();
            PreferencesUtils.shareInstance(context).writeMacAddress(macAddress);
        }
        return macAddress;
    }

    /**
     * 获取本机SIM serial number，如果是CDMA设备，则返回空字符串
     *
     * @return
     */
    public static String getSimSerialNumber(Context context) {
        String simSerialNumber = PreferencesUtils.shareInstance(context).readSimSerialNumber();
        if (TextUtils.isEmpty(simSerialNumber)) {
            TelephonyManager tm = null;
            tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String ssn = tm.getSimSerialNumber();
            if (!TextUtils.isEmpty(ssn)) {
                PreferencesUtils.shareInstance(context).writeSimSerialNumber(ssn);
                simSerialNumber = ssn;
            }
        }
        return simSerialNumber;
    }

    /**
     * 获取本机的Serial Number, Android 2.3以前的设备可能返回空值
     *
     * @return
     */
    public static String getSerialNumber(Context context) {
        String serialNumber = PreferencesUtils.shareInstance(context).readSerialNumber();
        if (TextUtils.isEmpty(serialNumber)) {
            String sn = android.os.Build.SERIAL;
            if (!TextUtils.isEmpty(sn)) {
                PreferencesUtils.shareInstance(context).writeSerialNumber(serialNumber);
                serialNumber = sn;
            }
        }
        return serialNumber;
    }

    /**
     * 获取本机的Android Id，某些设备可能会返回空值或者无用值。
     *
     * @return
     */
    public static String getAndroidId(Context context) {
        String androidId = PreferencesUtils.shareInstance(context).readAndroidId();
        if (TextUtils.isEmpty(androidId)) {
            String ai = null;
            try {
                ai = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(ai)) {
                PreferencesUtils.shareInstance(context).writeAndroidId(ai);
                androidId = ai;
            }
        }
        return androidId;
    }

    public boolean isAppOnForeground(Context context) {
        ActivityManager mActivityManager = null;
        mActivityManager = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> tasksInfo = mActivityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            LogUtils.d("top Activity = "
                    + tasksInfo.get(0).topActivity.getPackageName());
            // 应用程序位于堆栈的顶层
            final String mPackageName = "com.aimi.pintuan";
            if (mPackageName.equals(tasksInfo.get(0).topActivity
                    .getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static String getAssetsVersion(Context context) {
        try {
            String assetsVersion = "";
            InputStream is = null;
            is = context.getAssets().open("version.config");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String assetsVersionStr = new String(buffer, "utf-8").trim();
            assetsVersion = assetsVersionStr.split("=")[1];
            LogUtils.d("assetsVersion " + assetsVersion);
            return assetsVersion;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 复制一个目录及其子目录、文件到另外一个目录
     *
     * @param src
     * @param dest
     * @throws IOException
     */
    public static boolean copyFile(File src, File dest) {
        try {
            if (src.isDirectory()) {
                if (!dest.exists()) {
                    dest.mkdir();
                }
                String files[] = src.list();
                for (String file : files) {
                    File srcFile = new File(src, file);
                    File destFile = new File(dest, file);
                    // 递归复制
                    copyFile(srcFile, destFile);
                }
            } else {
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dest);

                byte[] buffer = new byte[1024];

                int length;

                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                    out.flush();
                }
                in.close();
                out.flush();
                out.close();
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static Bitmap compressImgByWidthAndHeight(String imgPath, float pixelW, float pixelH) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true，即只读边不读内容
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_4444;
        // Get bitmap info, but notice that bitmap is null now
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        LogUtils.d("compressImgByWidthAndHeight path before w/h = " + (1.0 * w / h));
        // 想要缩放的目标尺寸
        float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
        float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0) be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        // 开始压缩图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(imgPath, newOpts);
        LogUtils.d("compressImgByWidthAndHeight path after w/h = " + (1.0 * bitmap.getWidth() / bitmap.getHeight()));
        return bitmap;
    }

    public static Bitmap compressImgByWidthAndHeight(Bitmap bgimage, float pixelW, float pixelH) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        LogUtils.d("compressImgByWidthAndHeight bitmap before w/h = " + (width / height));
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) pixelW) / width;
        float scaleHeight = ((float) pixelH) / height;
        float scale = 1.0f;
        if (scaleHeight > scaleWidth) {
            scale = scaleHeight;
        } else {
            scale = scaleWidth;
        }
        // 缩放图片动作
        matrix.postScale(scale, scale);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        LogUtils.d("compressImgByWidthAndHeight bitmap after w/h = " + (1.0 * bitmap.getWidth() / bitmap.getHeight()));
        return bitmap;
    }

    public static byte[] compressImgBySize(Bitmap img, long limitSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int quality = 100;

        img.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        LogUtils.d("compressImgBySize before len = " + baos.toByteArray().length);
        while (quality > 0 && baos.toByteArray().length > limitSize) {
            baos.reset();
            quality -= 10;
            img.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        }
        byte[] bytes = baos.toByteArray();
        LogUtils.d("compressImgBySize after len = " + bytes.length);
        return bytes;
    }
}