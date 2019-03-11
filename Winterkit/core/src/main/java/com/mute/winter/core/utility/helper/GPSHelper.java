package com.mute.winter.core.utility.helper;

import android.content.Context;
import android.location.LocationManager;

import androidx.annotation.NonNull;


/**
 * @author dkoller
 * @since 20.09.2017
 */

public class GPSHelper {

    public static boolean isGPSEnabled(@NonNull Context context){
        return isGPSProviderAvailable(context);
    }

    public static boolean isGPSOrNetworkEnabled(@NonNull Context context){
        return isGPSEnabled(context) || NetworkHelper.isNetworkProviderAvailable(context);
    }

    public static boolean isGPSProviderAvailable(@NonNull Context context){
        try {
            LocationManager service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
