package com.mute.winter.web.utility.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import okhttp3.internal.Util;

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
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static int getScreenWidthPx(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static float getDisplayDensity(Activity activity){
        return activity.getResources().getDisplayMetrics().density;
    }

    public static int getStatusBarHeightPx(Activity activity){
        Rect rectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return rectangle.top;
    }

    /**
     * Convert Methods
     */
    public static float convertPxToDp(Activity activity, float px){
        Resources resources = activity.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / metrics.density;
    }

    public static float convertDpToPx(Activity activity, float dp){
        Resources resources = activity.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * metrics.density;
    }

    /**
     * other
     */

    public float[] getDisplayPixelsPerInch(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float xDpi = metrics.xdpi;
        float yDpi = metrics.ydpi;

        return new float[]{
                xDpi,
                yDpi
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
        return Util.toHumanReadableAscii(Build.MANUFACTURER);
    }

    public static String getBrand(){
        return Util.toHumanReadableAscii(Build.BRAND);
    }

    public static String getModel(){
        return Util.toHumanReadableAscii(Build.MODEL);
    }

    public static int getApiLevel(){
        return Build.VERSION.SDK_INT;
    }

    public static String getAndroidId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceName(){
        return Util.toHumanReadableAscii(Build.DEVICE);
    }

    public static String getDeviceProductName(){
        return Util.toHumanReadableAscii(Build.PRODUCT);
    }
}
