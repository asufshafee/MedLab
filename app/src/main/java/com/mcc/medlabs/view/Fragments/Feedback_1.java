package com.mcc.medlabs.view.Fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

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
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;
import com.medialablk.easytoast.EasyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback_1 extends Fragment {


    private com.hedgehog.ratingbar.RatingBar ratingBar1, ratingBar2, ratingBar3, ratingBar4, ratingBar5, ratingBar6, ratingBar7, ratingBar8, ratingBar9;
    Dialog Progress;
    com.hedgehog.ratingbar.RatingBar RatingBar1;

    float A1, A2, A3, A4, A5, A6, A7, A8, A9;

    public Feedback_1() {
        // Required empty public constructor
    }

    MyApplication myApplication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback_1, container, false);
        ((MainActivity) getActivity()).HideShare_toolbar();
        myApplication = (MyApplication) getActivity().getApplicationContext();
        Progress = AppUtils.LoadingSpinner(getActivity());
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("Feedback");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Feedback));

        }
        ((MainActivity) getActivity()).ShowBack_toolbar();


        RatingBar1 = (com.hedgehog.ratingbar.RatingBar) view.findViewById(R.id.ratingbar);
        ratingBar2 = (com.hedgehog.ratingbar.RatingBar) view.findViewById(R.id.RatingBar2);
        ratingBar3 = (com.hedgehog.ratingbar.RatingBar) view.findViewById(R.id.RatingBar3);
        ratingBar4 = (com.hedgehog.ratingbar.RatingBar) view.findViewById(R.id.RatingBar4);
        ratingBar5 = (com.hedgehog.ratingbar.RatingBar) view.findViewById(R.id.RatingBar5);
        ratingBar6 = (com.hedgehog.ratingbar.RatingBar) view.findViewById(R.id.RatingBar6);
        ratingBar7 = (com.hedgehog.ratingbar.RatingBar) view.findViewById(R.id.RatingBar7);
        ratingBar8 = (com.hedgehog.ratingbar.RatingBar) view.findViewById(R.id.RatingBar8);
        ratingBar9 = (com.hedgehog.ratingbar.RatingBar) view.findViewById(R.id.RatingBar9);
        RatingBar1.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {

                A1 = RatingCount;
            }
        });
        ratingBar2.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                A2 = RatingCount;
            }
        });
        ratingBar3.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                A3 = RatingCount;
            }
        });
        ratingBar4.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                A4 = RatingCount;
            }
        });
        ratingBar5.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                A5 = RatingCount;
            }
        });
        ratingBar6.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                A6 = RatingCount;
            }
        });
        ratingBar7.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                A7 = RatingCount;
            }
        });
        ratingBar8.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                A8 = RatingCount;
            }
        });
        ratingBar9.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                A9 = RatingCount;
            }
        });

        view.findViewById(R.id.Next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((A1 > 0) && (A2 > 0) && (A3 > 0) &&
                        (A4 > 0) && (A5 > 0) && (A6 > 0) &&
                        (A7 > 0) && (A8 > 0) && (A9 > 0)) {

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
                } else {
                    EasyToast.info(getActivity(), getString(R.string.PLEASE_FILL_MANDTORY_Raiting));
                }


            }
        });
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

    private String prepareJsonRequest() throws JSONException {

        JSONObject request = new JSONObject();
        JSONArray ArrQu = new JSONArray();

        request.put("LabId", "0");
        request.put("LabName", "Dummy");

        JSONObject JQ1 = new JSONObject();
        JQ1.put("QuestionId", "1");
        JQ1.put("RatingValue", A1);
        ArrQu.put(0, JQ1);

        JSONObject JQ2 = new JSONObject();
        JQ2.put("QuestionId", "2");
        JQ2.put("RatingValue", A2);
        ArrQu.put(1, JQ2);


        JSONObject JQ3 = new JSONObject();
        JQ3.put("QuestionId", "3");
        JQ3.put("RatingValue", A3);
        ArrQu.put(2, JQ3);

        JSONObject JQ4 = new JSONObject();
        JQ4.put("QuestionId", "4");
        JQ4.put("RatingValue", A4);
        ArrQu.put(3, JQ4);


        JSONObject JQ5 = new JSONObject();
        JQ5.put("QuestionId", "5");
        JQ5.put("RatingValue", A5);
        ArrQu.put(4, JQ5);


        JSONObject JQ6 = new JSONObject();
        JQ6.put("QuestionId", "6");
        JQ6.put("RatingValue", A6);
        ArrQu.put(5, JQ6);

        JSONObject JQ7 = new JSONObject();
        JQ7.put("QuestionId", "7");
        JQ7.put("RatingValue", A7);
        ArrQu.put(6, JQ7);


        JSONObject JQ8 = new JSONObject();
        JQ8.put("QuestionId", "8");
        JQ8.put("RatingValue", A8);
        ArrQu.put(7, JQ8);


        JSONObject JQ9 = new JSONObject();
        JQ9.put("QuestionId", "9");
        JQ9.put("RatingValue", A9);
        ArrQu.put(8, JQ9);

        request.put("RatingQues", ArrQu);


        return request.toString().replace("-1", "0");
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
            String Url = myApplication.FeedbackRating;
            final String finalRequest = request;
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    try {

                        Progress.dismiss();
                        Fragment fragment = null;
                        Class fragmentClass = null;

                        fragmentClass = Feedback_2.class;
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                            Bundle bundle = new Bundle();
                            fragment.setArguments(bundle);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment, "Feedback").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

                    } catch (Exception Ex) {

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
}
