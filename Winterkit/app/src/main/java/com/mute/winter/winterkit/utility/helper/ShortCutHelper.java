package com.mute.winter.winterkit.utility.helper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;

import java.util.Arrays;

/**
 * @author dkoller
 * @since 06.07.2017
 */

public class ShortCutHelper {

    public static void createDynamicShortCut(Context context, int iconLayoutId, String shortCutId, String shortLabel, String longLabel, Intent intent){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);

            ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(context, shortCutId)
                    .setShortLabel(shortLabel)
                    .setLongLabel(longLabel)
                    .setIcon(Icon.createWithResource(context, iconLayoutId))
                    .setIntent(intent)
                    .build();
            shortcutManager.setDynamicShortcuts(Arrays.asList(shortcutInfo));
        }
    }

    public static void deleteAllShortCuts(Context context){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            shortcutManager.removeAllDynamicShortcuts();
        }
    }
}
