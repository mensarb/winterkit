package com.mute.winter.core.utility.helper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dkoller
 * @since 02.06.2016
 * {@link android.content.SharedPreferences}
 */
public class CollectionConverter {

    /**
     * Converts List<String> to Set<String>
     * @param list List
     * @return Set
     */
    public static Set<String> convertListToSet(@NonNull List<String> list){
        return new HashSet<>(list);
    }

    /**
     * Converts Set<String> to List<String>
     * @param set Set
     * @return List
     */
    public static List<String> convertSetToList(@NonNull Set<String> set){
        return new ArrayList<>(set);
    }

    public static String[] convertListToArray(@NonNull List<String> list){
        return list.toArray(new String[list.size()]);
    }
}
