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
import com.webmarke8.app.medlab.Session.MyApplication;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class Time_Used extends Fragment {


    public Time_Used() {
        // Required empty public constructor
    }

    MyApplication myApplication;
    TextView Hours,Yesterday,TimeHeading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time__used, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();

        if (myApplication.GetLanguage().equals("ar")) {
            ((MainActivity) getActivity()).Change_Tittle("الوقت المستخدم");
        } else {
            ((MainActivity) getActivity()).Change_Tittle("Time Used");
        }


        ((MainActivity) getActivity()).ShowBack_toolbar();
        ((MainActivity) getActivity()).ShowShare_toolbar();

        Hours=(TextView)view.findViewById(R.id.Hours);
        TimeHeading=(TextView)view.findViewById(R.id.TextHeading);
        Hours=(TextView)view.findViewById(R.id.YesterDay);

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
