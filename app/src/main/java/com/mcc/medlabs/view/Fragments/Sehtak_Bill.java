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
import com.mcc.medlabs.view.Adapters.Shekha_Adapter;
import com.mcc.medlabs.view.Objects.JsonParserLocation;
import com.mcc.medlabs.view.Objects.Shakha;
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
public class Sehtak_Bill extends Fragment {


    public Sehtak_Bill() {
        // Required empty public constructor
    }

    Dialog Progress;
    RecyclerView recycle;
    java.util.List<Shakha.SahtakBilDeniaObObject> List;
    MyApplication myApplication;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sehtak__bill, container, false);
        myApplication=(MyApplication)getActivity().getApplicationContext();
        ((MainActivity)getActivity()).GetNotifications();

        if (myApplication.GetLanguage().equals("en"))
        ((MainActivity) getActivity()).Change_Tittle("SEHTAK BIL DENIA");
        else ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Sehtak_Bil_Denia));
        ((MainActivity) getActivity()).HideToolbarWithBack();


        recycle = (RecyclerView) view.findViewById(R.id.recycle);
        Progress = AppUtils.LoadingSpinner(getActivity());
        LoadData();

        return view;
    }

    public void LoadData() {
        Progress.dismiss();
        GetTips();

    }

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

        Progress.show();
        String Url = myApplication.GetAllSahtakBilDenia;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Progress.dismiss();
                try
                {
                    if (response.contains("Success")) {


                        Gson gson = new Gson();
                        Shakha shakha = new Shakha();
                        shakha = gson.fromJson(response, Shakha.class);
                        Shakha.SahtakBilDeniaObObject sahtakBilDeniaObObject = new Shakha.SahtakBilDeniaObObject();
                        sahtakBilDeniaObObject.setId("no");
                        sahtakBilDeniaObObject.setProgramName("GIFT VOUCHER");
                        sahtakBilDeniaObObject.setProgramNameAr("قسيمة الهدية");
                        sahtakBilDeniaObObject.setDescription("Sahtak Bil Denia");
                        sahtakBilDeniaObObject.setDescriptionAr("سهتتك بيل دينيا");
                        List = new ArrayList<>();
                        List.addAll(shakha.getSahtakBilDeniaOb());
                        List.add(sahtakBilDeniaObObject);



                        Progress.dismiss();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        Shekha_Adapter Adapter = new Shekha_Adapter(List, getActivity());
                        recycle.setLayoutManager(linearLayoutManager);
                        recycle.setItemAnimator(new DefaultItemAnimator());
                        recycle.setAdapter(Adapter);

                    } else {
                        if (myApplication.GetLanguage().equals("en"))
                            EasyToast.error(getActivity(), "Something Went Wrong!!");
                        else
                            EasyToast.error(getActivity(), " هناك خطأ ما");
                    }

                }catch (Exception a)
                {

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
