package com.myllenno.androidt.sensores;

import android.hardware.Sensor;

public class SensorModel {

    private int id;
    private String name;
    private String description;
    private int quantOutputs;
    private Sensor sensor;

    public SensorModel(int id, String name, String description, int quantOutputs, Sensor sensor){
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantOutputs = quantOutputs;
        this.sensor = sensor;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getQuantOutputs(){
        return quantOutputs;
    }

    public Sensor getSensor(){
        return sensor;
    }
}
