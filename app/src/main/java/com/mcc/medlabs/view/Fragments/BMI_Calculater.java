package com.mcc.medlabs.view.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.DecimalUtils;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class BMI_Calculater extends Fragment {


    EditText Height, Age, Weight;
    TextView BMI, IdealWeight;

    LinearLayout Me1, Me2, Me3, Me4, Me5, Me6, Me7;

    public BMI_Calculater() {
        // Required empty public constructor
    }

    MyApplication myApplication;
    Animation startAnimation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_bmi__calculater, container, false);

        Me1 = (LinearLayout) view.findViewById(R.id.Me1);
        Me2 = (LinearLayout) view.findViewById(R.id.Me2);
        Me3 = (LinearLayout) view.findViewById(R.id.Me3);
        Me4 = (LinearLayout) view.findViewById(R.id.Me4);
        Me5 = (LinearLayout) view.findViewById(R.id.Me5);
        Me6 = (LinearLayout) view.findViewById(R.id.Me6);
        Me7 = (LinearLayout) view.findViewById(R.id.Me7);

        startAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);


        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("BMI Calculator");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.BMI_Calculator));

        }
        ((MainActivity) getActivity()).ShowBack_toolbar();


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
                        centimeterToFeet(Height.getText().toString(), BMI);
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
//                        HeightDouble = Double.parseDouble(Height.getText().toString());
//                        double BMIDouble = ((Double.parseDouble(Weight.getText().toString()) * 703) / (HeightDouble * HeightDouble));
//                        DecimalFormat df = new DecimalFormat("#.##");
//                        BMIDouble = Double.valueOf(df.format(BMIDouble));
//                        BMI.setText(String.valueOf(BMIDouble));
                        centimeterToFeet(Height.getText().toString(), BMI);
//                        IdealWeight.setText(centimeterToFeet(Height.getText().toString()));
//
//                        double bmi = (Double.parseDouble(Weight.getText().toString() / Math.pow(heightInMeters, 2.0)));
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

    public void centimeterToFeet(String centemeter, TextView BMI) {
        int feetPart = 0;
        int inchesPart = 0;
        if (!TextUtils.isEmpty(centemeter)) {
            double dCentimeter = Double.valueOf(centemeter);
            feetPart = (int) Math.floor((dCentimeter / 2.54) / 12);
            System.out.println((dCentimeter / 2.54) - (feetPart * 12));
            inchesPart = (int) Math.ceil((dCentimeter / 2.54) - (feetPart * 12));
        }
        double heightInMeters = (((feetPart * 12) + inchesPart) * .0254);
        double bmi = (Double.parseDouble(Weight.getText().toString()) / Math.pow(heightInMeters, 2.0));
        DecimalFormat df = new DecimalFormat("#.##");
        bmi = DecimalUtils.round(bmi, 1);
        BMI.setText(String.valueOf(bmi));

        if (inchesPart > 5)
            IdealWeight.setText(String.valueOf(DecimalUtils.round(idealBodyWeight(inchesPart), 1)) + " kg");
        else IdealWeight.setText("56.2 kg");


        Me1.clearAnimation();
        Me2.clearAnimation();
        Me3.clearAnimation();
        Me4.clearAnimation();
        Me5.clearAnimation();
        Me6.clearAnimation();
        Me7.clearAnimation();

        if (bmi < 16) {
            Me1.startAnimation(startAnimation);
        } else if (bmi >= 16 && bmi < 17) {
            Me2.startAnimation(startAnimation);
        } else if (bmi >= 17 && bmi < 18.5) {
            Me3.startAnimation(startAnimation);
        } else if (bmi >= 18.5 && bmi < 25) {
            Me4.startAnimation(startAnimation);
        } else if (bmi >= 25 && bmi < 30) {
            Me5.startAnimation(startAnimation);
        } else if (bmi >= 30 && bmi < 35) {
            Me6.startAnimation(startAnimation);
        } else if (bmi >= 35 && bmi < 40) {
            Me7.startAnimation(startAnimation);
        }

//      Do I need this last else if there?
//      else {
//          System.out.print("");
//      }


    }

    @Override
    public void onDestroy() {
        ((MainActivity) getActivity()).HideShare_toolbar();
        if (((MainActivity) getActivity()).getSupportFragmentManager().getBackStackEntryCount() != 1)
            if (myApplication.GetLanguage().equals("en"))
                ((MainActivity) getActivity()).Change_Tittle("Manage My Health");
            else {
                ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Manage_My_Helth));

            }
        super.onDestroy();
    }

}
