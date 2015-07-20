package com.myllenno.androidt.sensores;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Banner.
        AdView adView = (AdView) this.findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void event(View view){
        startActivity(new Intent(this, ActivityMenu.class));
    }
}
