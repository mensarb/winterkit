package com.mute.winter.core.utility.helper;

import android.content.Context;
import android.widget.Toast;

/**
 * @author dkoller
 * @since 05.04.2017
 */

public class MessageHelper {

    public static void drawShortToast(Context context, String messageText){
        drawToast(context, messageText, Toast.LENGTH_SHORT);
    }

    public static void drawLongToast(Context context, String messageText){
        drawToast(context, messageText, Toast.LENGTH_LONG);
    }

    private static void drawToast(Context context, String messageText, int length){
        Toast.makeText(context, messageText, length).show();
    }
}
