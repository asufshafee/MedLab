package com.webmarke8.app.medlab.Session;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.Locale;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class GlobalActions extends Activity {

    private static final int NOTIFICATION_ID = 1;
    private static final int TEST_RESULT_ID = 2;


    public static void setFont(Activity currentActivity, TextView view, String fontName) {
        Typeface font = Typeface.createFromAsset(currentActivity.getAssets(), fontName);
        view.setTypeface(font);
    }

    public static void setFontForEditText(Activity currentActivity, EditText view, String fontName) {
        Typeface font = Typeface.createFromAsset(currentActivity.getAssets(), fontName);
        view.setTypeface(font);
    }

    public static void setFontForButton(Activity currentActivity, Button view, String fontName) {
        Typeface font = Typeface.createFromAsset(currentActivity.getAssets(), fontName);
        view.setTypeface(font);
    }


    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    public static void saveDataToSharedPrefrences(String key, String value, Context ctx) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDataToSharedPrefrences(String key, Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        String value = preferences.getString(key, "");
        return value;
    }


    public static void chanegeLocale(String loc, Context ctx) {
        Resources res = ctx.getResources();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(loc);

        res.updateConfiguration(conf, null);
        MyApplication.APP_LANG_CODE = loc;
    }

    public static String distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        float value = Float.valueOf("" + dist);
        String ff = String.format("%.2f", value);

        return ff;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    public void call(String phone, Context ctx) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(GlobalActions.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        ctx.startActivity(callIntent);
    }


    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


}
