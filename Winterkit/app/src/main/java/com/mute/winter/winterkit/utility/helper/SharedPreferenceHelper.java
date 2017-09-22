package com.mute.winter.winterkit.utility.helper;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author dkoller
 * @since 03.02.2017
 */
public class SharedPreferenceHelper {

    /**
     * saves a String into a specific sharedPreference
     * @param name the identifying name of the String
     * @param value the String itself
     * @param preferenceName the name of the sharedPreference
     */
    public static void saveString(Application app, String name, String value, String preferenceName){
        SharedPreferences settings = app.getSharedPreferences(preferenceName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(name, value);
        editor.apply();
    }

    /**
     * loads a String from a specific sharedPreference
     * @param name the identifying name of the String
     * @param preferenceName the name of the sharedPreference
     * @return the String if found, null if not found
     */
    public static String loadString(Application app, String name, String preferenceName){
        SharedPreferences settings = app.getSharedPreferences(preferenceName, 0);
        return settings.getString(name, null);
    }

    /**
     * saves a Set into a specific sharedPreference
     * @param name the identifying name of the Set
     * @param set the set itself
     * @param preferenceName the name of the sharedPreferences
     */
    public static void saveSet(Application app, String name, Set<String> set, String preferenceName){
        SharedPreferences settings = app.getSharedPreferences(preferenceName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(name, set);
        editor.apply();
    }

    /**
     * loads a Set from a specific sharedPreference
     * @param name the identifying name of the Set
     * @param preferenceName the name of the sharedPreference
     * @return the Set if found, null if not found
     */
    public static Set<String> loadSet(Application app, String name, String preferenceName){
        SharedPreferences settings = app.getSharedPreferences(preferenceName, 0);
        return settings.getStringSet(name, null);
    }

    /**
     * clears a specific sharedPreference
     * @param preferenceName the name of the sharedPreference
     */
    public static void clearPreference(Application app, String preferenceName){
        SharedPreferences settings = app.getSharedPreferences(preferenceName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }
}
