package com.mute.winter.winterkit.utility.helper;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * @author dkoller
 * @since 20.09.2017
 */

public class PlayServiceHelper {

    public static boolean isGooglePlayServicesAvailable(@NonNull Context context){
        try {
            GoogleApiAvailability api = GoogleApiAvailability.getInstance();
            int code = api.isGooglePlayServicesAvailable(context);
            return code == ConnectionResult.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isGoogleMapsInstalled(@NonNull Application application){
        try {
            application.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
