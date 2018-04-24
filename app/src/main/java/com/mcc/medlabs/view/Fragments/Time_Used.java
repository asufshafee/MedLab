package com.mcc.medlabs.view.Fragments;


import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.AppItem;
import com.mcc.medlabs.view.Objects.DataManager;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_MULTI_PROCESS;
import static com.mcc.medlabs.view.Fragments.AppHelper.initAppHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class Time_Used extends Fragment {


    public Time_Used() {
        // Required empty public constructor
    }

    MyApplication myApplication;
    TextView HoursText, Yesterday, TimeHeading;
    Dialog dialog;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time__used, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();

        MyApplication.setTimeUsedPermission(false);

        dialog = AppUtils.LoadingSpinner(getActivity());
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

        HoursText = (TextView) view.findViewById(R.id.Hours);
        TimeHeading = (TextView) view.findViewById(R.id.TextHeading);
        Yesterday = (TextView) view.findViewById(R.id.YesterDay);
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MedLabsTimeUsed", MODE_MULTI_PROCESS);
//        String Hours = getDurationString(Integer.parseInt(String.valueOf(sharedPreferences.getLong("Secondsfortimeused", 0))));
//        String YersterDay = getDurationString(Integer.parseInt(String.valueOf(MyApplication.getYesterdaytime())));
//        TimeHeading.setText(" " + String.valueOf(Hours) + " " + getString(R.string.hours_time));
//        Yesterday.setText(getString(R.string.yesterday) + " " + String.valueOf(String.valueOf(YersterDay)) + " " + getString(R.string.hours_time));
//        HoursText.setText(String.valueOf(String.valueOf(Hours)));
        dialog.show();
        fillStats();


        if (mTimer != null) // Cancel if already existed
            mTimer.cancel();
        else
            mTimer = new Timer();   //recreate new

//        mTimer.scheduleAtFixedRate(new TimeDisplay(), 1000, notify);   //Schedule task
        return view;
    }

    private void fillStats() {
        if (hasPermission()) {
            initAppHelper(getActivity());
            process();
        } else {
            requestPermission();
            dialog.dismiss();

        }
    }

    private void requestPermission() {
        Toast.makeText(getActivity(), "Need to request permission", Toast.LENGTH_SHORT).show();
        startActivityForResult(new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS), MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);
    }

    private static final int MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS = 100;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS:
                fillStats();
                break;
        }
    }

    private boolean hasPermission() {
        AppOpsManager appOps = (AppOpsManager)
                getActivity().getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getActivity().getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
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

    private void getStatsInfo() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long millis = 0;
        long millisYesterday = 0;

        switch (selectedPeriod) {
            case DAILY:
                Calendar yesterday = Calendar.getInstance();
                yesterday.add(Calendar.DATE, -1);
                millisYesterday = yesterday.getTimeInMillis();
                millis = calendar.getTimeInMillis();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                String Today = sdf.format(calendar.getTime());
                String Yesterday = sdf.format(yesterday.getTime());
                Log.d("", Today + Yesterday);
                break;
            case WEEKLY:
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                millis = calendar.getTimeInMillis();
                break;
            case MONTHLY:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                millis = calendar.getTimeInMillis();
                break;
            default:
                break;
        }

//        Date startresultdate = new Date(millis);
//        Date endresultdate = new Date(System.currentTimeMillis());
        Map<String, UsageStats> lUsageStatsMap = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lUsageStatsMap = AppHelper.getUsageStatsManager().
                    queryAndAggregateUsageStats(
                            millis,
                            System.currentTimeMillis());
        }

        Map<String, UsageStats> lUsageStatsMapYesterDay = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lUsageStatsMapYesterDay = AppHelper.getUsageStatsManager().
                    queryAndAggregateUsageStats(
                            millisYesterday,
                            System.currentTimeMillis());
        }


        float time = 0;
        if (lUsageStatsMap.size() > 0) {
            for (String Key : lUsageStatsMap.keySet()) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    time = time + AppHelper.getMinutes(lUsageStatsMap.get(Key).
                            getTotalTimeInForeground());
                }
            }
            HoursText.setText(String.valueOf(time));
        }


        float timeYesterday = 0;
        if (lUsageStatsMap.size() > 0) {
            for (String Key : lUsageStatsMapYesterDay.keySet()) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    timeYesterday = timeYesterday + AppHelper.getMinutes(lUsageStatsMapYesterDay.get(Key).
                            getTotalTimeInForeground());
                }
            }
            HoursText.setText(String.valueOf(timeYesterday));
        }


        long SecondsToday = TimeUnit.MINUTES.toSeconds((long) time);
        long SecondsYesterday = TimeUnit.MINUTES.toSeconds((long) timeYesterday);


        String Hours = getDurationString((int) SecondsToday);
        String YersterDay = getDurationString((int) SecondsYesterday);
        TimeHeading.setText(" " + String.valueOf(Hours) + " " + getString(R.string.hours_time));
        Yesterday.setText(getString(R.string.yesterday) + " " + String.valueOf(String.valueOf(YersterDay)) + " " + getString(R.string.hours_time));
        HoursText.setText(String.valueOf(String.valueOf(Hours)));
    }

    private void SetValuse(ArrayList<Float> values) {
    }

    private final int DAILY = AppHelper.DAILY_STATS;
    private final int WEEKLY = AppHelper.WEEKLY_STATS;
    private final int MONTHLY = AppHelper.MONTHLY_STATS;

    private int selectedPeriod = 0;
    private List<String> appList = null;
    private List<String> appNameList = null;


    @SuppressLint("StaticFieldLeak")
    class MyAsyncTask extends AsyncTask<Integer, Void, List<AppItem>> {

        int check = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<AppItem> doInBackground(Integer... integers) {
            check = integers[1];
            return DataManager.getInstance().getApps(getActivity(), integers[0], integers[1]);
        }

        @Override
        protected void onPostExecute(List<AppItem> appItems) {
            mTotal = 0;
            for (AppItem item : appItems) {
                if (item.mUsageTime <= 0) continue;
                mTotal += item.mUsageTime;
            }
            long second = mTotal / 1000L;
            if (check == 0) {
                TimeHeading.setText(" " + getDurationString((int) second) + " " + getString(R.string.hours_time));
                HoursText.setText(String.valueOf(getDurationString((int) second)));
                MyApplication.setSECONDS(second);

            } else {
                Yesterday.setText(getString(R.string.yesterday) + " " + getDurationString((int) second) + " " + getString(R.string.hours_time));
            }
            dialog.dismiss();

        }
    }

    private void process() {
        if (DataManager.getInstance().hasPermission(getActivity())) {
            new MyAsyncTask().execute(0, 0);
            new MyAsyncTask().execute(0, 1);
        }
    }

    private long mTotal;

}
