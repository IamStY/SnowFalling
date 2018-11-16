package com.example.stevenyang.snowexampleactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stevenyang.snowfalling.SnowFlakesLayout;

public class SnowFallingExampleActivity extends AppCompatActivity {
    SnowFlakesLayout snowFlakesLayout;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        snowFlakesLayout = (SnowFlakesLayout) this.findViewById(R.id.snowflakelayout);
        snowFlakesLayout.init();
        snowFlakesLayout.setWholeAnimateTiming(3000000);
        snowFlakesLayout.setAnimateDuration(10000);
        snowFlakesLayout.setGenerateSnowTiming(300);
        snowFlakesLayout.setRandomSnowSizeRange(40, 1);
        snowFlakesLayout.setImageResourceID(R.drawable.snow_flakes_pic);
        snowFlakesLayout.setEnableRandomCurving(true);
        snowFlakesLayout.setEnableAlphaFade(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        snowFlakesLayout.startSnowing();
    }

    @Override
    protected void onStop() {
        super.onStop();
        snowFlakesLayout.stopSnowing();
    }
}
