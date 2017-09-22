package com.mute.winter.winterkit.utility.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

/**
 * @author dkoller
 * @since 15.03.2017
 */

public class ResourceHelper {

    public static int getColor(Context context, int colorId){
        return ContextCompat.getColor(context, colorId);
    }

    public static String getColorValue(Context context, int colorId){
        return context.getResources().getString(colorId);
    }

    public static Drawable getDrawable(Context context, int resourceId){
        return ResourcesCompat.getDrawable(context.getResources(), resourceId, null);
    }

    public static float getDimension(Context context, int dimensionId){
        return context.getResources().getDimension(dimensionId);
    }

    public static String getString(Context context, int stringId){
        return context.getResources().getString(stringId);
    }
}
