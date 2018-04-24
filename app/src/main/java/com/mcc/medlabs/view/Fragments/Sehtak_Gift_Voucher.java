package com.mcc.medlabs.view.Fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.medialablk.easytoast.EasyToast;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MedlabsConstants;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sehtak_Gift_Voucher extends Fragment {

    int testId = 1, paymentMethodId = 1;
    EditText editTextSenderName, editTextSenderPhone, editTextSenderAddress,
            editTextRecipientName, editTextRecipientPhone, editTextRecipientAdderss;
    Spinner spinnerTestId;
    RadioButton Cash, Management;
    Dialog Progress;

    public Sehtak_Gift_Voucher() {
        // Required empty public constructor
    }

    MyApplication myApplication;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sehtak__gift__voucher, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        Progress = AppUtils.LoadingSpinner(getActivity());
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("Gift Voucher");
        else ((MainActivity) getActivity()).Change_Tittle("قسيمة الهدية");
        ((MainActivity) getActivity()).ShowBack_toolbar();
        Cash = (RadioButton) view.findViewById(R.id.Cash);

        Cash.setChecked(true);


        editTextSenderName = (EditText) view.findViewById(R.id.SenderName);
        editTextSenderPhone = (EditText) view.findViewById(R.id.SenderPhone);
        editTextSenderAddress = (EditText) view.findViewById(R.id.SenderAddress);

        editTextRecipientName = (EditText) view.findViewById(R.id.Rec_Name);
        editTextRecipientPhone = (EditText) view.findViewById(R.id.Rec_Phone);
        editTextRecipientAdderss = (EditText) view.findViewById(R.id.Rec_Address);

        spinnerTestId = (Spinner) view.findViewById(R.id.Spinner);


        spinnerTestId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                testId = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view.findViewById(R.id.Submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpity(editTextSenderName) || isEmpity(editTextSenderPhone) || isEmpity(editTextSenderAddress) || isEmpity(editTextRecipientName
                ) || isEmpity(editTextRecipientPhone) || isEmpity(editTextRecipientAdderss)) {
                    return;
                }



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
                        Voucher();
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

    private String prepareJsonRequest() {

        if (Cash.isChecked()) {
            paymentMethodId = 1;
        } else {
            paymentMethodId = 2;

        }
        String request = "";

        request += "{";
        request += "\"device\":{";
        request += "\"Platform\":" + "\"Android\"" + ",";
        request += "\"Resolution\":" + "\"" + MedlabsConstants.RESOLUATION + "\"" + ",";
        request += "\"Version\":" + "\"" + MedlabsConstants.APP_VERSION + "\"";
        request += "},";

        request += "\"request\":{";
        request += "\"PaymentMethodId\":" + "\"" + paymentMethodId + "\"" + ",";
        request += "\"RecipientAddress\":" + "\"" + editTextRecipientAdderss.getText().toString() + "\"" + ",";
        request += "\"RecipientName\":" + "\"" + editTextRecipientName.getText().toString() + "\"" + ",";
        request += "\"RecipientPhoneNumber\":" + "\"" + editTextRecipientPhone.getText().toString() + "\"" + ",";
        request += "\"SenderAddress\":" + "\"" + editTextSenderAddress.getText().toString() + "\"" + ",";
        request += "\"SenderName\":" + "\"" + editTextSenderName.getText().toString() + "\"" + ",";
        request += "\"SenderPhoneNumber\":" + "\"" + editTextSenderPhone.getText().toString() + "\"" + ",";
        request += "\"TestId\":" + "\"" + testId + "\"";
        request += "},";
        request += "\"userId\":" + "\"" + MedlabsConstants.USER_ID + "\"" + ",";
        request += "\"WSPassword\":" + "\"" + "Medl@p$app17" + "\"" + ",";
        request += "\"WSUsername\":" + "\"" + "Medlabs" + "\"";
        request += "}";
        return request;
    }


    private void Voucher() {

        final String request = prepareJsonRequest();

        Progress.show();
        String Url = myApplication.RequestGiftVoucher;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                EasyToast.success(getActivity(), getString(R.string.confirm_gift));
                Progress.dismiss();
                setEmpity(editTextSenderName);
                setEmpity(editTextSenderAddress);
                setEmpity(editTextSenderPhone);

                setEmpity(editTextRecipientPhone);
                setEmpity(editTextRecipientAdderss);
                setEmpity(editTextRecipientName);


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

    public Boolean isEmpity(TextView view) {

        if (view.getText().toString().equals("")) {
            if (myApplication.GetLanguage().equals("en"))
                view.setError("Fill This Field");
            else view.setError("املأ هذا الحقل");
            return true;
        }
        return false;
    }

    public void setEmpity(TextView view) {
        view.setText("");
    }

}
