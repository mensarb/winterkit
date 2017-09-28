package com.mute.winter.analytics.modules.analytics;

/**
 * @author dkoller
 * @since 22.05.2017
 */

public class AnalyticsScreenEvent {

    private String screenName;

    public AnalyticsScreenEvent(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }
}
