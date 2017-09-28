package com.mute.winter.core.utility;

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

    private boolean isDebug = true;

    public void init(boolean isDebug){
        this.isDebug = isDebug;
        initLogger();
    }

    private void initLogger(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                //.methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                //.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                //.tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        Logger.addLogAdapter(new LogAdapter() {
            @Override
            public boolean isLoggable(int i, String s) {
                return isDebug;
            }

            @Override
            public void log(int i, String s, String s1) {

            }
        });
    }
}
