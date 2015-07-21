package com.myllenno.androidt.sensores;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ActivityMenu extends Activity implements SensorEventListener, View.OnClickListener {

    private LinearLayout layoutButtons;
    private SensorManager sensorManager = null;
    private Sensor sensor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons);
        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        resetAttributes();
        initSensors();
        initButtons();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
    
    //- Reset attributes of sensors.
    private void resetAttributes(){
        Attributes.sensorsSupported = new ArrayList<SensorModel>();
        Attributes.getDataT1 = "";
        Attributes.getDataT2 = new String[]{"","",""};
        Attributes.sensorModel = null;
    }

    //- Add sensors supported in list.
    private void initSensors(){
        SensorModel sensorInfo;
        for (int i=0; i < Attributes.sensors.length; i++){
            sensor = sensorManager.getDefaultSensor(Attributes.sensors[i]);
            //- Verify support of sensor.
            if (sensor != null){
                sensorInfo = new SensorModel(Attributes.sensorsSupported.size(), // id.
                        getApplicationContext().getString(Attributes.nameSensors[i]), // name.
                        getApplicationContext().getString(Attributes.descriptionSensors[i]), // description.
                        Attributes.quantOutputsSensors[i],sensor); // quantity output // sensor.
                Attributes.sensorsSupported.add(sensorInfo);
            }
        }
    }

    private void registerListenerSensors(Sensor sensor){
        sensorManager.unregisterListener(this);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    //- Init buttons for sensors supported.
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initButtons(){
        Button button;
        for (int i = 0; i < Attributes.sensorsSupported.size(); i++){
            button = new Button(new ContextThemeWrapper(this, R.style.buttonMenu));
            button.setId(Attributes.sensorsSupported.get(i).getId());
            button.setText(Attributes.sensorsSupported.get(i).getName());
            button.setOnClickListener(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
                button.setBackground(getResources().getDrawable(R.drawable.button_style));
            else
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_style));
            layoutButtons.addView(button);
        }
    }

    public void onSensorChanged(SensorEvent event){

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            Attributes.getDataT2[0] = "X: "+Attributes.formatT2.format(event.values[0])+" rad/s";
            Attributes.getDataT2[1] = "Y: "+Attributes.formatT2.format(event.values[1])+" rad/s";
            Attributes.getDataT2[2] = "Z: "+Attributes.formatT2.format(event.values[2])+" rad/s";
        }

        else if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            System.arraycopy(event.values, 0, Attributes.input, 0, event.values.length);
            Attributes.timestampLA = System.nanoTime();
            Attributes.dt = 1 / (Attributes.count / ((Attributes.timestampLA - Attributes.timestampOld) / 1000000000.0f));
            Attributes.count++;
            Attributes.alpha = Attributes.timeConstant / (Attributes.timeConstant + Attributes.dt);

            Attributes.gravity[0] = Attributes.alpha * Attributes.gravity[0] + (1 - Attributes.alpha) * Attributes.input[0];
            Attributes.gravity[1] = Attributes.alpha * Attributes.gravity[1] + (1 - Attributes.alpha) * Attributes.input[1];
            Attributes.gravity[2] = Attributes.alpha * Attributes.gravity[2] + (1 - Attributes.alpha) * Attributes.input[2];

            Attributes.getDataT2[0] = Attributes.formatT2.format(Attributes.input[0] - Attributes.gravity[0]) + " m/s²";
            Attributes.getDataT2[1] = Attributes.formatT2.format(Attributes.input[1] - Attributes.gravity[1]) + " m/s²";
            Attributes.getDataT2[2] = Attributes.formatT2.format(Attributes.input[2] - Attributes.gravity[2]) + " m/s²";
        }

        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            Attributes.getDataT2[0] = "X: "+Attributes.formatT2.format(event.values[0])+" ?T";
            Attributes.getDataT2[1] = "Y: "+Attributes.formatT2.format(event.values[1])+" ?T";
            Attributes.getDataT2[2] = "Z: "+Attributes.formatT2.format(event.values[2])+" ?T";
        }

        else if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            Attributes.getDataT1 = Attributes.formatT1.format(event.values[0])+" "+getResources().getString(R.string.steps);
        }

        else if ((event.sensor.getType() == Sensor.TYPE_PROXIMITY)||
            (event.sensor.getType() == Sensor.TYPE_SIGNIFICANT_MOTION)||
            (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR)){
            if (event.values[0] >= 1.0f)
                Attributes.getDataT1 = getResources().getString(R.string.detect);
            else
                Attributes.getDataT1 = getResources().getString(R.string.notDetect);
        }

        else if (event.sensor.getType() == Sensor.TYPE_GRAVITY){
            Attributes.getDataT2[0] = "X: "+Attributes.formatT2.format(event.values[0])+" m/s²";
            Attributes.getDataT2[1] = "Y: "+Attributes.formatT2.format(event.values[1])+" m/s²";
            Attributes.getDataT2[2] = "Z: "+Attributes.formatT2.format(event.values[2])+" m/s²";
        }

        else if (event.sensor.getType() == Sensor.TYPE_HEART_RATE){
            Attributes.getDataT1 = Attributes.formatT1.format(event.values[0])+" bpm";
        }

        else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Attributes.getDataT2[0] = "X: "+Attributes.formatT1.format(event.values[0])+" m/s²";
            Attributes.getDataT2[1] = "Y: "+Attributes.formatT1.format(event.values[1])+" m/s²";
            Attributes.getDataT2[2] = "Z: "+Attributes.formatT1.format(event.values[2])+" m/s²";
        }

        else if (event.sensor.getType() == Sensor.TYPE_LIGHT){
            Attributes.getDataT1 = Attributes.formatT2.format(event.values[0]);
        }

        else if (event.sensor.getType() == Sensor.TYPE_PRESSURE){
            Attributes.getDataT1 = Attributes.formatT2.format(event.values[0])+" hPa";
        }

        else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            Attributes.getDataT1 = Attributes.formatT2.format(event.values[0])+"ºC";
        }

        else if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            Attributes.getDataT1 = Attributes.formatT1.format(event.values[0])+"%";
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onClick(View v){
        Intent intent;
        int id = v.getId();
        // Register events of sensor clicked.
        registerListenerSensors(Attributes.sensorsSupported.get(id).getSensor());
        //- Open Exhibition 1 or Exhibition 2.
        Attributes.sensorModel = Attributes.sensorsSupported.get(id);
        if (Attributes.sensorsSupported.get(id).getQuantOutputs() == 1)
            intent = new Intent(getApplicationContext(), ExhibitionModel1.class);
        else
            intent = new Intent(getApplicationContext(), ExhibitionModel2.class);
        startActivity(intent);
    }
}
