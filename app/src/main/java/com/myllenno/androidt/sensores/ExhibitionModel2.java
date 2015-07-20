package com.myllenno.androidt.sensores;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ExhibitionModel2 extends Activity {

    TextView title, description, datax, datay, dataz;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibition_model_1);
        title = (TextView) findViewById(R.id.title_m1);
        title.setText(Attributes.sensorModel.getName());
        description = (TextView) findViewById(R.id.infoSensor_m1);
        description.setText(Attributes.sensorModel.getDescription());
        datax = (TextView) findViewById(R.id.m1_x);
        datay = (TextView) findViewById(R.id.m1_y);
        dataz = (TextView) findViewById(R.id.m1_z);
        thread = getInfo();
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }

    private Thread getInfo(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    while (true){
                        datax.post(new Runnable() {
                            @Override
                            public void run() {
                                datax.setText(Attributes.getDataT2[0]);
                                datay.setText(Attributes.getDataT2[1]);
                                dataz.setText(Attributes.getDataT2[2]);
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
