package com.mcc.medlabs.view.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mcc.medlabs.view.Adapters.Tips_Adapter;
import com.mcc.medlabs.view.Fragments.Home;
import com.mcc.medlabs.view.Fragments.Labs_locations;
import com.mcc.medlabs.view.Fragments.Login;
import com.mcc.medlabs.view.Fragments.More;
import com.mcc.medlabs.view.Fragments.Notifications;
import com.mcc.medlabs.view.Fragments.Sehtak_Bill;
import com.mcc.medlabs.view.Fragments.Test_Directory;
import com.mcc.medlabs.view.Fragments.Test_Result_Screen;
import com.mcc.medlabs.view.Fragments.Time_Used;
import com.mcc.medlabs.view.Fragments.Water_Remainder;
import com.mcc.medlabs.view.Objects.JsonParserLocation;
import com.mcc.medlabs.view.Objects.SmsNotifications;
import com.mcc.medlabs.view.Objects.Tips_Object;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Services.AlarmReceiver;
import com.mcc.medlabs.view.Session.GlobalActions;
import com.mcc.medlabs.view.Session.MedlabsConstants;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Session.SharedPrefrenceKeys;
import com.mcc.medlabs.view.Utils.AppUtils;
import com.medialablk.easytoast.EasyToast;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView Home, Heart, More, Location, Result;
    Boolean HomeSelected = true, HeartSelected = false, MoreSelected = false, LocationSelected = false, ResultSelected = false;

    MyApplication myApplication;
    ImageView bandage;
    ImageView Share;
    TextView Download;
    Home HomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        GetTips();

        NotificationWater();
        TimeUsedNotification();
        HomeFragment = new Home();


        Tittle_Back = (TextView) findViewById(R.id.Tittle_Back);

        HideToolbarWithBack();
        myApplication = (MyApplication) getApplicationContext();


        if (myApplication.isSplashIn()) {
            Intent intent = new Intent(getApplicationContext(), Help_Screen.class);
            startActivity(intent);
            myApplication.setIsSplash(false);
        }


        Share = (ImageView) findViewById(R.id.Share);
        Download = (TextView) findViewById(R.id.Download);
        GetNotifications();
        Home = (ImageView) findViewById(R.id.Home);
        Heart = (ImageView) findViewById(R.id.Heart);
        More = (ImageView) findViewById(R.id.More);
        Location = (ImageView) findViewById(R.id.Location);
        Result = (ImageView) findViewById(R.id.Result);
        HideToolbarWithBack();
        bandage = (ImageView) findViewById(R.id.bandage);

        Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon_selected));
        findViewById(R.id.Notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myApplication.GetLanguage().equals("en"))
                    ShowFragment(new Notifications(), "Notifications");
                else
                    ShowFragment(new Notifications(), getResources().getString(R.string.Notification));
            }
        });
        findViewById(R.id.Search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myApplication.GetLanguage().equals("en"))
                    ShowFragment(new Test_Directory(), "Test Directory");
                else
                    ShowFragment(new Test_Directory(), getResources().getString(R.string.Test_Directory));


            }
        });
        Back();
        Refresh();

        if (getIntent().getStringExtra("Water") != null && getIntent().getStringExtra("Water").equals("Water")) {
            if (myApplication.GetLanguage().equals("en"))
                ShowFragment(new Water_Remainder(), "Water Remainder");
            else
                ShowFragment(new Water_Remainder(), getResources().getString(R.string.water_remeinder));
        } else if (getIntent().getStringExtra("Time") != null && getIntent().getStringExtra("Time").equals("Time")) {
            if (myApplication.GetLanguage().equals("en"))
                ShowFragment(new Time_Used(), "Time Used");
            else
                ShowFragment(new Time_Used(), getResources().getString(R.string.time_used));
        } else {

            if (getIntent().getStringExtra("Notification") != null && getIntent().getStringExtra("Notification").equals("no")) {

            } else {
                if (myApplication.GetLanguage().equals("en"))
                    ShowFragment(new Notifications(), "Notifications");
                else
                    ShowFragment(new Notifications(), getResources().getString(R.string.Notification));
            }

        }


    }

    public static long toMilliSeconds() {
        return (long) (1 * 24 * 60 * 60 * 1000);
    }

    public void More(View view) {
        MoreSelected = true;
        HomeSelected = false;
        HeartSelected = false;
        LocationSelected = false;
        ResultSelected = false;
        Refresh();
    }

    public void Location(View view) {
        MoreSelected = false;
        HomeSelected = false;
        HeartSelected = false;
        LocationSelected = true;
        ResultSelected = false;
        Refresh();
    }

    public void Result(View view) {
        MoreSelected = false;
        HomeSelected = false;
        HeartSelected = false;
        LocationSelected = false;
        ResultSelected = true;
        Refresh();
    }

    public void Heart(View view) {
        MoreSelected = false;
        HomeSelected = false;
        HeartSelected = true;
        LocationSelected = false;
        ResultSelected = false;
        Refresh();
    }

    public void Home(View view) {
        MoreSelected = false;
        HomeSelected = true;
        HeartSelected = false;
        LocationSelected = false;
        ResultSelected = false;
        Refresh();
    }

    public void Refresh() {

        if (HomeSelected) {
            if (getCurrentFragment() != null) {
                String Tag = getCurrentFragment().getTag();
                if (getCurrentFragment().getTag().equals("HOME PAGE") || getCurrentFragment().getTag().equals(getApplicationContext().getString(R.string.HOEM_PAGE))) {

                    BackPressButton();

                } else {
                    if (myApplication.GetLanguage().equals("en")) {
                        Change_Tittle("HOME PAGE");
                        ShowTopFragment(HomeFragment, "HOME PAGE");
                    } else {
                        ShowTopFragment(HomeFragment, getResources().getString(R.string.HOEM_PAGE));
                        Change_Tittle(getResources().getString(R.string.HOEM_PAGE));
                    }

                }
            } else {
                if (myApplication.GetLanguage().equals("en")) {
                    Change_Tittle("HOME PAGE");
                    ShowTopFragment(HomeFragment, "HOME PAGE");
                } else {
                    ShowTopFragment(HomeFragment, getResources().getString(R.string.HOEM_PAGE));
                    Change_Tittle(getResources().getString(R.string.HOEM_PAGE));
                }

            }


            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon_selected));
            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon));
            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon));
            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon));
            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_iconn));
        }


        if (HeartSelected) {
            if (getCurrentFragment() != null) {
                if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("SEHTAK BIL DENIA") || getCurrentFragment().getTag().equals(getResources().getString(R.string.Sehtak_Bil_Denia))) {
                    BackPressButton();

                } else {
                    if (myApplication.GetLanguage().equals("en")) {
                        ShowTopFragment(new Sehtak_Bill(), "SEHTAK BIL DENIA");
                        Change_Tittle("SEHTAK BIL DENIA");
                    } else {
                        ShowTopFragment(new Sehtak_Bill(), getResources().getString(R.string.Sehtak_Bil_Denia));
                        Change_Tittle(getResources().getString(R.string.Sehtak_Bil_Denia));
                    }


                }
            } else {
                if (myApplication.GetLanguage().equals("en")) {
                    ShowTopFragment(new Sehtak_Bill(), "SEHTAK BIL DENIA");
                    Change_Tittle("SEHTAK BIL DENIA");
                } else {
                    ShowTopFragment(new Sehtak_Bill(), getResources().getString(R.string.Sehtak_Bil_Denia));
                    Change_Tittle(getResources().getString(R.string.Sehtak_Bil_Denia));
                }

            }


            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_icon_selected));
            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon));
            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon));
            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon));
            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon));
        }


        if (ResultSelected) {
            if (getCurrentFragment() != null) {
                if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("MedLabs") || getCurrentFragment().getTag().equals("Test Results") || getCurrentFragment().getTag().equals(getResources().getString(R.string.Test_Results))) {
                    BackPressButton();

                } else {

                    if (!myApplication.isLoggedIn()) {
                        Change_Tittle("MedLabs");
                        ShowTopFragment(new Login(), "MedLabs");
                    } else {
                        {
                            if (myApplication.GetLanguage().equals("en")) {
                                Change_Tittle("Test Results");
                                ShowTopFragment(new Test_Result_Screen(), "Test Results");
                            } else {
                                ShowTopFragment(new Test_Result_Screen(), getResources().getString(R.string.Test_Results));
                                Change_Tittle(getResources().getString(R.string.Test_Results));
                            }


                        }

                    }
                }

            } else {
                if (!myApplication.isLoggedIn()) {
                    Change_Tittle("MedLabs");
                    ShowTopFragment(new Login(), "MedLabs");
                } else {
                    {
                        if (myApplication.GetLanguage().equals("en")) {
                            Change_Tittle("Test Results");
                            ShowTopFragment(new Test_Result_Screen(), "Test Results");
                        } else {
                            ShowTopFragment(new Test_Result_Screen(), getResources().getString(R.string.Test_Results));
                            Change_Tittle(getResources().getString(R.string.Test_Results));
                        }


                    }
                }


            }

            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon_selected));
            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon));
            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon));
            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon));
            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_iconn));
        }

        if (LocationSelected) {
            if (getCurrentFragment() != null) {
                if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("Labs Locations") || getCurrentFragment().getTag().equals(getResources().getString(R.string.Labs_Locations))) {
                    BackPressButton();

                } else {
                    if (myApplication.GetLanguage().equals("en")) {
                        ShowTopFragment(new Labs_locations(), "Labs Locations");
                        Change_Tittle("Labs Locations");
                    } else {
                        ShowTopFragment(new Labs_locations(), getResources().getString(R.string.Labs_Locations));
                        Change_Tittle(getResources().getString(R.string.Labs_Locations));
                    }
                }

            } else {
                if (myApplication.GetLanguage().equals("en")) {
                    ShowTopFragment(new Labs_locations(), "Labs Locations");
                    Change_Tittle("Labs Locations");
                } else {
                    ShowTopFragment(new Labs_locations(), getResources().getString(R.string.Labs_Locations));
                    Change_Tittle(getResources().getString(R.string.Labs_Locations));
                }
            }

            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon_selected));
            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon));
            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon));
            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon));
            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_iconn));
        }

        if (MoreSelected) {
            int sixe = getSupportFragmentManager().getBackStackEntryCount();
            if (getCurrentFragment() != null) {
                if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("More") || getCurrentFragment().getTag().equals(getResources().getString(R.string.More))) {
                    if (getSupportFragmentManager().getBackStackEntryCount() == 3)
                        if (myApplication.GetLanguage().equals("en")) {
                            ShowTopFragment(new More(), "More");
                            Change_Tittle("More");
                        } else {
                            ShowTopFragment(new More(), getResources().getString(R.string.More));
                            Change_Tittle(getResources().getString(R.string.More));
                        }
                    if (getSupportFragmentManager().getBackStackEntryCount() == 2)
                        BackPressButton();


                } else {
                    if (myApplication.GetLanguage().equals("en")) {
                        ShowTopFragment(new More(), "More");
                        Change_Tittle("More");
                    } else {
                        ShowTopFragment(new More(), getResources().getString(R.string.More));
                        Change_Tittle(getResources().getString(R.string.More));
                    }
                }
            } else {
                if (myApplication.GetLanguage().equals("en")) {
                    ShowTopFragment(new More(), "More");
                    Change_Tittle("More");
                } else {
                    ShowTopFragment(new More(), getResources().getString(R.string.More));
                    Change_Tittle(getResources().getString(R.string.More));
                }
            }


            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon_selected));
            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon));
            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon));
            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon));
            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_iconn));
        }


    }

    public void HideToolbarWithBack() {


        if (!Tittle_Back.getText().equals(getString(R.string.Manage_My_Helth))) {
            View view = (View) findViewById(R.id.toolbar_with_back);
            view.setVisibility(View.INVISIBLE);
        }


    }

    TextView Tittle_Back;

    public void Change_Tittle(String Text) {
        TextView Tittle = (TextView) findViewById(R.id.Tittle);
        Tittle.setText(Text);
        Tittle_Back = (TextView) findViewById(R.id.Tittle_Back);
        Tittle_Back.setText(Text);


    }

    public void ShowTopFragment(Fragment fragment, String Tag) {

        if (false) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, Tag).setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).commit();

        } else {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, Tag).setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack("").commit();

        }
    }

    private Fragment getCurrentFragment() {
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getUserVisibleHint())
                return fragment;
        }
        return null;
    }

    public void ShowBack_toolbar() {


        View view = (View) findViewById(R.id.toolbar_with_back);
        view.setVisibility(View.VISIBLE);


    }

    public void ShowShare_toolbar() {

        Share.setImageDrawable(getResources().getDrawable(R.drawable.share_icon));
        Share.setVisibility(View.VISIBLE);

    }

    public void HideShare_toolbar() {

        Share.setImageDrawable(getResources().getDrawable(R.drawable.menu_dot));
        Share.setVisibility(View.GONE);
        HideDownlaod_toolbar();

    }

    public void ShowDownload_toolbar() {

        Download.setVisibility(View.VISIBLE);

    }

    public void HideDownlaod_toolbar() {

//        Download.setImageDrawable(getResources().getDrawable(R.drawable.menu_dot));
        Download.setVisibility(View.GONE);

    }


    public void Back() {

        findViewById(R.id.Beck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackPressButton();
            }
        });
    }

    public void BackPressButton() {
        int Count = getSupportFragmentManager().getBackStackEntryCount();
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            if (!getCurrentFragment().getTag().equals("HOME PAGE") || !getCurrentFragment().getTag().equals(getString(R.string.HOEM_PAGE))) {

                getSupportFragmentManager().popBackStack();
                if (myApplication.GetLanguage().equals("en")) {
                    if (getCurrentFragment() != null) {

                        if (getCurrentFragment().getTag().equals("Lab_Locations_Details")) {


                            Change_Tittle("Labs Locations");

                        } else {

                            Change_Tittle(getCurrentFragment().getTag());

                        }
                        String Tittle = getCurrentFragment().getTag();
                        if (getCurrentFragment().getTag().equals("More") || getCurrentFragment().getTag().equals("Labs Locations") || getCurrentFragment().getTag().equals("MedLabs") || getCurrentFragment().getTag().equals("SEHTAK BIL DENIA") || getCurrentFragment().getTag().equals("HOME PAGE") || getCurrentFragment().getTag().equals("Test Results")) {
                            if (getSupportFragmentManager().getBackStackEntryCount() == 2)
                                HideToolbarWithBack();
                        }
                    }
                } else {
                    if (getCurrentFragment() != null) {

                        if (getCurrentFragment().getTag().equals("Lab_Locations_Details")) {


                            Change_Tittle(getString(R.string.Labs_Locations));

                        } else {

                            Change_Tittle(getCurrentFragment().getTag());

                        }
                        String Tittle = getCurrentFragment().getTag();
                        if (getCurrentFragment().getTag().equals(getString(R.string.More)) || getCurrentFragment().getTag().equals(getString(R.string.Labs_Locations)) || getCurrentFragment().getTag().equals("MedLabs") || getCurrentFragment().getTag().equals(getString(R.string.Sehtak_Bil_Denia)) || getCurrentFragment().getTag().equals(getString(R.string.HOEM_PAGE)) || getCurrentFragment().getTag().equals(getString(R.string.Test_Results))) {
                            if (getSupportFragmentManager().getBackStackEntryCount() == 2)
                                HideToolbarWithBack();
                        }
                    }
                }
            }
    }

    public void ShowFragment(Fragment fragment, String Tag) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, Tag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
        Change_Tittle(Tag);
    }

    double back_pressed = 0.0;
    private boolean doubleBackToExitPressedOnce = false;
    private boolean doubleBackToExitPressedOnce1 = false;

    private static final int TIME_INTERVAL = 700; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = true;
                doubleBackToExitPressedOnce1 = true;
            }
        }, 2000);
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            if (!getCurrentFragment().getTag().equals("HOME PAGE") || !getCurrentFragment().getTag().equals(getString(R.string.HOEM_PAGE))) {

                getSupportFragmentManager().popBackStack();
                if (myApplication.GetLanguage().equals("en")) {
                    if (getCurrentFragment() != null) {

                        if (getCurrentFragment().getTag().equals("Lab_Locations_Details")) {


                            Change_Tittle("Labs Locations");

                        } else {

                            Change_Tittle(getCurrentFragment().getTag());

                        }
                        String Tittle = getCurrentFragment().getTag();
                        if (getCurrentFragment().getTag().equals("More") || getCurrentFragment().getTag().equals("Labs Locations") || getCurrentFragment().getTag().equals("MedLabs") || getCurrentFragment().getTag().equals("SEHTAK BIL DENIA") || getCurrentFragment().getTag().equals("HOME PAGE") || getCurrentFragment().getTag().equals("Test Results")) {
                            if (getSupportFragmentManager().getBackStackEntryCount() == 2)
                                HideToolbarWithBack();
                            GetNotifications();
                        }
                    }
                } else {
                    if (getCurrentFragment() != null) {

                        if (getCurrentFragment().getTag().equals("Lab_Locations_Details")) {


                            Change_Tittle(getString(R.string.Labs_Locations));

                        } else {

                            Change_Tittle(getCurrentFragment().getTag());

                        }
                        String Tittle = getCurrentFragment().getTag();
                        if (getCurrentFragment().getTag().equals(getString(R.string.More)) || getCurrentFragment().getTag().equals(getString(R.string.Labs_Locations)) || getCurrentFragment().getTag().equals("MedLabs") || getCurrentFragment().getTag().equals(getString(R.string.Sehtak_Bil_Denia)) || getCurrentFragment().getTag().equals(getString(R.string.HOEM_PAGE)) || getCurrentFragment().getTag().equals(getString(R.string.Test_Results))) {
                            if (getSupportFragmentManager().getBackStackEntryCount() == 2)
                                HideToolbarWithBack();
                        }
                    }
                }
            }

        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            if (doubleBackToExitPressedOnce) {
//            finish();
                if (doubleBackToExitPressedOnce1) {
//                finish();
                } else {
                    doubleBackToExitPressedOnce1 = true;
//                EasyToast.custom(getApplicationContext(),"Press Back again to Exit");
                }
            } else {


            }

        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                if (true)
                    finish();
            } else {
                if (true) {
                    EasyToast.custom(getApplicationContext(), "Press Back again to Exit");
                }
                mBackPressed = System.currentTimeMillis();
            }


