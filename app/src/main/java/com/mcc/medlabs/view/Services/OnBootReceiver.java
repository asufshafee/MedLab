package com.mcc.medlabs.view.Services;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OnBootReceiver extends BroadcastReceiver {
    private static final String TAG = "PhoneUsages.OnBootReceiver";
    public OnBootReceiver() {
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Phone booted, start listener service");
        Intent service = new Intent(context, TimeUsed.class);
        context.startService(service);
    }
}
