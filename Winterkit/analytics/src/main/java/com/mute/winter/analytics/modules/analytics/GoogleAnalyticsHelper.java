package com.mute.winter.analytics.modules.analytics;

import android.support.annotation.NonNull;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * @author dkoller
 * @since 13.04.2017
 */

public class GoogleAnalyticsHelper {

    public static void sendEvent(@NonNull Tracker tracker, String category, String action, String label, long value){
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .setValue(value)
                .build());
    }

    public static void sendScreenName(@NonNull Tracker tracker, String screenName){
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
