package com.mute.winter.winterkit.utility.helper;

/**
 * @author dkoller
 * @since 22.09.2017
 */

public class UrlHelper {

    public static String getValidUrl(String url){
        if (url != null) {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            return url.replaceAll(" ", "%20");
        }
        return null;
    }
}
