package com.mcc.medlabs.view.Fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import com.google.gson.Gson;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.JsonParserSchedule;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;
import com.mcc.medlabs.view.Utils.DatePickerDialogWithMaxMinRange;
import com.medialablk.easytoast.EasyToast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schecule_a_house extends Fragment {

    private DatePickerDialogWithMaxMinRange datePickerDialog;


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

    String year, day, mounth;


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
        Date = (TextView) view.findViewById(R.id.Date);
        Time = (TextView) view.findViewById(R.id.Time);

        Calendar myCalendar = Calendar.getInstance();

        int CurrentYear = myCalendar.get(Calendar.YEAR);
        int CurrentMonth = myCalendar.get(Calendar.MONTH);
        int CurrentDay = myCalendar.get(Calendar.DAY_OF_MONTH);
        year = String.valueOf(myCalendar.get(Calendar.YEAR));
        mounth = String.valueOf(myCalendar.get(Calendar.MONTH) + 1);
        day = String.valueOf(myCalendar.get(Calendar.DAY_OF_MONTH));
        Date.setText(String.valueOf(CurrentDay) + " " + theMonth(CurrentMonth) + " " + String.valueOf(CurrentYear));


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

                String msg = null;
                Boolean status = true;
                if (isEmpity(Username) || isEmpity(PhoneNO) || isEmpity(City) || isEmpity(Area) || isEmpity(Street) || isEmpity(BulidinNO) || isEmpity(Floor)) {
                    return;
                }


                if (PhoneNO.getText().toString().length() < 9) {
                    msg = getResources().getString(R.string.invalid_mobile_number);
                    status = false;

                } else {
                    Calendar c = Calendar.getInstance();
                    int current_year = c.get(Calendar.YEAR);
                    int current_mounth = c.get(Calendar.MONTH) + 1; // cause it 0 index
                    int current_day = c.get(Calendar.DAY_OF_MONTH);


                    GregorianCalendar cal = new GregorianCalendar();
                    Boolean isLeap = cal.isLeapYear(Integer.parseInt(year)); // true


                    if (year.trim().equalsIgnoreCase("") || (Integer.parseInt(year) < 1900 || Integer.parseInt(year) < current_year)) {
                        msg = getResources().getString(R.string.PLEASE_ENTER_CORRECT_TIME);
                        status = false;
                    } else if ((mounth.trim().equalsIgnoreCase("") || (Integer.parseInt(mounth) < 1 || Integer.parseInt(mounth) > 12
                            || Integer.parseInt(mounth) < current_mounth)) && Integer.parseInt(year) < current_year) {
//      			msg = getResources().getString(R.string.PLEASE_ENTER_CORRECT_MONTH);
                        msg = getResources().getString(R.string.PLEASE_ENTER_CORRECT_TIME);
                        status = false;
                    } else if ((day.trim().equalsIgnoreCase("") || (Integer.parseInt(day) < 1 ||
                            Integer.parseInt(day) > 31 ||
                            (Integer.parseInt(mounth) == 2 && isLeap && Integer.parseInt(day) > 29) ||
                            (Integer.parseInt(mounth) == 2 && !isLeap && Integer.parseInt(day) > 28) ||
                            (Integer.parseInt(day) < current_day && Integer.parseInt(mounth) <= current_mounth) && Integer.parseInt(year) < current_year)
                    )) {
//      			msg = getResources().getString(R.string.PLEASE_ENTER_CORRECT_DAY);
                        msg = getResources().getString(R.string.PLEASE_ENTER_CORRECT_TIME);
                        status = false;
                    }

                }
                if (!status) {
                    EasyToast.error(getActivity(), msg);
                } else {

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
                            CallHouse();
                            dialog.hide();

                        }
                    });
                    dialog.show();


                }
            }
        });

        return view;
    }

    public void ShowDate() {
        createDateDialog(getActivity(), getActivity());


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
        requestObject.setDateTime(day + "/" + mounth + "/" + year);
        requestObject.setHourTime(timeSpinner.getSelectedItem().toString());
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

    public void createDateDialog(final Context ctx, Activity current) {


        DatePickerDialog.OnDateSetListener datePickerOnDateSetListener;
        Calendar myCalendar;

        datePickerOnDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year1, int monthOfYear1,
                                  int dayOfMonth) {
                year = String.valueOf(year);
                mounth = String.valueOf(monthOfYear1 + 1);
                day = String.valueOf(dayOfMonth);

                Date.setText(dayOfMonth + " " + theMonth(monthOfYear1) + " " + year);
            }

        };

        myCalendar = Calendar.getInstance();

        int CurrentYear = myCalendar.get(Calendar.YEAR);
        int CurrentMonth = myCalendar.get(Calendar.MONTH);
        int CurrentDay = myCalendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(getActivity(), R.style.DialogTheme1, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {
                //DO SOMETHING
                year = String.valueOf(year1);
                mounth = String.valueOf(monthOfYear + 1);
                day = String.valueOf(dayOfMonth);
                Date.setText(dayOfMonth + " " + theMonth(monthOfYear) + " " + year);
            }
        }, CurrentYear, CurrentMonth, CurrentDay).show();


        int MaxYear = CurrentYear + 10;
        int MaxMonth = 11;
        int MaxDay = 31;

        int MinYear = CurrentYear;
        int MinMonth = CurrentMonth;
        int MinDay = CurrentDay;

        if (datePickerDialog == null) {
            datePickerDialog = new DatePickerDialogWithMaxMinRange(ctx, datePickerOnDateSetListener,
                    MinYear, MinMonth, MinDay, MaxYear, MaxMonth, MaxDay);
        }
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    // Do Stuff
                }
            }
        });
//        datePickerDialog.show();


    }
}
