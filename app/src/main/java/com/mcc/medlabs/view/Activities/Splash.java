package com.mcc.medlabs.view.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.mcc.medlabs.view.DatabasePart.MedlabsDelegate;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Services.TimeUsed;
import com.mcc.medlabs.view.Session.GlobalActions;
import com.mcc.medlabs.view.Session.MedlabsConstants;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Session.SharedPrefrenceKeys;

import java.io.IOException;
import java.util.Locale;

public class Splash extends AppCompatActivity {

    MyApplication myApplication;
    private MedlabsDelegate delegate;
    Boolean Check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        Intent intent = new Intent(getApplicationContext(), TimeUsed.class);
        if (!isMyServiceRunning(TimeUsed.class))
            startService(intent);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String user_id_value = preferences.getString(SharedPrefrenceKeys.USER_ID, "");

        if (user_id_value.equals("")) {
            MedlabsConstants.USER_ID = "";
        } else {
            MedlabsConstants.USER_ID = GlobalActions.getDataToSharedPrefrences(SharedPrefrenceKeys.USER_ID, this);
        }

        Log.e(MedlabsConstants.TAG, "MedlabsConstants.USER_ID : " + MedlabsConstants.USER_ID);
        // **************************************************************************************************

        String push_enabled_value = preferences.getString(SharedPrefrenceKeys.PUSH_ENABLED, "");
        if (push_enabled_value.equals("")) {
            // MedlabsConstants.isPushEnabled = "false";
        } else {
            MedlabsConstants.isPushEnabled = GlobalActions.getDataToSharedPrefrences(SharedPrefrenceKeys.PUSH_ENABLED,
                    this);
        }


        delegate = MedlabsDelegate.getMedLabsDelegateInstance();

        try {
            Log.e(MedlabsConstants.TAG, "*********************createDatabase******************************");
            delegate.createDatabase(Splash.this);
            Log.e(MedlabsConstants.TAG, "*********************createDatabase******************************");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions((Activity) Splash.this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE
                            , android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , android.Manifest.permission.CALL_PHONE
                            , android.Manifest.permission.ACCESS_FINE_LOCATION
                            , android.Manifest.permission.ACCESS_COARSE_LOCATION
                    }, 1);
        } else {

            myApplication = (MyApplication) getApplicationContext();
            MyApplication.RESOLUATION = getResoluation();
            MedlabsConstants.RESOLUATION = getResoluation();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (getIntent().getExtras() != null) {
                        for (String key : getIntent().getExtras().keySet()) {
                            String value = getIntent().getExtras().getString(key);
                            if (value != null
                                    && value.contains("medlabs")) {
                                Check = true;
                            }
                        }
                        if (Check) {
                            Intent intent = new Intent(Splash.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(Splash.this, MainActivity.class);
                            intent.putExtra("Notification", "no");
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        intent.putExtra("Notification", "no");
                        startActivity(intent);
                        finish();
                    }
                }
            }, 2000);
            String languageToLoad = myApplication.GetLanguage();
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.setLocale(locale);
            getResources().updateConfiguration(configuration, getApplicationContext().getResources().getDisplayMetrics());


        }


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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == 1) {
            myApplication = (MyApplication) getApplicationContext();
            myApplication.RESOLUATION = getResoluation();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (getIntent().getExtras() != null) {
                        for (String key : getIntent().getExtras().keySet()) {
                            String value = getIntent().getExtras().getString(key);
                            if (value.contains("medlabs")) {
                                Check = true;
                            }
                        }
                        if (Check) {
                            Intent intent = new Intent(Splash.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(Splash.this, MainActivity.class);
                            intent.putExtra("Notification", "no");
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        intent.putExtra("Notification", "no");
                        startActivity(intent);
                        finish();
                    }
                }
            }, 2000);

            String languageToLoad = myApplication.GetLanguage();
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.setLocale(locale);
            getResources().updateConfiguration(configuration, getApplicationContext().getResources().getDisplayMetrics());

        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
