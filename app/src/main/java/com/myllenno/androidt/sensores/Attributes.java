package com.myllenno.androidt.sensores;

import android.hardware.Sensor;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Attributes {

    /* --------------------------------------- EXHIBITION  -------------------------------------------- */

    public static String getDataT1;
    public static String[] getDataT2;
    public static SensorModel sensorModel;

    /* --------------------------------------- INFO SENSORS  -------------------------------------------- */

    public static int[] sensors = new int[]{Sensor.TYPE_GYROSCOPE,Sensor.TYPE_LINEAR_ACCELERATION,
            Sensor.TYPE_MAGNETIC_FIELD,Sensor.TYPE_STEP_COUNTER,Sensor.TYPE_PROXIMITY,
            Sensor.TYPE_SIGNIFICANT_MOTION,Sensor.TYPE_STEP_DETECTOR,Sensor.TYPE_GRAVITY,
            Sensor.TYPE_HEART_RATE,Sensor.TYPE_ACCELEROMETER,Sensor.TYPE_LIGHT,
            Sensor.TYPE_PRESSURE,Sensor.TYPE_AMBIENT_TEMPERATURE,Sensor.TYPE_RELATIVE_HUMIDITY};

    public static int[] nameSensors = new int[]{R.string.gyroscope,R.string.linearAcceleration,
            R.string.magneticField,R.string.stepCounter,R.string.proximity,R.string.significantMotion,
            R.string.stepDetector,R.string.gravity,R.string.heartRate,R.string.accelerometer,
            R.string.light,R.string.pressure,R.string.ambientTemperature,R.string.relativeHumidity};

    public static int[] descriptionSensors = new int[]{R.string.gyroscopeD,R.string.linearAccelerationD,
            R.string.magneticFieldD,R.string.stepCounterD,R.string.proximityD,R.string.significantMotionD,
            R.string.stepDetectorD,R.string.gravityD,R.string.heartRateD,R.string.accelerometerD,
            R.string.lightD,R.string.pressureD,R.string.ambientTemperatureD,R.string.relativeHumidityD};

    public static int[] quantOutputsSensors = new int[]{3,3,3,1,1,1,1,3,1,3,1,1,1,1};

    public static ArrayList<SensorModel> sensorsSupported = null;

    /* -------------------------------- OTHERS ATTRIBUTES  -------------------------------------- */

    public static DecimalFormat formatT1 = new DecimalFormat("##");
    public static DecimalFormat formatT2 = new DecimalFormat("##.#");

    /* ---------------------------- ATTRIBUTES LINEAR ACCELERATION ------------------------------ */

    public static float timeConstant = 0.18f;
    public static float alpha = 0.9f;
    public static float dt = 0;
    public static int count = 0;
    public static float timestampLA = System.nanoTime();
    public static float timestampOld = System.nanoTime();
    public static float[] gravity = new float[]{0,0,0};
    public static float[] input = new float[]{0,0,0};

}
