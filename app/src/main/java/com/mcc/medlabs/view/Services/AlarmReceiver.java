package com.mcc.medlabs.view.Services;

/**
 * Created by GeeksEra on 4/19/2018.
 */

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.AppItem;
import com.mcc.medlabs.view.Objects.DataManager;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by ptyagi on 4/17/17.
 */

/**
 * AlarmReceiver handles the broadcast message and generates Notification
 */
public class AlarmReceiver extends BroadcastReceiver {
    Context context;
    int mTotal;
    String message = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        //Get notification manager to manage/send notifications


        this.context = context;
        Bundle bundle = intent.getExtras();
        message = bundle.getString("Water");
        if (message.equals("1")) {
            int Glass = MyApplication.getGLASS();


            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(Calendar.getInstance().getTime());
            if (MyApplication.getGLASSDate().equals("")) {
                Glass = 0;
                MyApplication.setGLASSDate(date);
            } else {

                if (MyApplication.getGLASSDate().equals(date)) {
                    Glass = MyApplication.getGLASS();
                } else {
                    Glass = 0;
                    MyApplication.setGLASSDate(date);
                    MyApplication.setCheckFor3Hours(true);
                    MyApplication.setCheckFor12Am(true);
                }
            }

            if (true) {

                if (MyApplication.GetLanguageStatic().equals("en")) {
                    sendNotification(String.valueOf(8 - Glass) + " more cup to reach daily intake water", "1");
                } else {
                    sendNotification(String.valueOf(8 - Glass) + " أكثر من كوب للوصول إلى اليومية كمية الماء", "1");
                }

            }
        } else if (message.equals("2")) {

            if (DataManager.getInstance().hasPermission(context)) {
                new MyAsyncTask().execute(0, 0);
            }

        }

    }

    private void sendNotification(String messageBody, String Check) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (Check.equals("1"))
            intent.putExtra("Water", "Water");
        else
            intent.putExtra("Time", "Time");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "1";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Medlabs")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        if (Check.equals("1"))
            notificationManager.notify(11 /* ID of notification */, notificationBuilder.build());
        else
            notificationManager.notify(createID() /* ID of notification */, notificationBuilder.build());

    }

    public int createID() {
        Date now = new Date();
        int id = Integer.parseInt(new SimpleDateFormat("ddHHmmss", Locale.US).format(now));
        return id;
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
            return DataManager.getInstance().getApps(context, integers[0], integers[1]);
        }

        @Override
        protected void onPostExecute(List<AppItem> appItems) {
            mTotal = 0;
            for (AppItem item : appItems) {
                if (item.mUsageTime <= 0) continue;
                mTotal += item.mUsageTime;
            }


            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(Calendar.getInstance().getTime());
            if (MyApplication.getGLASSDate().equals("")) {
                MyApplication.setGLASSDate(date);
            } else {

                if (MyApplication.getGLASSDate().equals(date)) {

                } else {
                    MyApplication.setGLASSDate(date);
                    MyApplication.setCheckFor3Hours(true);
                    MyApplication.setCheckFor12Am(true);
                }
            }

            long Seconds = mTotal / 1000L;
            String Time = getDurationString((int) Seconds);
            int hours = (int) (Seconds / 3600);
            if (hours >= 3 && MyApplication.isCheckFor3Hours()) {
                sendNotification("Today you have used your mobile " + Time + " Hours ", "2");
                MyApplication.setCheckFor3Hours(false);

            }

            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Log.d("TodayDate", "Today's date is " + dateFormat.format(cal.getTime()));
            String Today = dateFormat.format(cal.getTime());

            int hourscheckfor12pm = cal.get(Calendar.HOUR_OF_DAY);
            if (hourscheckfor12pm == 12 && MyApplication.isCheckFor12Am()) {
                MyApplication.setCheckFor12Am(false);
                sendNotification("Today you have used your mobile " + Time + " Hours ", "2");

            }


        }
    }


}