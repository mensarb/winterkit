package com.mute.winter.core.utility.helper;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

/**
 * @author dkoller
 * @since 20.09.2017
 */

public class NetworkHelper {

    public static boolean isNetworkConnectionAvailable(@NonNull Application application){
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo() != null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isNetworkProviderAvailable(@NonNull Context context){
        try{
            LocationManager service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return  service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
