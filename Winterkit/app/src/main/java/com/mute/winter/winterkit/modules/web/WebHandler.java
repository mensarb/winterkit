package com.mute.winter.winterkit.modules.web;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * @author dkoller
 * @since 05.04.2017
 */

public class WebHandler implements Callback {

    protected OkHttpClient client;

    public WebHandler(OkHttpClient client) {
        this.client = client;
    }

    public void loadUrl(String url){

    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {

    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

    }
}
