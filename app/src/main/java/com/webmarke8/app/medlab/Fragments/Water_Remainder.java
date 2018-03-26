package com.webmarke8.app.medlab.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.R;

import javax.microedition.khronos.opengles.GL;

/**
 * A simple {@link Fragment} subclass.
 */
public class Water_Remainder extends Fragment {


    TextView Text, TextHeading;
    int Glass = 0;

    public Water_Remainder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_water__remainder, container, false);
        ((MainActivity) getActivity()).Change_Tittle("Water Remainder");
        ((MainActivity) getActivity()).ShowBack_toolbar();
        ((MainActivity) getActivity()).ShowShare_toolbar();

        Text = (TextView) view.findViewById(R.id.Text);
        TextHeading = (TextView) view.findViewById(R.id.TextHeading);
        TextHeading.setText("You are doing great, " + String.valueOf(Glass) + " more \n cup to reach daily \n intake water");
        Text.setText(String.valueOf(Glass) + " of 8 glasses");

        view.findViewById(R.id.Decrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Glass != 0) {
                    Glass--;
                }
                TextHeading.setText("You are doing great, " + String.valueOf(Glass) + " more \n cup to reach daily \n intake water");
                Text.setText(String.valueOf(Glass) + " of 8 glasses");
            }
        });
        view.findViewById(R.id.Increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Glass != 8) {
                    Glass++;
                }
                TextHeading.setText("You are doing great, " + String.valueOf(Glass) + " more \n cup to reach daily \n intake water");
                Text.setText(String.valueOf(Glass) + " of 8 glasses");
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
        super.onDestroy();
    }

}
