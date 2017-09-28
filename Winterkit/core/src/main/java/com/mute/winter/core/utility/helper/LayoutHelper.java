package com.mute.winter.core.utility.helper;

import android.graphics.PorterDuff;
import android.widget.ProgressBar;

/**
 * @author dkoller
 * @since 15.03.2017
 */

public class LayoutHelper {

    public static void styleProgressBar(ProgressBar progressBar, int color){
        progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }

    public static void styleHorizontalProgressBar(ProgressBar progressBar, int color){
        progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}
