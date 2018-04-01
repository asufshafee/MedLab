package com.mcc.medlabs.view.Utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import com.mcc.medlabs.view.Session.MedlabsConstants;

/**
 * Created by GeeksEra on 4/1/2018.
 */

public class DatePickerDialogWithMaxMinRange extends DatePickerDialog {

    static int maxYear=2200;
    static int maxMonth=11;
    static int maxDay=31;

    int minYear=2013;
    int minMonth=0;
    int minDay=1;


    public DatePickerDialogWithMaxMinRange(Context context, OnDateSetListener callBack, int minYear, int minMonth, int minDay, int maxYear, int maxMonth, int maxDay) {
        super(context,callBack, minYear, minMonth, minDay);
        this.minDay = minDay;
        this.minMonth = minMonth;
        this.minYear = minYear;
        DatePickerDialogWithMaxMinRange.maxDay = maxDay;
        DatePickerDialogWithMaxMinRange.maxMonth = maxMonth;
        DatePickerDialogWithMaxMinRange.maxYear = maxYear;

    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        super.onDateChanged(view, year, monthOfYear, dayOfMonth);

        Log.d(MedlabsConstants.TAG , "onDateChanged validation !!!!");



        Log.e(MedlabsConstants.TAG , "Max Year : " +maxYear + " Max Month : " +maxMonth + " Max Day : " +maxDay);


        Log.e(MedlabsConstants.TAG , "Min Year : " +minYear + " Min Month : " +minMonth + " Min Day : " +minDay);


        Log.e(MedlabsConstants.TAG , "Year : " +year + " Month : " +monthOfYear + " Day : " +dayOfMonth);




        if (year > maxYear ||monthOfYear > maxMonth && year == maxYear||
                dayOfMonth > maxDay && year == maxYear && monthOfYear == maxMonth){
            Log.d(MedlabsConstants.TAG , "first if .... ");

            view.updateDate(maxYear, maxMonth, maxDay);

        }else if(year < minYear ||monthOfYear < minMonth && year == minYear||
                dayOfMonth < minDay && year == minYear && monthOfYear == minMonth){
            Log.d(MedlabsConstants.TAG , "second if .... ");
            view.updateDate(minYear, minMonth, minDay );
        }
    }



}