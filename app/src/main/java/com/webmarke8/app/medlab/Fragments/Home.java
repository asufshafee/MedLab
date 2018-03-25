package com.webmarke8.app.medlab.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((MainActivity) getActivity()).Change_Tittle("HOME PAGE");
        ((MainActivity) getActivity()).HideToolbarWithBack();

        view.findViewById(R.id.Schedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).ShowFragment(new Schecule_a_house(), "Schecule_a_house");
            }
        });
        view.findViewById(R.id.News).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).ShowFragment(new News(), "News");
            }
        });
        view.findViewById(R.id.Company).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).ShowFragment(new Insurance_Companies(), "Insurance Companies");
            }
        });
        view.findViewById(R.id.ContectUS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).ShowFragment(new Contact_Us(), "Contact Us");
            }
        });
        view.findViewById(R.id.Tips).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).ShowFragment(new Tips(), "Tips");
            }
        });


        return view;
    }

}
