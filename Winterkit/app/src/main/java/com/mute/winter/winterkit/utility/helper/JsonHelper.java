package com.mute.winter.winterkit.utility.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author dkoller
 * @since 26.06.2017
 */

public class JsonHelper {

    @Nullable
    public static String bundleToJsonString(@Nullable Bundle bundle){
        if (bundle == null){
            Logger.e("bundle is null");
        }else {
            JSONObject jsonObject = new JSONObject();
            try {
                for (String key : bundle.keySet()) {
                    jsonObject.put(key, bundle.get(key));
                }
                return jsonObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String convert(Map<String, String> map) {
        return new Gson().toJson(map);
    }

    public static Map<String, String> revert(String json) {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        return new Gson().fromJson(json, type);
    }
}
