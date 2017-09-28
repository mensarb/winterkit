package com.mute.winter.bluetooth.utility.helper;

import android.bluetooth.BluetoothAdapter;

/**
 * @author dkoller
 * @since 20.09.2017
 */

public class BluetoothHelper {

    public static boolean isSupported(){
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        return adapter != null;
    }

    public static boolean isEnabled(){
        if (isSupported()){
            return BluetoothAdapter.getDefaultAdapter().isEnabled();
        }
        return false;
    }
}
