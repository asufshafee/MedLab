package com.mcc.medlabs.view.Fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class About_Developers extends Fragment {


    MyApplication myApplication;

    public About_Developers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about__developers, container, false);

        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.about_developers));
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.about_developers));
        }
        ((MainActivity) getActivity()).ShowBack_toolbar();

        view.findViewById(R.id.mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail("info@ mobileznation.com");

            }
        });
        view.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                socialActions("http://www.mobileznation.com");

            }
        });


        return view;


    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

    public void socialActions(String url) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
    }

    public void sendEmail(String email) {
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("message/rfc822");
        String[] arrayOfString = new String[1];
        arrayOfString[0] = email;
        localIntent.putExtra("android.intent.extra.EMAIL", arrayOfString);
        localIntent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name_share));
        localIntent.putExtra("android.intent.extra.TEXT", "");
        try {
            startActivity(localIntent);
            return;
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_LONG).show();

        }

    }


}
