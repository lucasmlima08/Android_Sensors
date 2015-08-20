package com.myllenno.androidt.sensores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void event(View view){
        startActivity(new Intent(this, ActivityMenu.class));
    }
}
