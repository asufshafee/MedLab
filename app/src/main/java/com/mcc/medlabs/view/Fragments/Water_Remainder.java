package com.mcc.medlabs.view.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class Water_Remainder extends Fragment {


    TextView Text, TextHeading;
    int Glass = 0;
    MyApplication myApplication;

    public Water_Remainder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_water__remainder, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("Water Remainder");
        else {
            String Test = getString(R.string.water_remeinder);

            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.water_remeinder));
        }

        ((MainActivity) getActivity()).ShowBack_toolbar();
        Glass = MyApplication.getGLASS();


        Text = (TextView) view.findViewById(R.id.Text);
        TextHeading = (TextView) view.findViewById(R.id.TextHeading);

        if (myApplication.GetLanguage().equals("en")) {
            TextHeading.setText("You are doing great, " + String.valueOf(Glass) + " more \n cup to reach daily \n intake water");
            Text.setText(String.valueOf(Glass) + " of 8 glasses");
        } else {
            TextHeading.setText("انت تبلي بلاء رائعا،" + String.valueOf(Glass) + " أكثر من \n كوب للوصول إلى اليومية \n كمية الماء");

            Text.setText(String.valueOf(Glass) + " من 8 أكواب");
        }


        view.findViewById(R.id.Decrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Glass != 0) {
                    Glass--;
                    MyApplication.setGLASS(Glass);
                }
                if (myApplication.GetLanguage().equals("en")) {
                    TextHeading.setText("You are doing great, " + String.valueOf(Glass) + " more \n cup to reach daily \n intake water");
                    Text.setText(String.valueOf(Glass) + " of 8 glasses");
                } else {
                    TextHeading.setText("انت تبلي بلاء رائعا،" + String.valueOf(Glass) + " أكثر من \n كوب للوصول إلى اليومية \n كمية الماء");

                    Text.setText(String.valueOf(Glass) + " من 8 أكواب");
                }

            }
        });
        view.findViewById(R.id.Increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Glass != 8) {
                    Glass++;
                    MyApplication.setGLASS(Glass);

                }
                if (myApplication.GetLanguage().equals("en")) {
                    TextHeading.setText("You are doing great, " + String.valueOf(Glass) + " more \n cup to reach daily \n intake water");
                    Text.setText(String.valueOf(Glass) + " of 8 glasses");
                } else {
                    TextHeading.setText("انت تبلي بلاء رائعا،" + String.valueOf(Glass) + " أكثر من \n كوب للوصول إلى اليومية \n كمية الماء");

                    Text.setText(String.valueOf(Glass) + " من 8 أكواب");
                }

            }
        });

        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

    @Override
    public void onDestroy() {
        ((MainActivity) getActivity()).HideShare_toolbar();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("Manage My Health");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Manage_My_Helth));

        }
        ((MainActivity) getActivity()).ShowBack_toolbar();
        super.onDestroy();
    }

}
