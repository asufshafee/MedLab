package com.webmarke8.app.medlab.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
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
        View view = inflater.inflate(R.layout.fragment_bmi__calculater, container, false);
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

                if (Weight.getText().toString().equals("")) {

                    if (!s.equals("")) {
                        HeightDouble = Double.parseDouble(Height.getText().toString());
                        HeightDouble = HeightDouble / 100;
                        double BMIDouble = Double.parseDouble(s.toString()) / (HeightDouble * HeightDouble);
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

                if (Height.getText().toString().equals("")) {

                    if (!s.equals("")) {
                        HeightDouble = Double.parseDouble(Height.getText().toString());
                        HeightDouble = HeightDouble / 100;
                        double BMIDouble = Double.parseDouble(s.toString()) / (HeightDouble * HeightDouble);
                        BMI.setText(String.valueOf(round(BMIDouble, 1)));
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
}
