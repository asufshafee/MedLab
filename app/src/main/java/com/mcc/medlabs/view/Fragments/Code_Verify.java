package com.mcc.medlabs.view.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class Code_Verify extends Fragment {


    public Code_Verify() {
        // Required empty public constructor
    }
    MyApplication myApplication;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_code__verify, container, false);
        ((MainActivity) getActivity()).Change_Tittle("MedLabs");
        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("MedLabs");
        else {
            ((MainActivity) getActivity()).Change_Tittle("MedLabs");

        }
        return view;
    }

}
