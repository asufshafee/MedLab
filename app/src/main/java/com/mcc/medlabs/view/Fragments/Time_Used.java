package com.mcc.medlabs.view.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.DecimalUtils;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Services.TimeUsed;
import com.mcc.medlabs.view.Session.MyApplication;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_MULTI_PROCESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class Time_Used extends Fragment {


    public Time_Used() {
        // Required empty public constructor
    }

    MyApplication myApplication;
    TextView HoursText, Yesterday, TimeHeading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time__used, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();

        if (myApplication.GetLanguage().equals("ar")) {
            ((MainActivity) getActivity()).Change_Tittle("الوقت المستخدم");
        } else {
            ((MainActivity) getActivity()).Change_Tittle("Time Used");
        }
        view.findViewById(R.id.Click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        ((MainActivity) getActivity()).ShowBack_toolbar();
//        ((MainActivity) getActivity()).ShowShare_toolbar();

        HoursText = (TextView) view.findViewById(R.id.Hours);
        TimeHeading = (TextView) view.findViewById(R.id.TextHeading);
        Yesterday = (TextView) view.findViewById(R.id.YesterDay);
        String Hours = getDurationString(Integer.parseInt(String.valueOf(MyApplication.getSECONDS())));
        long YersterDay = TimeUnit.SECONDS.toHours(MyApplication.getYesterdaytime());
        TimeHeading.setText(" " + String.valueOf(Hours) + " " + getString(R.string.hours_time));
        Yesterday.setText(getString(R.string.yesterday) + " " + String.valueOf(String.valueOf(YersterDay)) + " " + getString(R.string.hours_time));
        HoursText.setText(String.valueOf(String.valueOf(Hours)));


        if (mTimer != null) // Cancel if already existed
            mTimer.cancel();
        else
            mTimer = new Timer();   //recreate new

        mTimer.scheduleAtFixedRate(new TimeDisplay(), 1000, notify);   //Schedule task
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }


    @Override
    public void onDestroy() {
        ((MainActivity) getActivity()).HideShare_toolbar();
        if (((MainActivity) getActivity()).getSupportFragmentManager().getBackStackEntryCount() != 1)
            if (myApplication.GetLanguage().equals("en"))
                ((MainActivity) getActivity()).Change_Tittle("Manage My Health");
            else {
                ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Manage_My_Helth));

            }
        super.onDestroy();
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

    public static final int notify = 10000;  //interval between two services(Here Service run every 5 Minute)
    private Handler mHandler = new Handler();   //run on another Thread to avoid crash
    private Timer mTimer = null;    //timer handling

    class TimeDisplay extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MedLabsTimeUsed", MODE_MULTI_PROCESS);


                    String Hours = getDurationString(Integer.parseInt(String.valueOf(sharedPreferences.getLong("Secondsfortimeused", 0))));
                    String YersterDay = getDurationString(Integer.parseInt(String.valueOf(MyApplication.getYesterdaytime())));
                    TimeHeading.setText(" " + String.valueOf(Hours) + " " + getString(R.string.hours_time));
                    Yesterday.setText(getString(R.string.yesterday) + " " + String.valueOf(String.valueOf(YersterDay)) + " " + getString(R.string.hours_time));
                    HoursText.setText(String.valueOf(String.valueOf(Hours)));
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        mTimer.cancel();    //For Cancel Timer
        super.onDestroyView();
    }
}
