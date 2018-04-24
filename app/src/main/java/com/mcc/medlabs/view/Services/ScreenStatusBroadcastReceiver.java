package com.mcc.medlabs.view.Services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.text.format.Time;
import android.util.Log;

import com.mcc.medlabs.view.Session.MyApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ScreenStatusBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "PhoneUsages.ScreenStatusBroadcastReceiver";
    private Time mTime;
    private String mTimeFormat = "%k:%M:%S";

    public ScreenStatusBroadcastReceiver() {
        mTime = new Time(Time.getCurrentTimezone());
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        mTime.setToNow();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            Log.d(TAG, mTime.format(mTimeFormat) + ": Screen is UNLOCK");
            int numUnlock = pref.getInt(Constants.PREF_NUM_UNLOCK, 0);
            pref.edit()
                    .putInt(Constants.PREF_NUM_UNLOCK, ++numUnlock)
                    .putLong(Constants.PREF_LAST_UNLOCK_TIME_MS, mTime.toMillis(false))
                    .commit();
        } else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            Log.d(TAG, mTime.format(mTimeFormat) + ": Screen is ON");
            int numScreenOn = pref.getInt(Constants.PREF_NUM_SCREEN_ON, 0);
            pref.edit().putInt(Constants.PREF_NUM_SCREEN_ON, ++numScreenOn).commit();
        } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            Log.d(TAG, mTime.format(mTimeFormat) + ": Screen is OFF");
            long lastTime = pref.getLong(Constants.PREF_LAST_UNLOCK_TIME_MS, 0);
            long usageTime = mTime.toMillis(false) - lastTime;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(usageTime);

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(Calendar.getInstance().getTime());

            if (MyApplication.getGLASSDate().equals("")) {
//                    Glass = 0;
                MyApplication.setGLASSDate(date);
                MyApplication.setyesterdaytime((long) 0);
            } else {

                if (MyApplication.getGLASSDate().trim().contains(date.trim())) {
                    MyApplication.setSECONDS(seconds);
                    Log.i("[BroadcastReceiver]", "SAME DATE");
                } else {

                    MyApplication.setyesterdaytime(seconds);
                    MyApplication.setSECONDS(-1);
                    MyApplication.setGLASSDate(date);
                    Log.i("[BroadcastReceiver]", "Change");

                }
            }
        }
    }
}
