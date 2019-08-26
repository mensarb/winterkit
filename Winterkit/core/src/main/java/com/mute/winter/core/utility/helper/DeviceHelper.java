package com.mute.winter.core.utility.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author dkoller
 * @since 24.10.2016
 */

public class DeviceHelper {

    /**
     * Screen Brightness
     */
    public static float getScreenBrightness(Activity activity){
        return activity.getWindow().getAttributes().screenBrightness;
    }

    public static void setScreenBrightness(Activity activity, float brightness){
        WindowManager.LayoutParams layout = activity.getWindow().getAttributes();
        layout.screenBrightness = brightness;
        activity.getWindow().setAttributes(layout);
    }

    /**
     * Screen Height/Width
     */
    public static int getScreenHeightPx(Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.y;
    }

    public static int getScreenWidthPx(Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static float getDisplayDensity(Activity activity){
        return activity.getResources().getDisplayMetrics().density;
    }

    /**
     * Convert Methods
     */
    public static float convertPxToDp(Activity activity, float px){
        return px / activity.getResources().getDisplayMetrics().density;
    }

    public static float convertDpToPx(Activity activity, float dp){
        return dp * activity.getResources().getDisplayMetrics().density;
    }

    /**
     * other
     */

    public float[] getDisplayPixelsPerInch(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return new float[]{
                metrics.xdpi,
                metrics.ydpi
        };
    }

    public static boolean isPortraitOrientation(Activity activity){
        return getScreenWidthPx(activity) < getScreenHeightPx(activity);
    }

    public static boolean isLandscapeOrientation(Activity activity){
        return  getScreenHeightPx(activity) < getScreenWidthPx(activity);
    }

    /**
     * Get Device Info
     */

    public static String getManufacturer(){
        return Build.MANUFACTURER;
    }

    public static String getBrand(){
        return Build.BRAND;
    }

    public static String getModel(){
        return Build.MODEL;
    }

    public static int getApiLevel(){
        return Build.VERSION.SDK_INT;
    }

    public static String getAndroidId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceName(){
        return Build.DEVICE;
    }

    public static String getDeviceProductName(){
        return Build.PRODUCT;
    }
}
