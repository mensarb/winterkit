package com.mute.winter.winterkit.utility.helper;

import java.util.Arrays;

/**
 * @author dkoller
 * @since 25.09.2017
 */

public class EnumHelper {

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }
}