//        this.doubleBackToExitPressedOnce = true;
//        MoreSelected = false;
//        HomeSelected = true;
//        HeartSelected = false;
//        LocationSelected = false;
//        ResultSelected = false;
//        Refresh();

    }

    public void ChangeTintColors(ImageView imageView, TextView textView, Fragment fragment, String Tag) {
        imageView.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
        textView.setTextColor(getResources().getColor(R.color.red));
        ShowFragment(fragment, Tag);
    }


    public String Check() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String LNID = preferences.getString(SharedPrefrenceKeys.LAST_NOTIFICATION_ID, "");
        String CoNV = preferences.getString(SharedPrefrenceKeys.COUNT_OF_VISITS, "");
        String c_id = preferences.getString(SharedPrefrenceKeys.SELECTED_COUNTRY_ID, "");

        if (LNID.equals("")) {
            MedlabsConstants.LAST_NOTIFICATION_ID_VALUE = 0;
        } else {
            MedlabsConstants.LAST_NOTIFICATION_ID_VALUE = Integer.parseInt(GlobalActions.getDataToSharedPrefrences(SharedPrefrenceKeys.LAST_NOTIFICATION_ID, getApplicationContext()));
        }

        if (CoNV.equals("")) {
            MedlabsConstants.COUNT_OF_VISITS_VALUE = 0;
        } else {
            MedlabsConstants.COUNT_OF_VISITS_VALUE = Integer.parseInt(GlobalActions.getDataToSharedPrefrences(SharedPrefrenceKeys.COUNT_OF_VISITS, getApplicationContext()));
        }

        if (c_id.equals("")) {
            MedlabsConstants.COUNTERY_ID = "1";
        } else {
            MedlabsConstants.COUNTERY_ID = GlobalActions.getDataToSharedPrefrences(SharedPrefrenceKeys.SELECTED_COUNTRY_ID, getApplicationContext());
        }


        String request = "";
        request += "{";
        request += "\"CoNV\":" + "\"" + MedlabsConstants.COUNT_OF_VISITS_VALUE + "\"" + ",";
        request += "\"Countryid\":" + "\"" + MedlabsConstants.COUNTERY_ID + "\"" + ",";
        request += "\"LNId\":" + "\"" + MedlabsConstants.LAST_NOTIFICATION_ID_VALUE + "\"" + ",";

        request += "\"device\":{";
        request += "\"Platform\":" + "\"Android\"" + ",";
        request += "\"Resolution\":" + "\"" + MedlabsConstants.RESOLUATION + "\"" + ",";
        request += "\"Version\":" + "\"" + MedlabsConstants.APP_VERSION + "\"";
        request += "},";

        request += "\"userId\":" + "\"" + MedlabsConstants.USER_ID + "\"";
        request += "}";

        Log.d(MedlabsConstants.TAG, ">>>>" + request);
        return request;
    }

    public void GetNotifications() {


        final String request = Check();

        String Url = myApplication.GetNotificationNumber;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                SmsNotifications bean = new SmsNotifications();
                Gson gson = new Gson();
                bean = gson.fromJson(response, SmsNotifications.class);
                MedlabsConstants.NOTIFICATION_COUNT = String.valueOf(bean.getSumOfNotifcation());
                if (!MedlabsConstants.NOTIFICATION_COUNT.equals("0")) {
                    bandage.setVisibility(View.VISIBLE);
                } else {
                    bandage.setVisibility(View.GONE);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        if (myApplication.GetLanguage().equals("en"))
////                            EasyToast.error(getApplicationContext(), "Something Went Wrong!!");
//                        else
//                            EasyToast.error(getApplicationContext(), " هناك خطأ ما");
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ServerError) {
                        } else if (error instanceof NetworkError) {
                        } else if (error instanceof ParseError) {
                        }
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return request == null ? null : request.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", request, "utf-8");
                    return null;
                }
            }


            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    private void TimeUsedNotification() {
        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, 1);        // add 5 minutes to the calendar object
        cal.add(Calendar.MILLISECOND, 60000);
        Intent intentNOtificaation = new Intent(getApplicationContext(), AlarmReceiver.class);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Log.d("TodayDate", "Today's date is " + dateFormat.format(cal.getTime()));
        String Today = dateFormat.format(cal.getTime());
        intentNOtificaation.putExtra("Water", "2");

        // In reality, you would want to have a static variable for the request code instead of 192837
        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 192837, intentNOtificaation, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager service
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60000 , sender);

    }


    private void NotificationWater() {

        Calendar cal = Calendar.getInstance();
        // add 5 minutes to the calendar object
        cal.add(Calendar.MILLISECOND, 60000 * 60);
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("Water", "1");
        // In reality, you would want to have a static variable for the request code instead of 192837
        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 1928237, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager service
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60000 * 60, sender);

    }

    java.util.List<Tips_Object.TipsObObject> List;
    public static String TipName = "", TipDetails = "";

    private void GetTips() {

        Gson gson = new Gson();
        final JsonParserLocation jsonParserLocation = new JsonParserLocation();
        JsonParserLocation.DeviceObject deviceObject = new JsonParserLocation.DeviceObject();
        deviceObject.setPlatform("Android");
        deviceObject.setResolution(myApplication.RESOLUATION);
        deviceObject.setVersion(myApplication.APP_VERSION);
        jsonParserLocation.setWSPassword("Medl@p$app17");
        jsonParserLocation.setWSUsername("Medlabs");
        jsonParserLocation.setUserId(Integer.parseInt(myApplication.USER_ID));
        jsonParserLocation.setDevice(deviceObject);
        jsonParserLocation.setPageIndex(1);
        jsonParserLocation.setPageSize(1000);
        final String request = gson.toJson(jsonParserLocation);

        String Url = myApplication.GetTips;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (response.contains("Success")) {

                    Gson gson = new Gson();
                    Tips_Object tips = new Tips_Object();
                    tips = gson.fromJson(response, Tips_Object.class);
                    List = tips.getTipsOb();

                    Random rand = new Random();

                    if (myApplication.GetLanguage().equals("en")) {
                        TipName = List.get(rand.nextInt(List.size() - 1)).getTitle();
                        try {
                            TipDetails = List.get(List.size() - 1).getText().substring(0, 85) + " " + getString(R.string.more);

                        } catch (Exception Ex) {
                            TipDetails = List.get(List.size() - 1).getText() + " " + getString(R.string.more);
                        }
                    } else {
                        TipName = List.get(List.size() - 1).getTitleAr();
                        try {
                            TipDetails = List.get(List.size() - 1).getTextAr().substring(0, 85) + " " + getString(R.string.more);
                        } catch (Exception Ex) {
                            TipDetails = List.get(List.size() - 1).getTextAr() + " " + getString(R.string.more);
                        }
                    }

                    HomeFragment.Name.setText(TipName);
                    HomeFragment.Details.setText(TipDetails);

                } else {
                    if (myApplication.GetLanguage().equals("en"))
                        EasyToast.error(MainActivity.this, "Something Went Wrong!!");
                    else
                        EasyToast.error(MainActivity.this, " هناك خطأ ما");
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (AppUtils.isNetworkAvailable(MainActivity.this)) {
                            EasyToast.error(MainActivity.this, getString(R.string.NO_INTERNET_CONNECTION));
                        } else {
                            EasyToast.error(MainActivity.this, getString(R.string.something_went_wrong));
                        }

                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ServerError) {
                        } else if (error instanceof NetworkError) {
                        } else if (error instanceof ParseError) {
                        }
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return request == null ? null : request.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", request, "utf-8");
                    return null;
                }
            }


            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}
