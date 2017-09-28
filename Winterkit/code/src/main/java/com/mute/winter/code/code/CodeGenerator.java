package com.mute.winter.code.code;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author dkoller
 * @since 27.06.2016
 */
public class CodeGenerator {

    @Nullable
    public static Bitmap generateQRCode(String message, int size, int foregroundColor, int backgroundColor) {
        try {
            return encodeQRCodeAsBitmap(message, size, size, foregroundColor, backgroundColor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Bitmap encodeQRCodeAsBitmap(String content, int width, int height, int color1, int color2) throws WriterException, IllegalArgumentException {
        BitMatrix result = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, null);

        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];

        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? color1 : color2;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        bitmap.setPixels(pixels, 0, width, 0, 0, w, h);

        return bitmap;
    }

    @Nullable
    public static Bitmap generateBarCode(String message, int width, int height, int foregroundColor, int backgroundColor){
        try {
            return encodeBarcodeAsBitmap(message, BarcodeFormat.CODE_128, width, height, foregroundColor, backgroundColor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Bitmap encodeBarcodeAsBitmap(String content, BarcodeFormat format, int width, int height, int color1, int color2) throws WriterException, IllegalArgumentException {
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(content);

        if (encoding != null) {
            hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result = writer.encode(content, format, width, height, hints);

        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];

        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? color1 : color2;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);

        return bitmap;
    }

    @Nullable
    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}