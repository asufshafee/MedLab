package com.webmarke8.app.medlab.Activities;

import android.content.SharedPreferences;
import android.os.Build;
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
import com.medialablk.easytoast.EasyToast;
import com.webmarke8.app.medlab.Adapters.Notification_Adapter;
import com.webmarke8.app.medlab.Fragments.Home;
import com.webmarke8.app.medlab.Fragments.Labs_locations;
import com.webmarke8.app.medlab.Fragments.Login;
import com.webmarke8.app.medlab.Fragments.More;
import com.webmarke8.app.medlab.Fragments.Notifications;
import com.webmarke8.app.medlab.Fragments.Sehtak_Bill;
import com.webmarke8.app.medlab.Fragments.Test_Directory;
import com.webmarke8.app.medlab.Fragments.Test_Result_Screen;
import com.webmarke8.app.medlab.Objects.JsonParserLocation;
import com.webmarke8.app.medlab.Objects.SmsNotifications;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.GlobalActions;
import com.webmarke8.app.medlab.Session.MedlabsConstants;
import com.webmarke8.app.medlab.Session.MyApplication;
import com.webmarke8.app.medlab.Session.SharedPrefrenceKeys;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView Home, Heart, More, Location, Result;
    Boolean HomeSelected = true, HeartSelected = false, MoreSelected = false, LocationSelected = false, ResultSelected = false;

    MyApplication myApplication;
    ImageView bandage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HideToolbarWithBack();
        myApplication = (MyApplication) getApplicationContext();
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
                if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("HOME PAGE") || getCurrentFragment().getTag().equals(getApplicationContext().getString(R.string.HOEM_PAGE))) {

                } else {
                    if (myApplication.GetLanguage().equals("en")) {
                        Change_Tittle("HOME PAGE");
                        ShowTopFragment(new Home(), "HOME PAGE");
                    } else {
                        ShowTopFragment(new Home(), getResources().getString(R.string.HOEM_PAGE));
                        Change_Tittle(getResources().getString(R.string.HOEM_PAGE));
                    }


                }
            } else {
                if (myApplication.GetLanguage().equals("en")) {
                    Change_Tittle("HOME PAGE");
                    ShowTopFragment(new Home(), "HOME PAGE");
                } else {
                    ShowTopFragment(new Home(), getResources().getString(R.string.HOEM_PAGE));
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
            if (getCurrentFragment() != null) {
                if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("More") || getCurrentFragment().getTag().equals(getResources().getString(R.string.More))) {

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
        View view = (View) findViewById(R.id.toolbar_with_back);
        view.setVisibility(View.INVISIBLE);
    }

    public void Change_Tittle(String Text) {
        TextView Tittle = (TextView) findViewById(R.id.Tittle);
        Tittle.setText(Text);
        TextView Tittle_Back = (TextView) findViewById(R.id.Tittle_Back);
        Tittle_Back.setText(Text);


    }

    public void ShowTopFragment(Fragment fragment, String Tag) {

        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, Tag).setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack("").commit();
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

        ImageView Share = (ImageView) findViewById(R.id.Share);
        Share.setImageDrawable(getResources().getDrawable(R.drawable.share_icon));

    }

    public void HideShare_toolbar() {

        ImageView Share = (ImageView) findViewById(R.id.Share);
        Share.setImageDrawable(getResources().getDrawable(R.drawable.menu_dot));

    }


    public void Back() {

        findViewById(R.id.Beck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                    HideToolbarWithBack();
                                }
                            }
                        }
                    }
            }
        });
    }

    public void ShowFragment(Fragment fragment, String Tag) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, Tag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
        Change_Tittle(Tag);
    }

    double back_pressed = 0.0;

    @Override
    public void onBackPressed() {
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
                            HideToolbarWithBack();
                        }
                    }
                }
            }

        if (back_pressed + 800 > System.currentTimeMillis()) {

            finish();
        } else {
        }
        back_pressed = System.currentTimeMillis();
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

}
