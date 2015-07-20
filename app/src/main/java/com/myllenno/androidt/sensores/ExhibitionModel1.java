package com.myllenno.androidt.sensores;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ExhibitionModel1 extends Activity {

    TextView title, description, data;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibition_model_2);
        title = (TextView) findViewById(R.id.title_m2);
        title.setText(Attributes.sensorModel.getName());
        description = (TextView) findViewById(R.id.infoSensor_m2);
        description.setText(Attributes.sensorModel.getDescription());
        data = (TextView) findViewById(R.id.m2);
        thread = getInfo();
        thread.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        thread.interrupt();
    }

    private Thread getInfo(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    while (true){
                        data.post(new Runnable() {
                            @Override
                            public void run() {
                                data.setText(Attributes.getDataT1);
                            }
                        });
                        Thread.sleep(300);
                    }
                } catch (Exception e){}
            }
        });
        return thread;
    }
}
