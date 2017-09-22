package com.mute.winter.winterkit.utility.helper;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.List;

/**
 * @author dkoller
 * @since 19.05.2017
 */

public class SensorHelper {

    public static List<Sensor> getAllSensors(Context context){
        return getSensors(context, Sensor.TYPE_ALL);
    }

    public static List<Sensor> getSensors(Context context, int type){
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        return sensorManager.getSensorList(type);
    }

    public static boolean isAccelerometerAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_ACCELEROMETER);
    }

    public static boolean isAmbientTemperatureAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_AMBIENT_TEMPERATURE);
    }

    public static boolean isGravityAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_GRAVITY);
    }

    public static boolean isGyroscopeAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_GYROSCOPE);
    }

    public static boolean isLightAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_LIGHT);
    }

    public static boolean isLinearAccelerationAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_LINEAR_ACCELERATION);
    }

    public static boolean isMagneticFieldAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_MAGNETIC_FIELD);
    }

    public static boolean isPressureAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_PRESSURE);
    }

    public static boolean isProximityAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_PROXIMITY);
    }

    public static boolean isRelativeHumidityAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_RELATIVE_HUMIDITY);
    }

    public static boolean isRotationVectorAvailable(Context context){
        return isSensorAvailable(context, Sensor.TYPE_ROTATION_VECTOR);
    }

    public static boolean isSensorAvailable(Context context, int type){
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        return sensorManager.getDefaultSensor(type) != null;
    }
}
