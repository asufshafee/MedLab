package com.mcc.medlabs.view.Fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;

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
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.medialablk.easytoast.EasyToast;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MedlabsConstants;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback_2 extends Fragment {


    public Feedback_2() {
        // Required empty public constructor
    }

    Dialog Progress;
    EditText Name, Email, Phone, Comment;


    MyApplication myApplication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback_2, container, false);
        Progress = AppUtils.LoadingSpinner(getActivity());
        myApplication = (MyApplication) getActivity().getApplicationContext();
        Name = (EditText) view.findViewById(R.id.Name);
        Email = (EditText) view.findViewById(R.id.Email);
        Phone = (EditText) view.findViewById(R.id.Phone);
        Comment = (EditText) view.findViewById(R.id.Comment);

        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("Feedback");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Feedback));
        }
        ((MainActivity) getActivity()).ShowBack_toolbar();

        view.findViewById(R.id.Submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpity(Name) || isEmpity(Phone) || isEmpity(Email) || isEmpity(Comment))
                    return;
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_box_conformation);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.findViewById(R.id.Cross).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();


                    }
                });

                dialog.findViewById(R.id.Change).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Progress.show();
                        FeedBack();
                        dialog.hide();

                    }
                });
                dialog.show();
            }
        });
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

    private void FeedBack() {
        {

            String request = null;
            try {
                request = prepareJsonRequest();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Progress.show();
            String Url = myApplication.FeedbackUserInformation;
            final String finalRequest = request;
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Progress.dismiss();
                    if (myApplication.GetLanguage().equals("en")) {
                        EasyToast.success(getActivity(), "Thanks for you Feedback");

                    } else {
                        EasyToast.success(getActivity(), "شكرا لك ردود الفعل");

                    }
                    ((MainActivity) getActivity()).getSupportFragmentManager().popBackStack();
                    ((MainActivity) getActivity()).getSupportFragmentManager().popBackStack();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Progress.dismiss();
                            if (myApplication.GetLanguage().equals("en"))
                                EasyToast.error(getActivity(), "Something Went Wrong!!");
                            else
                                EasyToast.error(getActivity(), " هناك خطأ ما");
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
                        return finalRequest == null ? null : finalRequest.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", finalRequest, "utf-8");
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

    private String prepareJsonRequest() throws JSONException {


        JSONObject request = new JSONObject();
        request.put("Comment", Comment.getText().toString());
        request.put("Email", Email.getText().toString());
        request.put("FBId", "0");
        request.put("LabId", "0");
        request.put("Mobile", Phone.getText().toString());
        request.put("UserName", Name.getText().toString());

        Log.v(MedlabsConstants.TAG, "!RE : " + request.toString());

        return request.toString();
    }

    interface AllWebServicesRequest {
        String LDR = "LDR";
        String LAST_ID = "LastId";
        String PACKGE_NO = "Pkgno";
        String DEVICE = "device";
        String PLATFORM = "Platform";
        String RESOLUATION = "Resolution";
        String VERSION = "Version";
        String USER_ID = "userId";
        String USERNAME = "Username";
        String Password = "Password";


        String MSISDN = "MSISDN";
        String OperatorId = "OperatorId";
        String PageIndex = "PageIndex";
        String Keyword = "Keyword";
        String PageSize = "PageSize";
        String SubStatus = "SubStatus";
        String Username = "Username";
    }

    public Boolean isEmpity(EditText view) {

        if (view.getText().toString().equals("")) {
            if (myApplication.GetLanguage().equals("en"))
                view.setError("Fill This Field");
            else view.setError("املأ هذا الحقل");
            return true;
        }
        return false;
    }
}
