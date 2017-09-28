package com.mute.winter.core.utility.helper;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * @author dkoller
 * @since 20.02.2017
 */

public class MapsRouteHelper {

    /**
     * creates a routing-Intent for GoogleMaps
     */
    public static Intent route(double startLatitude, double startLongitude, double destinationLatitude, double destinationLongitude){
        String formatString = "http://maps.google.com/maps?&saddr=%1$s,%2$s&daddr=%3$s,%4$s";
        String uriFormatted = String.format(formatString, startLatitude, startLongitude, destinationLatitude, destinationLongitude);

        Uri uri = Uri.parse(uriFormatted);
        return IntentHelper.getGoogleMapsIntent(uri);
    }

    /**
     * creates a routing-Intent for GoogleMaps
     */
    public static Intent route(@NonNull Location start, @NonNull Location destination){
        return route(start.getLatitude(), start.getLongitude(), destination.getLatitude(), destination.getLongitude());
    }
}
