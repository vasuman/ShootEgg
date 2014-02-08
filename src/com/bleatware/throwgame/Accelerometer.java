package com.bleatware.throwgame;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 2:39 PM
 */
public class Accelerometer implements SensorEventListener {
    private float valY;
    @Override
    public void onSensorChanged(SensorEvent event) {
        valY = event.values[1];
    }

    public float getValY() {
        return valY;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
