package com.mcc.medlabs.view.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.Shakha;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sehtak_Bill_Screen_1 extends Fragment {


    public Sehtak_Bill_Screen_1() {
        // Required empty public constructor
    }

    Shakha.SahtakBilDeniaObObject sahtakBilDeniaObObject;
    MyApplication myApplication;
    TextView  Name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sehtak__bill__screen_1, container, false);
//        ((MainActivity) getActivity()).Change_Tittle("HairLoss Checkups");


        sahtakBilDeniaObObject = (Shakha.SahtakBilDeniaObObject) getArguments().getSerializable("data");
        Name = (TextView) view.findViewById(R.id.Name);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        WebView wv = (WebView) view.findViewById(R.id.Details);


        ((MainActivity) getActivity()).ShowBack_toolbar();
        if (myApplication.GetLanguage().equals("en")) {

            ((MainActivity) getActivity()).Change_Tittle(sahtakBilDeniaObObject.getProgramName());
            Name.setText(sahtakBilDeniaObObject.getProgramName());
            String html = sahtakBilDeniaObObject.getDescription();
            wv.loadData(html, "text/html", "utf-8");


        } else {
            ((MainActivity) getActivity()).Change_Tittle(sahtakBilDeniaObObject.getProgramNameAr());
            Name.setText(sahtakBilDeniaObObject.getProgramNameAr());
            String html = sahtakBilDeniaObObject.getDescriptionAr();
            wv.loadData(html, "text/html", "utf-8");

        }


        return view;


    }


    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }


}
