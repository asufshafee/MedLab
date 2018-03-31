package com.webmarke8.app.medlab.Fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.Objects.JsonParserLocation;
import com.webmarke8.app.medlab.Objects.JsonParserLogin;
import com.webmarke8.app.medlab.Objects.JsonParserSchedule;
import com.webmarke8.app.medlab.Objects.Login_Object;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.GlobalActions;
import com.webmarke8.app.medlab.Session.MyApplication;
import com.webmarke8.app.medlab.Session.SharedPrefrenceKeys;
import com.webmarke8.app.medlab.Utils.AppUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schecule_a_house extends Fragment {


    TextView Date, Time;
    RadioButton Jordan, Palstine;
    Dialog Progress;
    MyApplication myApplication;
    private Spinner timeSpinner;
    View viewMian;
    EditText City;
    EditText Area;
    EditText Street;
    EditText Floor;
    EditText PhoneNO;
    EditText Username;
    EditText BulidinNO;


    private String time[] = {"8", "9", "10", "11", "12", "1", "2", "3", "4"};
    private int mTime[] = {8, 9, 10, 11, 12, 13, 14, 15, 16};

    public Schecule_a_house() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schecule_a_house, container, false);
        viewMian = view;
//        ((MainActivity) getActivity()).Change_Tittle("Schedule a house call");
        ((MainActivity) getActivity()).ShowBack_toolbar();
        Progress = AppUtils.LoadingSpinner(getActivity());
        myApplication = (MyApplication) getActivity().getApplicationContext();


        City = (EditText) viewMian.findViewById(R.id.City);
        Area = (EditText) viewMian.findViewById(R.id.Area);
        Street = (EditText) viewMian.findViewById(R.id.Street);
        Floor = (EditText) viewMian.findViewById(R.id.FloorNumber);
        PhoneNO = (EditText) viewMian.findViewById(R.id.Phone);
        Username = (EditText) viewMian.findViewById(R.id.Username);
        BulidinNO = (EditText) viewMian.findViewById(R.id.BuildingNumber);


        timeSpinner = (Spinner) view.findViewById(R.id.TimeSpiner);

        ArrayList<String> timeData = new ArrayList<String>();
        if (myApplication.GetLanguage().equals("en")) {
            timeData.add(time[0] + " AM");
            timeData.add(time[1] + " AM");
            timeData.add(time[2] + " AM");
            timeData.add(time[3] + " AM");
            timeData.add(time[4] + " PM");
            timeData.add(time[5] + " PM");
            timeData.add(time[6] + " PM");
            timeData.add(time[7] + " PM");
            timeData.add(time[8] + " PM");
        } else {
            timeData.add(time[0] + " ص");
            timeData.add(time[1] + " ص");
            timeData.add(time[2] + " ص");
            timeData.add(time[3] + " ص");
            timeData.add(time[4] + " م");
            timeData.add(time[5] + " م");
            timeData.add(time[6] + " م");
            timeData.add(time[7] + " م");
            timeData.add(time[8] + " م");
        }

        SpinnerAdapter dataAdapter = new SpinnerAdapter(getActivity(), timeData);
        dataAdapter.setDropDownViewResource(R.layout.item_spinner);
        timeSpinner.setAdapter(dataAdapter);


        Date = (TextView) view.findViewById(R.id.Date);
        Time = (TextView) view.findViewById(R.id.Time);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDate();
            }
        });

        Jordan = (RadioButton) view.findViewById(R.id.Jordan);
        Palstine = (RadioButton) view.findViewById(R.id.Palstine);
        Jordan.setChecked(true);

        view.findViewById(R.id.Submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpity(City) || isEmpity(Area) || isEmpity(Street) || isEmpity(Floor) || isEmpity(PhoneNO) || isEmpity(Username) || isEmpity(BulidinNO)) {
                    return;
                }

                Progress.show();
                CallHouse();
            }
        });


        return view;
    }

    public void ShowDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date

        DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
                datePickerListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        datePicker.setCancelable(true);
        datePicker.setTitle("Date");
        datePicker.show();


    }

    public class SpinnerAdapter extends ArrayAdapter<String> {

        private final Context context;
        private final ArrayList<String> values;

        public SpinnerAdapter(Context context, ArrayList<String> values) {
            super(context, R.layout.item_spinner, values);

            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.item_spinner, parent, false);
            TextView text = (TextView) rowView.findViewById(R.id.spinner_item_id);

            text.setText(values.get(position));
            return rowView;
        }

    }

    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth);
            String day1 = String.valueOf(selectedDay);
            Date.setText(day1 + " " + theMonth(Integer.parseInt(month1)) + " " + year1);

        }
    };

    public String theMonth(int month) {
        if (myApplication.GetLanguage().equals("en")) {
            String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            return monthNames[month];
        } else {
            String[] monthNamesArabic = {"كانون الثاني", "شهر فبراير", "مارس", "أبريل", "قد", "يونيو", "يوليو", "أغسطس", "سبتمبر", "شهر اكتوبر", "شهر نوفمبر", "ديسمبر"};
            return monthNamesArabic[month];
        }


    }


    private void CallHouse() {


        RadioButton Jordan, Phalastine;
        Jordan = (RadioButton) viewMian.findViewById(R.id.Jordan);


        JsonParserSchedule jsonParserSchedule = new JsonParserSchedule();
        JsonParserSchedule.DeviceObject deviceObject = new JsonParserSchedule.DeviceObject();
        deviceObject.setPlatform("Android");
        deviceObject.setResolution(myApplication.RESOLUATION);
        deviceObject.setVersion(myApplication.APP_VERSION);
        jsonParserSchedule.setWSPassword("Medl@p$app17");
        jsonParserSchedule.setWSUsername("Medlabs");
        jsonParserSchedule.setDevice(deviceObject);
        jsonParserSchedule.setUserId("");
        JsonParserSchedule.RequestObject requestObject = new JsonParserSchedule.RequestObject();
        requestObject.setArea(Area.getText().toString());
        requestObject.setBuildingNo(BulidinNO.getText().toString());
        requestObject.setCity(City.getText().toString());
        if (Jordan.isChecked())
            requestObject.setCountryid(1);
        else requestObject.setCountryid(2);
        requestObject.setFloorNo(Floor.getText().toString());
        requestObject.setDateTime("");
        requestObject.setHourTime("");
        requestObject.setName(Username.getText().toString());
        requestObject.setStreetName(Street.getText().toString());
        requestObject.setPhoneNumber(PhoneNO.getText().toString());
        jsonParserSchedule.setRequest(requestObject);


        Gson gson = new Gson();
        final String request = gson.toJson(jsonParserSchedule);
        Log.d("", request);

        Progress.show();
        String Url = myApplication.ScheduleHouseCallRequest;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Progress.dismiss();
                if (response.contains("Success")) {

                    setEmpity(City);
                    setEmpity(Area);
                    setEmpity(Street);
                    setEmpity(Floor);
                    setEmpity(PhoneNO);
                    setEmpity(Username);
                    setEmpity(BulidinNO);
                    Fragment fragment = null;
                    Class fragmentClass = null;

//                    fragmentClass = Code_Verify.class;
//                    try {
//                        fragment = (Fragment) fragmentClass.newInstance();
//                        Bundle bundle = new Bundle();
//                        fragment.setArguments(bundle);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.container, fragment, "MedLabs").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
//

                    if (myApplication.GetLanguage().equals("en"))
                        EasyToast.success(getActivity(), "Thanks For Request We Will Contact you Soon");
                    else
                        EasyToast.success(getActivity(), "شكرا للطلب سنقوم بالاتصال بك قريبا");


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
