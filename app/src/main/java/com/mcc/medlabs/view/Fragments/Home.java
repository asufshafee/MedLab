package com.mcc.medlabs.view.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {


    public Home() {
        // Required empty public constructor
    }

    MyApplication myApplication;
    public static TextView Name, Details;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();

        Name = view.findViewById(R.id.TipName);
        Details = view.findViewById(R.id.TipDetails);
        if (!MainActivity.TipName.equals("")) {
            Name.setText(MainActivity.TipName);
            Details.setText(MainActivity.TipDetails);

        }

        ((MainActivity) getActivity()).GetNotifications();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("HOME PAGE");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.HOEM_PAGE));

        }
        ((MainActivity) getActivity()).HideToolbarWithBack();

        view.findViewById(R.id.Schedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myApplication.GetLanguage().equals("en"))
                    ((MainActivity) getActivity()).ShowFragment(new Schecule_a_house(), "Schecule_a_house");
                else
                    ((MainActivity) getActivity()).ShowFragment(new Schecule_a_house(), getString(R.string.Schedule_a_house_call));

            }
        });
        view.findViewById(R.id.News).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myApplication.GetLanguage().equals("en"))
                    ((MainActivity) getActivity()).ShowFragment(new News(), "News");
                else
                    ((MainActivity) getActivity()).ShowFragment(new News(), getString(R.string.News));
            }
        });
        view.findViewById(R.id.Company).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myApplication.GetLanguage().equals("en"))
                    ((MainActivity) getActivity()).ShowFragment(new Insurance_Companies(), "Insurance Companies");
                else
                    ((MainActivity) getActivity()).ShowFragment(new Insurance_Companies(), getString(R.string.Insurance_Companies));

            }
        });
        view.findViewById(R.id.ContectUS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myApplication.GetLanguage().equals("en"))
                    ((MainActivity) getActivity()).ShowFragment(new Contact_Us(), "Contact Us");
                else
                    ((MainActivity) getActivity()).ShowFragment(new Contact_Us(), getString(R.string.Contect_Us));

            }
        });
        view.findViewById(R.id.Tips).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myApplication.GetLanguage().equals("en"))
                    ((MainActivity) getActivity()).ShowFragment(new Tips(), "Tips");
                else
                    ((MainActivity) getActivity()).ShowFragment(new Tips(), getString(R.string.Tips));

            }
        });


        return view;
    }

}
