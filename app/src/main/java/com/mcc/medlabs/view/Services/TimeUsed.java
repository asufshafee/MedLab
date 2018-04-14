package com.mcc.medlabs.view.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.mcc.medlabs.view.Session.MyApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimeUsed extends Service {

    public static final int notify = 10000;  //interval between two services(Here Service run every 5 Minute)
    private Handler mHandler = new Handler();   //run on another Thread to avoid crash
    private Timer mTimer = null;    //timer handling
    Intent myService;
    Date lastTime = new Date();
    Boolean Screen = false;

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    Long Ontime, Oftime;
    Calendar cal;
    Intent intent1;


    //Create broadcast object
    BroadcastReceiver mybroadcast = new BroadcastReceiver() {
        //When Event is published, onReceive method is called
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Log.i("[BroadcastReceiver]", "MyReceiver");
            intent1 = intent;

            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

                Ontime = System.currentTimeMillis();
                Screen = true;
                Log.i("[BroadcastReceiver]", "Screen ON");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("[BroadcastReceiver]", "Screen OFF");

                try {
                    Calculate();

                } catch (Exception Ex) {

                }


            }


        }
    };

    public void Calculate() {

        if (intent1 == null) {
            Oftime = System.currentTimeMillis();
            long totalTime = Oftime - Ontime;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);

            //to mentain difference
            seconds++;
            seconds++;
            seconds++;

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(Calendar.getInstance().getTime());
            Log.i("[BroadcastReceiver]", date);
            Log.i("[BroadcastReceiver]", MyApplication.getGLASSDate());

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
                    MyApplication.setSECONDS(0);
                    MyApplication.setGLASSDate(date);
                    Log.i("[BroadcastReceiver]", "Change");

                }
            }
            Log.i("[BroadcastReceiver]", "Screen OFF" + String.valueOf(seconds));

            Ontime = System.currentTimeMillis();
        } else if (intent1.getAction().equals(Intent.ACTION_SCREEN_ON)) {

            Oftime = System.currentTimeMillis();
            long totalTime = Oftime - Ontime;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);


            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(Calendar.getInstance().getTime());
            if (MyApplication.getGLASSDate().equals("")) {
//                    Glass = 0;
                MyApplication.setGLASSDate(date);
                MyApplication.setyesterdaytime((long) 0);
            } else {

                if (MyApplication.getGLASSDate().equals(date)) {
                    MyApplication.setSECONDS(seconds);
                } else {

                    MyApplication.setyesterdaytime(seconds);
                    MyApplication.setSECONDS(0);
                    MyApplication.setGLASSDate(date);
                }
            }
            Log.i("[BroadcastReceiver]", "Screen OFF" + String.valueOf(seconds));

            Ontime = System.currentTimeMillis();

        }


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Screen = true;
        Ontime = System.currentTimeMillis();
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));
        if (mTimer != null) // Cancel if already existed
            mTimer.cancel();
        else
            mTimer = new Timer();   //recreate new

        cal = Calendar.getInstance();
        String mLastTime = dateFormat.format(cal.getTime());
        try {
            lastTime = dateFormat.parse(mLastTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myService = new Intent(TimeUsed.this, TimeUsed.class);
        mTimer.scheduleAtFixedRate(new TimeDisplay(), 1000, notify);   //Schedule task

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();    //For Cancel Timer
        startService(new Intent(TimeUsed.this, TimeUsed.class));

    }


    //class TimeDisplay for handling task
    class TimeDisplay extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    try {
                        Calculate();

                    } catch (Exception Ex) {

                    }

                    Log.i("[BroadcastReceiver]", "Screen OFF" + getDurationString(Integer.parseInt(String.valueOf(MyApplication.getSECONDS()))));
                }
            });
        }
    }

    private String getDurationString(int seconds) {

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return twoDigitString(hours) + ":" + twoDigitString(minutes);
    }

    private String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }


}