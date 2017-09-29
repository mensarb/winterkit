package com.mute.winter.core.utility;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author dkoller
 * @since 30.05.2017.
 */

public class WinterkitInitializer {

    private Application app;
    private boolean isDebug = true;

    public void init(Application app, boolean isDebug){
        this.app = app;
        this.isDebug = isDebug;

        initLogger(app);
    }

    private void initLogger(Application app){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .tag(app.getString(app.getApplicationInfo().labelRes))
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isDebug;
            }
        });
    }
}
