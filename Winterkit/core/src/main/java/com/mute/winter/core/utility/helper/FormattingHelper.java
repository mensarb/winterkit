package com.mute.winter.core.utility.helper;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author dkoller
 * @since 24.10.2016
 */

public class FormattingHelper {

    public static String formatDecimal1Places(double value){
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(value);
    }

    public static String formatDecimal2Places(double value){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(value);
    }

    public static String formatDecimal3Places(double value){
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(value);
    }

    public static String formatPrice(double value){
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###.##");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        return decimalFormat.format(value);
    }
}
