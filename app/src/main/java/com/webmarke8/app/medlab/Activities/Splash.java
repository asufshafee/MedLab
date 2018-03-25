package com.webmarke8.app.medlab.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

public class Splash extends AppCompatActivity {

    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        myApplication = (MyApplication) getApplicationContext();
        myApplication.RESOLUATION = getResoluation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    public String getResoluation() {
        String resoluation = "High";
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        if (metrics.densityDpi == metrics.DENSITY_HIGH) {
            resoluation = "high";
        } else if (metrics.densityDpi == metrics.DENSITY_MEDIUM) {
            resoluation = "Medium";
        } else if (metrics.densityDpi == metrics.DENSITY_LOW) {
            resoluation = "Low";
        } else if (metrics.densityDpi == metrics.DENSITY_XHIGH) {
            resoluation = "XHigh";
        }

        if ((getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Log.i(myApplication.TAG, ">>>>> " + "Large screen");

        } else if ((getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Log.i(myApplication.TAG, ">>>>> " + "Normal sized screen");

        } else if ((getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Log.i(myApplication.TAG, ">>>>> " + "Small sized screen");
        } else {
            Log.i(myApplication.TAG, ">>>>> " + "Screen size is neither large, normal or small");
        }

        return resoluation;
    }
}
