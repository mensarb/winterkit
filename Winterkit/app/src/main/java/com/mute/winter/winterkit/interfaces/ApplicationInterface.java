package com.mute.winter.winterkit.interfaces;

import okhttp3.OkHttpClient;

/**
 * @author dkoller
 * @since 30.05.2017.
 */
@Deprecated
public interface ApplicationInterface {
    OkHttpClient getClient();
}
