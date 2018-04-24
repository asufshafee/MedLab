package com.mcc.medlabs.view.Fragments;


import android.app.Dialog;
import android.os.Bundle;
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
import com.mcc.medlabs.view.Adapters.Test_Result_Adapter;
import com.mcc.medlabs.view.Objects.JsonParserLogin;
import com.mcc.medlabs.view.Objects.Login_Object;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Test_Result_Screen extends Fragment {


    Login_Object login_object;
    MyApplication myApplication;
    Dialog Progress;


    RecyclerView recycle;
    java.util.List<Login_Object.PatientsObject> List;

    public Test_Result_Screen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test__result__screen, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        ((MainActivity) getActivity()).GetNotifications();

        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("Test Results");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Test_Results));

        }
        ((MainActivity) getActivity()).HideToolbarWithBack();
        Progress = AppUtils.LoadingSpinner(getActivity());
        recycle = (RecyclerView) view.findViewById(R.id.recycle);

        List = new ArrayList<>();
        if (myApplication.isLoggedIn()) {

            if (getArguments() != null) {
                login_object = (Login_Object) getArguments().getSerializable("login_object");
                List = login_object.getPatients();
                LoadData();
            } else
                Login();

        }


        if (getArguments() != null) {
            login_object = (Login_Object) getArguments().getSerializable("login_object");
            List = login_object.getPatients();
            LoadData();
        }


        return view;
    }

    public void LoadData() {
        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            Test_Result_Adapter Adapter = new Test_Result_Adapter(List, getActivity());
            recycle.setLayoutManager(linearLayoutManager);
            recycle.setItemAnimator(new DefaultItemAnimator());
            recycle.setAdapter(Adapter);

        } catch (Exception Ex) {

        }

    }


    private void Login() {


        Gson gson = new Gson();
        final JsonParserLogin jsonParserLogin = myApplication.getLoginSessionJsonParserLogin();
        final String request = gson.toJson(jsonParserLogin);

        Progress.show();
        String Url = myApplication.LinkedUserLogin;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Progress.dismiss();
                if (response.contains("Success")) {

                    Gson gson = new Gson();
                    Login_Object login_object = new Login_Object();
                    login_object = gson.fromJson(response, Login_Object.class);
                    List = login_object.getPatients();
                    LoadData();

                } else {
                    if (myApplication.GetLanguage().equals("en"))
                        EasyToast.error(getActivity(), "Something Went Wrong!!");
                    else
                        EasyToast.error(getActivity(), " هناك خطأ ما");
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Progress.dismiss();
                            if (AppUtils.isNetworkAvailable(getActivity())) {
                                EasyToast.error(getActivity(), getString(R.string.NO_INTERNET_CONNECTION));
                            } else {
                                EasyToast.error(getActivity(), getString(R.string.something_went_wrong));
                            }

                        } catch (Exception Ex) {

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
