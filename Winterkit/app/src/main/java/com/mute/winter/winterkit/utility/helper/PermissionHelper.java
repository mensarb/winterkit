package com.mute.winter.winterkit.utility.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * @author dkoller
 * @since 01.06.2017
 */

public class PermissionHelper {

    private static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private static final String[] EXTERNAL_STORAGE_PERMISSIONS = {
            WRITE_EXTERNAL_STORAGE
    };

    private static final int EXTERNAL_STORAGE_REQUEST_ID = 30;

    public static boolean isWriteExternalStoragePermitted(Context context){
        return ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestWriteExternalStoragePermissions(Activity activity){
        ActivityCompat.requestPermissions(activity, EXTERNAL_STORAGE_PERMISSIONS, EXTERNAL_STORAGE_REQUEST_ID);
    }
}
