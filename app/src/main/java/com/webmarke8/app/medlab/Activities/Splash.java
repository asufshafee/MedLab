package com.webmarke8.app.medlab.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

import java.util.Locale;

public class Splash extends AppCompatActivity {

    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);




        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions((Activity) Splash.this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE
                            ,android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ,android.Manifest.permission.CALL_PHONE
                            ,android.Manifest.permission.ACCESS_FINE_LOCATION
                            ,android.Manifest.permission.ACCESS_COARSE_LOCATION
                    }, 1);
        }


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

        String languageToLoad = myApplication.GetLanguage();
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, getApplicationContext().getResources().getDisplayMetrics());


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
