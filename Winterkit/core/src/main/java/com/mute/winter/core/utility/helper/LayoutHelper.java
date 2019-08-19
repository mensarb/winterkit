package com.mute.winter.core.utility.helper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.text.InputType;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

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

    public static void strikeThroughText(@NonNull TextView textView){
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public static void underlineText(@NonNull TextView textView){
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public static void removeUnderLineText(@NonNull TextView textView){
        textView.setPaintFlags(textView.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));
    }

    public static void colorView(ImageView image, @ColorInt int color){
        ImageViewCompat.setImageTintList(image, ColorStateList.valueOf(color));
    }

    public static void togglePassword(AppCompatEditText view, boolean visibility){
        if (visibility){
            view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else {
            view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    /**
     * =============================================================================================
     * LayoutManager
     * =============================================================================================
     */

    public static LinearLayoutManager getHorizontalLinearLayoutManager(Context context){
        return new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    }

    public static LinearLayoutManager getVerticalLinearLayoutManager(Context context){
        return getVerticalLinearLayoutManager(context, true);
    }

    public static LinearLayoutManager getVerticalLinearLayoutManager(Context context, final boolean canScroll){
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return canScroll;
            }
        };
    }

    public static void scrollUp(Context context, RecyclerView.LayoutManager layoutManager){
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(context){
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(0);
        layoutManager.startSmoothScroll(smoothScroller);
    }
}
