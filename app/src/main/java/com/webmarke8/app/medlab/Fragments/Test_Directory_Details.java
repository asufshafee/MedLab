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
import com.webmarke8.app.medlab.DatabasePart.LabwiseBean;
import com.webmarke8.app.medlab.Objects.JsonParserVisited;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class Test_Directory_Details extends Fragment {


    LabwiseBean Data;

    public Test_Directory_Details() {
        // Required empty public constructor
    }

    MyApplication myApplication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test__directory__details, container, false);
             myApplication=(MyApplication)getActivity().getApplicationContext();
//        ((MainActivity) getActivity()).Change_Tittle("Test Directory");


        if (myApplication.GetLanguage().equals("ar")) {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Test_Directory));
        } else {
            ((MainActivity) getActivity()).Change_Tittle("Test Directory");
        }
        ((MainActivity) getActivity()).ShowBack_toolbar();

        Data = (LabwiseBean) getArguments().getSerializable("Data");

        TextView Name = (TextView) view.findViewById(R.id.Name);
        TextView Description = (TextView) view.findViewById(R.id.Description);
        TextView Range = (TextView) view.findViewById(R.id.NormalRange);
        TextView Indication = (TextView) view.findViewById(R.id.Indication);
        Name.setText(Data.getName());
        Description.setText(Data.getDescription());
        Range.setText(Data.getNormalRange());
        Indication.setText(Data.getIndications());

        view.findViewById(R.id.Click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

}
