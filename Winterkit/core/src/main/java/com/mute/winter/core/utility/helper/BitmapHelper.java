package com.mute.winter.core.utility.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * @author dkoller
 * @since 20.06.2016
 */
public class BitmapHelper {

    /**
     * Recycle Bitmap
     * @param bitmap bitmap to recycle
     */
    public static void recycleBitmap(Bitmap bitmap){
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static long getBitmapSize(int resourceId, Context context){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
        return getBitmapSize(bitmap);
    }

    public static long getBitmapSize(Bitmap bitmap){
        if (bitmap == null){
            return -1;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageInByte = stream.toByteArray();
        return imageInByte.length;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, float angle){
        if (bitmap == null){
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * converts bitmap to byteArray
     * @param bitmap bitmap
     * @return byteArray
     */
    public static byte[] convertTyoByteArray(@NonNull Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    /**
     * converts byteArray to bitmap
     * @param image byteArray
     * @return bitmap
     */
    public static Bitmap getImage(@NonNull byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static String convertToBase64(@NonNull Bitmap bitmap) {
        return Base64.encodeToString(convertTyoByteArray(bitmap), Base64.DEFAULT);
    }

    public static Bitmap reduceSize(int resourceId, Context context, Bitmap.Config config){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        options.inScaled = false;
        return BitmapFactory.decodeResource(context.getResources(), resourceId, options);
    }

    public static Bitmap reduceSize(int resourceId, Context context){
        return reduceSize(resourceId, context, Bitmap.Config.RGB_565);
    }
}
