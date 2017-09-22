package com.mute.winter.winterkit.utility.helper;

import java.text.DecimalFormat;

/**
 * @author dkoller
 * @since 15.03.2017
 */

public class DistanceFormatter {

    /**
     * Metric Units
     */
    private static final String METRIC_M = "m";
    private static final String METRIC_KM = "km";
    private static final String METRIC_MILES = "mi";
    private static final String METRIC_YARDS = "yd";

    public static String formatDistanceKilometer(double km){
        return formatDistance(km, true);
    }

    public static String formatDistanceMiles(double miles){
        return formatDistance(miles, false);
    }

    private static String formatDistance(double distance, boolean isDistanceInKm){
        String unit;
        String formattedDistance;

        if (isDistanceInKm){
            if (distance < 1){
                unit = METRIC_M;
                formattedDistance = new DecimalFormat("#").format(DistanceMetricsHelper.convertKmToM(distance));
            }else {
                unit = METRIC_KM;
                formattedDistance = new DecimalFormat("#.##").format(distance);
            }
        }else {
            if (distance < 1){
                unit = METRIC_YARDS;
                formattedDistance = new DecimalFormat("#").format(DistanceMetricsHelper.convertMilesToYard(distance));
            }else {
                unit = METRIC_MILES;
                formattedDistance = new DecimalFormat("#.##").format(distance);
            }
        }

        return formattedDistance +" " +unit;
    }
}
