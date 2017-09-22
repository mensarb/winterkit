package com.mute.winter.winterkit.utility.helper;

import android.content.Context;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author dkoller
 * @since 08.03.2017
 */

public class AssetHelper {

    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Typeface getTypefaceFromAssets(Context context, String path){
        return Typeface.createFromAsset(context.getAssets(), path);
    }
}
