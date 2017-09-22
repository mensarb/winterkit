package com.mute.winter.winterkit.modules.analytics;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * @author dkoller
 * @since 13.04.2017
 */

public class Analytics {

    protected synchronized Tracker getTracker(Context context, String trackingId, int dispatchPeriod){
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        analytics.setLocalDispatchPeriod(dispatchPeriod);
        return analytics.newTracker(trackingId);
    }

    protected void sendEvent(@NonNull Tracker tracker, String category, String action, String label, long value){
        GoogleAnalyticsHelper.sendEvent(tracker, category, action, label, value);
    }

    protected void sendScreenName(@NonNull Tracker tracker, String screenName){
        GoogleAnalyticsHelper.sendScreenName(tracker, screenName);
    }
}
