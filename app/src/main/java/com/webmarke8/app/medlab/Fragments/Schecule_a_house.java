package com.webmarke8.app.medlab.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Utils.AppUtils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schecule_a_house extends Fragment {


    EditText Name, Phone, City, Area, Street, BuildingNumber, Floor;
    TextView Date, Time;
    RadioButton Jordan, Palstine;
    Dialog Progress;


    public Schecule_a_house() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schecule_a_house, container, false);
//        ((MainActivity) getActivity()).Change_Tittle("Schedule a house call");
        ((MainActivity) getActivity()).ShowBack_toolbar();
        Progress = AppUtils.LoadingSpinner(getActivity());


        Name = (EditText) view.findViewById(R.id.Name);
        Phone = (EditText) view.findViewById(R.id.Phone);
        City = (EditText) view.findViewById(R.id.City);
        Area = (EditText) view.findViewById(R.id.Area);
        Street = (EditText) view.findViewById(R.id.Street);
        BuildingNumber = (EditText) view.findViewById(R.id.BuildingNumber);
        Floor = (EditText) view.findViewById(R.id.FloorNumber);

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
                Progress.show();
            }
        });


        return view;
    }

    public void ShowDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date

// Create the DatePickerDialog instance
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
                datePickerListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        datePicker.setCancelable(true);
        datePicker.setTitle("Date");
        datePicker.show();


    }

    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            Date.setText(day1 + " " + theMonth(Integer.parseInt(month1)) + " " + year1);

        }
    };

    public static String theMonth(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
}
