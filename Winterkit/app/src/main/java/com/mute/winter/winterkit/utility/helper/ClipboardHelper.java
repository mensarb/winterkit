package com.mute.winter.winterkit.utility.helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * @author sanakan
 * @since 26.09.2017
 */

public class ClipboardHelper {

    public static void copyToClipboard(Context context, String text){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("", text);
        clipboardManager.setPrimaryClip(clipData);
    }
}
