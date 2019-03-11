package com.mute.winter.core.utility.helper;

import android.content.ActivityNotFoundException;
import android.content.Context;

import androidx.annotation.NonNull;


/**
 * @author dkoller
 * @since 24.04.2017
 */

public class AppstoreHelper {

    public static void openAppStore(@NonNull Context context){
        final String packageName = context.getPackageName();
        try {
            context.startActivity(IntentHelper.getPlaystoreAppIntent(packageName));
        } catch (ActivityNotFoundException a) {
            context.startActivity(IntentHelper.getPlaystoreBrowserIntent(packageName));
        }
    }
}
