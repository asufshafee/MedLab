package com.mcc.medlabs.view.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.medialablk.easytoast.EasyToast;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.JsonParserLogin;
import com.mcc.medlabs.view.Objects.Login_Object;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.GlobalActions;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Session.SharedPrefrenceKeys;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
import com.mcc.medlabs.view.Utils.AppUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    MyApplication myApplication;
    EditText Username, Password;
    Dialog Progress;
    CheckBox Remember;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
//        ((MainActivity) getActivity()).Change_Tittle("MedLabs");
        myApplication = (MyApplication) getActivity().getApplicationContext();

        if (myApplication.isLoggedIn()) {
            Fragment fragment = null;
            Class fragmentClass = null;

            fragmentClass = Test_Result_Screen.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment, "Test Results").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

        } else {
            ((MainActivity) getActivity()).GetNotifications();

            ((MainActivity) getActivity()).HideToolbarWithBack();


            Progress = AppUtils.LoadingSpinner(getActivity());
            Remember = (CheckBox) view.findViewById(R.id.Remember);

            Username = (EditText) view.findViewById(R.id.Username);
            Password = (EditText) view.findViewById(R.id.Password);

            view.findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Login();
                }
            });

        }


        return view;
    }


    private void Login() {

        Gson gson = new Gson();
        final JsonParserLogin jsonParserLogin = new JsonParserLogin();
        JsonParserLogin.DeviceObject deviceObject = new JsonParserLogin.DeviceObject();
        JsonParserLogin.RequestObject requestObject = new JsonParserLogin.RequestObject();
        deviceObject.setPlatform("Android");
        deviceObject.setResolution(myApplication.RESOLUATION);
        deviceObject.setVersion(myApplication.APP_VERSION);
        requestObject.setCountryid(Integer.parseInt(myApplication.COUNTERY_ID));
        requestObject.setPassword(Password.getText().toString().trim());
        requestObject.setRegId(GlobalActions.getDataToSharedPrefrences(SharedPrefrenceKeys.REGISTRATION_ID, getActivity().getApplicationContext()));
        requestObject.setUserName(Username.getText().toString().trim());
        jsonParserLogin.setWSPassword("Medl@p$app17");
        jsonParserLogin.setWSUsername("Medlabs");
        jsonParserLogin.setUserId(myApplication.USER_ID);
        jsonParserLogin.setDevice(deviceObject);
        jsonParserLogin.setRequest(requestObject);

        final String request = gson.toJson(jsonParserLogin);

        Progress.show();
        String Url = myApplication.LinkedUserLogin;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    Progress.dismiss();
                    if (response.contains("Success")) {


                        Gson gson = new Gson();
                        Login_Object login_object = new Login_Object();
                        login_object = gson.fromJson(response, Login_Object.class);
                        if (Remember.isChecked()) {
                            myApplication.setIslogged(login_object, jsonParserLogin);
                        } else {
                            myApplication.setSaveToken(login_object);
                        }

                        Fragment fragment = null;
                        Class fragmentClass = null;

                        fragmentClass = Test_Result_Screen.class;
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("login_object", login_object);
                            fragment.setArguments(bundle);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment, "Test Results").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();


                    } else {
                        if (myApplication.GetLanguage().equals("en"))
                            EasyToast.error(getActivity(), "Something Went Wrong!!");
                        else
                            EasyToast.error(getActivity(), " هناك خطأ ما");
                    }
                } catch (Exception Ex) {

                }


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
