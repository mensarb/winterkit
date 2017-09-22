package com.mute.winter.winterkit.utility;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author dkoller
 * @since 30.05.2017.
 */

public class ApplicationInitializer {

    private boolean isDebug = true;
    private OkHttpClient client;

    public void init(boolean isDebug){
        this.isDebug = isDebug;
        initLogger();
    }

    public OkHttpClient getClient(){
        if (client == null){
            client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(30 , TimeUnit.SECONDS)
                    .build();
        }
        return client;
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
