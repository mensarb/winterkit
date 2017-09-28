package com.mute.winter.core.utility.helper;

/**
 * @author dkoller
 * @since 02.05.2016
 */
public class DistanceMetricsHelper {

    /**
     * converts kilometer into miles
     * 1 [km] = 0.621371 [mi]
     * @param km value [km] to convert
     * @return converted value [mi]
     */
    public static double convertKmToMiles(double km){
        return km*0.621371;
    }

    /**
     * converts kilometer into meter
     * 1 [km] = 1000 [m]
     * @param km value [km] to convert
     * @return converted value [m]
     */
    public static double convertKmToM(double km){
        return km*1000;
    }

    /**
     * converts miles into kilometer
     * 1 [mi] = 1.60934 [km]
     * @param miles value [mi] to convert
     * @return converted value [km]
     */
    public static double convertMilesToKm(double miles){
        return miles*1.60934;
    }

    /**
     * converts miles into yards
     * 1 [mi] = 1760 [yd]
     * @param miles value [mi] to convert
     * @return converted value [yd]
     */
    public static double convertMilesToYard(double miles){
        return miles*1760;
    }
}