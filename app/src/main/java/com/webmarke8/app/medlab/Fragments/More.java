package com.webmarke8.app.medlab.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.medialablk.easytoast.EasyToast;
import com.webmarke8.app.medlab.Activities.Help_Screen;
import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

import java.sql.Ref;

/**
 * A simple {@link Fragment} subclass.
 */
public class More extends Fragment {


    Boolean ManageMyHealth = false, Settings = false, FeaturedTest = false, Help = false, Logout = false;

    ImageView ManageIcon, SettingsIcon, TestIcon, HelpIcon, LogoutIcon;
    TextView ManageName, SettingsName, TestName, HelpName, LogoutName;

    MyApplication myApplication;


    public More() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();


        ((MainActivity) getActivity()).HideToolbarWithBack();

        ManageIcon = (ImageView) view.findViewById(R.id.ManageIcon);
        ManageName = (TextView) view.findViewById(R.id.ManageName);

        SettingsIcon = (ImageView) view.findViewById(R.id.SettingsIcon);
        SettingsName = (TextView) view.findViewById(R.id.SettingsName);

        TestIcon = (ImageView) view.findViewById(R.id.TestIcon);
        TestName = (TextView) view.findViewById(R.id.TestName);

        HelpIcon = (ImageView) view.findViewById(R.id.HelpIcon);
        HelpName = (TextView) view.findViewById(R.id.HelpName);

        LogoutIcon = (ImageView) view.findViewById(R.id.LogouIcon);
        LogoutName = (TextView) view.findViewById(R.id.LogoutName);

        if (myApplication.GetLanguage().equals("ar")) {
            ((MainActivity) getActivity()).Change_Tittle("أكثر من");
            ManageName.setText(getString(R.string.Manage_My_Helth));
            SettingsName.setText(getString(R.string.Settings));
            TestName.setText("اختبار متميز");
            HelpName.setText(getString(R.string.Help));
            LogoutName.setText(getString(R.string.Logout));
        } else {
            ((MainActivity) getActivity()).Change_Tittle("More");
        }
        Refresh();


        view.findViewById(R.id.ManageMyHealth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManageMyHealth = true;
                Settings = false;
                FeaturedTest = false;
                Help = false;
                Logout = false;
                ((MainActivity) getActivity()).ChangeTintColors(ManageIcon, ManageName, new Manage_My_Health(), "Manage My Health");
                Refresh();
            }
        });


        view.findViewById(R.id.Logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManageMyHealth = false;
                Settings = false;
                FeaturedTest = false;
                Help = false;
                Logout = true;
                EasyToast.success(getActivity(), "Success");
//                ((MainActivity) getActivity()).ChangeTintColors(ManageIcon, ManageName, new Manage_My_Health(), "Manage My Health");
                Refresh();
            }
        });


        view.findViewById(R.id.Settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManageMyHealth = false;
                Settings = true;
                FeaturedTest = false;
                Help = false;
                Logout = false;
                ((MainActivity) getActivity()).ChangeTintColors(SettingsIcon, SettingsName, new Settings(), "Settings");
                Refresh();
            }
        });


        view.findViewById(R.id.FeaturedTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManageMyHealth = false;
                Settings = false;
                FeaturedTest = true;
                Help = false;
                Logout = false;
                ((MainActivity) getActivity()).ChangeTintColors(TestIcon, TestName, new Featured_Test(), "Featured Test");
                Refresh();
            }
        });


        view.findViewById(R.id.Help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManageMyHealth = false;
                Settings = false;
                FeaturedTest = false;
                Help = true;
                Logout = false;
                Intent intent = new Intent(getActivity(), Help_Screen.class);
                startActivity(intent);
                Refresh();
            }
        });


        ManageMyHealth = false;
        Settings = false;
        FeaturedTest = false;
        Help = false;
        Logout = false;


        return view;
    }

    public void RemoveTint(ImageView imageView, TextView textView) {
        imageView.setColorFilter(getResources().getColor(R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);
        textView.setTextColor(getActivity().getResources().getColor(R.color.textColor));
    }

    public void Refresh() {
        if (Logout) {
            RemoveTint(HelpIcon, HelpName);
            RemoveTint(SettingsIcon, SettingsName);
            RemoveTint(TestIcon, TestName);
            RemoveTint(ManageIcon, ManageName);

        }

        if (Help) {

            RemoveTint(SettingsIcon, SettingsName);
            RemoveTint(TestIcon, TestName);
            RemoveTint(ManageIcon, ManageName);
            RemoveTint(LogoutIcon, LogoutName);
        }
        if (ManageMyHealth) {
            RemoveTint(HelpIcon, HelpName);
            RemoveTint(SettingsIcon, SettingsName);
            RemoveTint(TestIcon, TestName);
            RemoveTint(LogoutIcon, LogoutName);
        }

        if (Settings) {
            RemoveTint(HelpIcon, HelpName);
            RemoveTint(TestIcon, TestName);
            RemoveTint(ManageIcon, ManageName);
            RemoveTint(LogoutIcon, LogoutName);
        }

        if (FeaturedTest) {
            RemoveTint(HelpIcon, HelpName);
            RemoveTint(SettingsIcon, SettingsName);
            RemoveTint(ManageIcon, ManageName);
            RemoveTint(LogoutIcon, LogoutName);
        }
    }

}
