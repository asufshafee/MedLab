package com.mcc.medlabs.view.Fragments;


import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.*;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Activities.Splash;
import com.mcc.medlabs.view.Objects.DataManager;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.medialablk.easytoast.EasyToast;

import java.util.Locale;

import static com.mcc.medlabs.view.Fragments.AppHelper.initAppHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class Manage_My_Health extends Fragment {


    Boolean TintWater = false, TintBMI = false, TintTime = false;

    ImageView WaterIcon, BMIIcon, TimeIcon;
    TextView WaterName, BMIName, TimeName;
    MyApplication myApplication;

    public Manage_My_Health() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_manage__my__health, container, false);

        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en")) {
            ((MainActivity) getActivity()).Change_Tittle("Manage My Health");

        } else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Manage_My_Helth));

        }

        ((MainActivity) getActivity()).ShowBack_toolbar();

        WaterIcon = (ImageView) view.findViewById(R.id.WaterIcon);
        WaterName = (TextView) view.findViewById(R.id.WaterName);

        TimeIcon = (ImageView) view.findViewById(R.id.TimeIcon);
        TimeName = (TextView) view.findViewById(R.id.TimeName);

        BMIIcon = (ImageView) view.findViewById(R.id.BMIIcon);
        BMIName = (TextView) view.findViewById(R.id.BmiName);
        TintWater = false;
        TintBMI = false;
        TintTime = false;
        Refresh();


        view.findViewById(R.id.Water).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TintWater = true;
                TintBMI = false;
                TintTime = false;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        RemoveTint(WaterIcon, WaterName);

                    }
                }, 1000);

                ((MainActivity) getActivity()).ChangeTintColors(WaterIcon, WaterName, new Water_Remainder(), getString(R.string.water_remeinder));
                Refresh();
            }
        });
        view.findViewById(R.id.Time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!MyApplication.isTimeUsedPermission()) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fillStats();
                    } else {
                        EasyToast.info(getActivity(), "Device Not Supported");
                    }

                } else {

                    TimeUsageDialog();

                }
                Refresh();

            }
        });
        view.findViewById(R.id.BMI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TintBMI = true;
                TintWater = false;
                TintTime = false;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        RemoveTint(BMIIcon, BMIName);

                    }
                }, 1000);

                ((MainActivity) getActivity()).ChangeTintColors(BMIIcon, BMIName, new BMI_Calculater(), getString(R.string.BMI_Calculator));
                Refresh();
            }
        });
        view.findViewById(R.id.Click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

    public void Refresh() {
        if (TintWater) {
            RemoveTint(TimeIcon, TimeName);
            RemoveTint(BMIIcon, BMIName);
        }

        if (TintBMI) {
            RemoveTint(TimeIcon, TimeName);
            RemoveTint(WaterIcon, WaterName);
        }

        if (TintTime) {
            RemoveTint(WaterIcon, WaterName);
            RemoveTint(BMIIcon, BMIName);
        }
    }

    public void RemoveTint(ImageView imageView, TextView textView) {
        imageView.setColorFilter(getResources().getColor(R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);
        textView.setTextColor(getActivity().getResources().getColor(R.color.textColor));
    }


    public void TimeUsageDialog() {
        final Dialog dialog = new Dialog(getActivity());
        // inflate the layout
        dialog.setContentView(R.layout.dialog_box_permission);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        dialog.findViewById(R.id.Allow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    fillStats();
                } else {
                    EasyToast.info(getActivity(), "Device Not Supported");
                }

                dialog.dismiss();

            }
        });
        dialog.findViewById(R.id.Deny).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void fillStats() {
        if (hasPermission()) {
            initAppHelper(getActivity());
            process();
        } else {
            requestPermission();

        }
    }

    private void process() {
        if (DataManager.getInstance().hasPermission(getActivity())) {

            TintTime = true;
            TintWater = false;
            TintBMI = false;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // do stuff
                    RemoveTint(TimeIcon, TimeName);

                }
            }, 500);
            ((MainActivity) getActivity()).ChangeTintColors(TimeIcon, TimeName, new Time_Used(), getString(R.string.time_used));

        }
    }

    private void requestPermission() {
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
        }
        startActivityForResult(intent, MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);
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

}
