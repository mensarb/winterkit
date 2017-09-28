package com.mute.winter.core.utility.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;

/**
 * @author dkoller
 * @since 12.04.2017
 */

public class IntentHelper {

    public static Intent getApplicationDetailsIntent(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        return intent;
    }

    public static Intent getLocationSettingsIntent(){
        return new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    public static Intent getExternalBrowserIntent(@NonNull String url){
        return new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse(url));
    }

    public static Intent getGoogleMapsIntent(@NonNull Uri uri){
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        return intent;
    }

    public static Intent getPlaystoreAppIntent(@NonNull String packageName){
        return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +packageName));
    }

    public static Intent getPlaystoreBrowserIntent(@NonNull String packageName){
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" +packageName));
    }

    public static Intent getPhoneDialIntent(@NonNull String telephoneNumber){
        String uri = "tel:" +telephoneNumber.trim();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        return intent;
    }

    public static Intent getDeviceLocationSettingsIntent(){
        return new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }
}
