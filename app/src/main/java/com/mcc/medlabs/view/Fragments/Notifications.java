package com.mcc.medlabs.view.Fragments;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Adapters.Notification_Adapter;
import com.mcc.medlabs.view.Objects.Notification;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.GlobalActions;
import com.mcc.medlabs.view.Session.MedlabsConstants;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Session.SharedPrefrenceKeys;
import com.mcc.medlabs.view.Utils.AppUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notifications extends Fragment {


    Dialog Progress;
    RecyclerView recycle;
    java.util.List<Notification.RespOBJObject> List;
    MyApplication myApplication;


    public Notifications() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        myApplication = (MyApplication) getActivity().getApplicationContext();
        ((MainActivity) getActivity()).ShowBack_toolbar();
        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en")) {
            ((MainActivity) getActivity()).Change_Tittle("Notification");

        } else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Notification));

        }


        recycle = (RecyclerView) view.findViewById(R.id.recycle);
        Progress = AppUtils.LoadingSpinner(getActivity());
        LoadData();


        return view;
    }


    public void LoadData() {
        Progress.dismiss();
        GetNotifications();
    }

    private String prepareJsonRequest() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String value = preferences.getString(SharedPrefrenceKeys.LAST_NOTIFICATION_ID, "");

        if (value.equals("")) {
            MedlabsConstants.LAST_NOTIFICATION_ID_VALUE = 0;
        } else {
            MedlabsConstants.LAST_NOTIFICATION_ID_VALUE = Integer.parseInt(GlobalActions.getDataToSharedPrefrences(SharedPrefrenceKeys.LAST_NOTIFICATION_ID, getActivity()));
        }

        String coNV = preferences.getString(SharedPrefrenceKeys.COUNT_OF_VISITS, "");
        if (coNV.equals("")) {
            MedlabsConstants.COUNT_OF_VISITS_VALUE = 0;
        } else {
            MedlabsConstants.COUNT_OF_VISITS_VALUE = Integer.parseInt(GlobalActions.getDataToSharedPrefrences(SharedPrefrenceKeys.COUNT_OF_VISITS, getActivity()));
        }

        if (MedlabsConstants.COUNTERY_ID.equals("")) {
            MedlabsConstants.COUNTERY_ID = "1";
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
        return request;
    }

    private void GetNotifications() {

        final String request = prepareJsonRequest();

        Progress.show();
        String Url = myApplication.GetNotification;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Progress.dismiss();

                List = new ArrayList<>();
                Gson gson = new Gson();
                Notification notifications = new Notification();
                notifications = gson.fromJson(response, Notification.class);
                if (notifications.getRespOBJ() != null)
                    List = notifications.getRespOBJ();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                Notification_Adapter Adapter = new Notification_Adapter(List, getActivity());
                recycle.setLayoutManager(linearLayoutManager);
                recycle.setItemAnimator(new DefaultItemAnimator());
                recycle.setAdapter(Adapter);


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Progress.dismiss();
                        if (AppUtils.isNetworkAvailable(getActivity())) {
                            EasyToast.error(getActivity(), getString(R.string.NO_INTERNET_CONNECTION));
                        } else {
                            EasyToast.error(getActivity(), getString(R.string.something_went_wrong));
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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


}
