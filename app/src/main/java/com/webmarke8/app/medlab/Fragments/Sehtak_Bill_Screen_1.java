package com.webmarke8.app.medlab.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.Objects.Shakha;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sehtak_Bill_Screen_1 extends Fragment {


    public Sehtak_Bill_Screen_1() {
        // Required empty public constructor
    }

    Shakha.SahtakBilDeniaObObject sahtakBilDeniaObObject;
    MyApplication myApplication;
    TextView HtmlText, Name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sehtak__bill__screen_1, container, false);
//        ((MainActivity) getActivity()).Change_Tittle("HairLoss Checkups");


        sahtakBilDeniaObObject = (Shakha.SahtakBilDeniaObObject) getArguments().getSerializable("data");
        Name = (TextView) view.findViewById(R.id.Name);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        HtmlText = (TextView) view.findViewById(R.id.Html);

        ((MainActivity) getActivity()).ShowBack_toolbar();
        if (myApplication.GetLanguage().equals("en")) {

            ((MainActivity) getActivity()).Change_Tittle(sahtakBilDeniaObObject.getProgramName());
            Name.setText(sahtakBilDeniaObObject.getProgramName());
            Spanned htmlAsSpanned = Html.fromHtml(sahtakBilDeniaObObject.getDescription()); // used by TextView
            htmlAsSpanned = Html.fromHtml(sahtakBilDeniaObObject.getDescription());
            HtmlText.setText(htmlAsSpanned);


        } else {
            ((MainActivity) getActivity()).Change_Tittle(sahtakBilDeniaObObject.getProgramNameAr());
            Name.setText(sahtakBilDeniaObObject.getProgramNameAr());
            Spanned htmlAsSpanned = Html.fromHtml(sahtakBilDeniaObObject.getDescriptionAr()); // used by TextView
            htmlAsSpanned = Html.fromHtml(sahtakBilDeniaObObject.getDescriptionAr());
            HtmlText.setText(htmlAsSpanned);
        }


        return view;


    }


    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }


}
