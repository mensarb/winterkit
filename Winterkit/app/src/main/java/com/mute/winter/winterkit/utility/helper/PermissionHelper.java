package com.mute.winter.winterkit.utility.helper;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * @author dkoller
 * @since 01.03.2016.
 *
 * Checks and requests Permissions
 */
public class PermissionHelper {

    public static boolean isPermitted(@NonNull Context context, String permission){
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(@NonNull Activity activity, String[] permissions, int requestId){
        ActivityCompat.requestPermissions(activity, permissions, requestId);
    }

    public boolean isPermissionGranted(int[] grantResult){
        return grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * =============================================================================================
     * Location
     * =============================================================================================
     */
    private static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private static final String[] LOCATION_PERMISSIONS = {
            ACCESS_FINE_LOCATION,
            ACCESS_COARSE_LOCATION
    };

    private boolean requestLocationPermissions = true;
    private boolean locationDialogVisible = false;

    private static final int PERMISSION_LOCATION_ID = 20;

    public static boolean isLocationPermitted(Context context){
        return isPermitted(context, ACCESS_FINE_LOCATION) ||
                isPermitted(context, ACCESS_COARSE_LOCATION);
    }

    public void requestLocationPermission(Activity activity){
        if (requestLocationPermissions && !locationDialogVisible) {
            requestPermission(activity, LOCATION_PERMISSIONS, PERMISSION_LOCATION_ID);
            locationDialogVisible = true;
        }
    }

    public boolean isLocationPermissionGranted(int requestCode, int[] grantResult){
        if (requestCode == PERMISSION_LOCATION_ID) {
            locationDialogVisible = false;
            boolean granted = isPermissionGranted(grantResult);
            requestLocationPermissions = granted;
            return granted;
        }
        return false;
    }

    /**
     * =============================================================================================
     * Camera
     * =============================================================================================
     */
    private static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String CAMERA = Manifest.permission.CAMERA;

    private static final String[] CAMERA_PERMISSIONS = {
            CAMERA,
            WRITE_EXTERNAL_STORAGE
    };

    private static final int PERMISSION_CAMERA_ID = 30;

    public static boolean isCameraPermitted(Context context){
        return isPermitted(context, CAMERA);
    }

    public static void requestCameraPermissions(Activity activity){
        requestPermission(activity, CAMERA_PERMISSIONS, PERMISSION_CAMERA_ID);
    }

    public static boolean isWriteExternalStoragePermitted(Context context){
        return isPermitted(context, WRITE_EXTERNAL_STORAGE);
    }

    /**
     * =============================================================================================
     * Contacts
     * =============================================================================================
     */
    private static final String READ_CONTACTS = Manifest.permission.READ_CONTACTS;

    private static final String[] CONTACT_PERMISSIONS = {
            READ_CONTACTS
    };

    private static final int PERMISSION_CONTACTS_ID = 40;

    public static boolean isReadContactsPermitted(Context context){
        return isPermitted(context, READ_CONTACTS);
    }

    public static void requestReadContactsPermission(Activity activity){
        requestPermission(activity, CONTACT_PERMISSIONS, PERMISSION_CONTACTS_ID);
    }

    /**
     * other
     */

    public static boolean isGpsEnabled(Context context){
        return GPSHelper.isGPSOrNetworkEnabled(context);
    }
}