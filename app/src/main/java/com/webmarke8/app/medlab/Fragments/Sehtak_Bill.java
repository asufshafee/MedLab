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
public class Sehtak_Bill extends Fragment {


    public Sehtak_Bill() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sehtak__bill, container, false);
        ((MainActivity) getActivity()).Change_Tittle("SEHTAK BIL DENIA");
        ((MainActivity) getActivity()).HideToolbarWithBack();

        return view;
    }

}
