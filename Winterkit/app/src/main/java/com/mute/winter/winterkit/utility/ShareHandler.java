package com.mute.winter.winterkit.utility;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

/**
 * @author dkoller
 * @since 03.04.2017
 *
 * more info @
 * https://github.com/codepath/android_guides/wiki/Sharing-Content-with-Intents
 */

public class ShareHandler {

    protected Intent getShareIntent(String shareText){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        return intent;
    }

    protected Intent getShareIntent(@NonNull Bitmap bitmap, ContentResolver contentResolver) throws SecurityException{
        String imagePath = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "", null);
        Uri imageUri = Uri.parse(imagePath);
        return getShareIntent(imageUri);
    }

    protected Intent getShareIntent(Uri imageUri){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/jpg");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        return intent;
    }
}
