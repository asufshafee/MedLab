package com.mcc.medlabs.view.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class About_Us extends Fragment {


    public About_Us() {
        // Required empty public constructor
    }
    MyApplication myApplication;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about__us, container, false);
        myApplication=(MyApplication)getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en"))
        ((MainActivity) getActivity()).Change_Tittle("About Us");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.About));

        }
        ((MainActivity) getActivity()).ShowBack_toolbar();
        return view;


    }
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

}
