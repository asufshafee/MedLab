package com.webmarke8.app.medlab.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class BMI_Calculater extends Fragment {


    EditText Height, Age, Weight;
    TextView BMI, IdealWeight;


    Double HeightDouble, WeightDouble, AgeDouble;

    public BMI_Calculater() {
        // Required empty public constructor
    }

    MyApplication myApplication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_bmi__calculater, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("BMI Calculator");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.BMI_Calculator));

        }

        Height = (EditText) view.findViewById(R.id.Height);
        Age = (EditText) view.findViewById(R.id.Age);
        Weight = (EditText) view.findViewById(R.id.Weight);

        BMI = (TextView) view.findViewById(R.id.BMI);
        IdealWeight = (TextView) view.findViewById(R.id.IdealWeight);

        Height.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (!Weight.getText().toString().equals("")) {

                    if (!Height.getText().toString().equals("")) {
                        HeightDouble = Double.parseDouble(Height.getText().toString());
                        double BMIDouble = ((Double.parseDouble(Weight.getText().toString()) * 703) / (HeightDouble * HeightDouble));
                        DecimalFormat df = new DecimalFormat("#.##");
                        BMIDouble = Double.valueOf(df.format(BMIDouble));
                        BMI.setText(String.valueOf(BMIDouble));
                    }
                }
            }
        });
        Weight.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (!Weight.getText().toString().equals("")) {

                    if (!Height.getText().toString().equals("")) {
                        HeightDouble = Double.parseDouble(Height.getText().toString());
                        double BMIDouble = ((Double.parseDouble(Weight.getText().toString()) * 703) / (HeightDouble * HeightDouble));
                        DecimalFormat df = new DecimalFormat("#.##");
                        BMIDouble = Double.valueOf(df.format(BMIDouble));
                        BMI.setText(String.valueOf(BMIDouble));
                        IdealWeight.setText(centimeterToFeet(Height.getText().toString()));
                    }
                }
            }
        });

        Age.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });


        return view;


    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }


    private static final Double INCH_PER_FEET = 12.0;
    private static final Double POUND_PER_INCH = 1.41;
    private static final Double MIN_HEIGHT_FEET = 5.0;
    private static final Double MIN_WEIGHT_POUND = 56.2;


    private static Double idealBodyWeight(int inches) {
        Double Ideal = MIN_WEIGHT_POUND;


        Ideal += inches * POUND_PER_INCH;

        return Ideal;
    }

    public static String centimeterToFeet(String centemeter) {
        int feetPart = 0;
        int inchesPart = 0;
        if (!TextUtils.isEmpty(centemeter)) {
            double dCentimeter = Double.valueOf(centemeter);
            feetPart = (int) Math.floor((dCentimeter / 2.54) / 12);
            System.out.println((dCentimeter / 2.54) - (feetPart * 12));
            inchesPart = (int) Math.ceil((dCentimeter / 2.54) - (feetPart * 12));
        }
        if (feetPart < 4) {

        } else {
            return String.valueOf(56.2);
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return String.valueOf(idealBodyWeight(inchesPart));
    }
}
