package com.webmarke8.app.medlab.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.squareup.picasso.Picasso;
import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.Objects.Locations;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lab_Locations_Details extends Fragment {


    Locations.BranchObObject branchObObject;

    public Lab_Locations_Details() {
        // Required empty public constructor
    }

    MyApplication myApplication;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lab__locations__details, container, false);
        ((MainActivity) getActivity()).ShowBack_toolbar();
        myApplication = (MyApplication) getActivity().getApplicationContext();

//        ((MainActivity) getActivity()).Change_Tittle("Labs Locations");


        branchObObject = (Locations.BranchObObject) getArguments().getSerializable("Branch");


        TextView Phone = (TextView) view.findViewById(R.id.Phone);
        TextView Name = (TextView) view.findViewById(R.id.Name);
        TextView Address = (TextView) view.findViewById(R.id.Address);

        ImageView Image = (ImageView) view.findViewById(R.id.Image);
        Picasso.with(getActivity()).load(branchObObject.getImage()).into(Image);


        if (myApplication.GetLanguage().equals("en")) {
            Phone.setText(branchObObject.getTelephone());
            Name.setText(branchObObject.getBranch());
            Address.setText(branchObObject.getAddress());
        } else {

            Phone.setText(branchObObject.getTelephone());
            Name.setText(branchObObject.getBranchAr());
            Address.setText(branchObObject.getAddressAr());
        }


        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

}
