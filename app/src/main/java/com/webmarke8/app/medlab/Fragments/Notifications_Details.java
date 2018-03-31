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
import com.webmarke8.app.medlab.Objects.Notification;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notifications_Details extends Fragment {

    Notification.RespOBJObject respOBJObject;

    TextView Name, Date, Description;

    public Notifications_Details() {
        // Required empty public constructor
    }

    MyApplication myApplication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications__details, container, false);

        Name = (TextView) view.findViewById(R.id.Name);
        Date = (TextView) view.findViewById(R.id.Date);
        Description = (TextView) view.findViewById(R.id.Description);


        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en")) {
            ((MainActivity) getActivity()).Change_Tittle("Notification");

        } else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Notification));

        }
        ((MainActivity) getActivity()).ShowBack_toolbar();

        respOBJObject = (Notification.RespOBJObject) getArguments().getSerializable("data");

        Name.setText(respOBJObject.getText());
        Date.setText(respOBJObject.getDate());
        Description.setText(respOBJObject.getDescription());
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

}
