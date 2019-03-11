package com.mute.winter.core.utility.helper;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

/**
 * @author dkoller
 * @since 23.05.2017
 */

public class ShareHelper {

    public static void share(Activity context, String dialogText, @NonNull String shareText){
        Intent intent = Intent.createChooser(getIntent(shareText), dialogText);
        context.startActivity(intent);
    }

    private static Intent getIntent(String shareText){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        return intent;
    }
}
