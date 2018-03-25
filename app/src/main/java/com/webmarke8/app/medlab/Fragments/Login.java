package com.webmarke8.app.medlab.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.medialablk.easytoast.EasyToast;
import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.GlobalActions;
import com.webmarke8.app.medlab.Session.MyApplication;
import com.webmarke8.app.medlab.Session.SharedPrefrenceKeys;

import org.json.JSONObject;

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
import com.webmarke8.app.medlab.Utils.AppUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    MyApplication myApplication;
    EditText Username, Password;
    Dialog Progress;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((MainActivity) getActivity()).Change_Tittle("MedLabs");
        ((MainActivity) getActivity()).HideToolbarWithBack();
        myApplication = (MyApplication) getActivity().getApplicationContext();
        Progress = AppUtils.LoadingSpinner(getActivity());

        Username = (EditText) view.findViewById(R.id.Username);
        Password = (EditText) view.findViewById(R.id.Password);

        view.findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(prepareLoginJsonRequest());
            }
        });

        return view;
    }

    private String prepareLoginJsonRequest() {

        String request = "";
        request += "{";
        request += "\"device\":{";
        request += "\"Platform\":" + "\"Android\"" + ",";
        request += "\"Resolution\":" + "\"" + myApplication.RESOLUATION + "\"" + ",";
        request += "\"Version\":" + "\"" + myApplication.APP_VERSION + "\"";
        request += "},";
        request += "\"request\":{";
        request += "\"Countryid\":" + "\"" + myApplication.COUNTERY_ID + "\"" + ",";
        request += "\"Password\":" + "\"" + Username.getText().toString().trim() + "\"" + ",";
        request += "\"RegId\":" + "\"" + GlobalActions.getDataToSharedPrefrences(SharedPrefrenceKeys.REGISTRATION_ID, getActivity().getApplicationContext()) + "\"" + ",";
        request += "\"UserName\":" + "\"" + Password.getText().toString().trim() + "\"";
        request += "},";

        request += "\"userId\":" + "\"" + myApplication.USER_ID + "\"";
        request += "}";
        return request;
    }

    private void Login(final String JsonRequest) {


        String Url = myApplication.LinkedUserLogin;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Progress.dismiss();
                if (response.contains("success")) {

//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        GetRiderResponse(String.valueOf(jsonObject.getInt("success")));
//                        myApplication.ClearCart();
//                        ((MainActivity) getActivity()).Bandge(0);
//                    } catch (Exception Ex) {
//                        EasyToast.error(getActivity(), "Something Went Wrong!!");
//
//                    }


                } else {
                    EasyToast.error(getActivity(), "Something Went Wrong!! Quantity issus");
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Progress.dismiss();
                        EasyToast.error(getActivity(), "Something Went Wrong!!");
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
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return JsonRequest == null ? null : JsonRequest.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", JsonRequest, "utf-8");
                    return null;
                }
            }


            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
