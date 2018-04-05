package com.mcc.medlabs.view.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.medialablk.easytoast.EasyToast;
import com.mcc.medlabs.view.Activities.Help_Screen;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

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
        ((MainActivity) getActivity()).GetNotifications();


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
                if (myApplication.GetLanguage().equals("en")) {
                    ((MainActivity) getActivity()).ChangeTintColors(ManageIcon, ManageName, new Manage_My_Health(), "Manage My Health");

                } else {
                    ((MainActivity) getActivity()).ChangeTintColors(ManageIcon, ManageName, new Manage_My_Health(), getString(R.string.Manage_My_Helth));

                }
                Refresh();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        RemoveTint(ManageIcon, ManageName);
                    }
                }, 1000);
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
                myApplication.logoutUser();
                ChangeTint(LogoutIcon, LogoutName);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        RemoveTint(LogoutIcon, LogoutName);
                        if (myApplication.isLoggedIn())
                            EasyToast.success(getActivity(), getString(R.string.success));
                        else {
                            EasyToast.success(getActivity(), getString(R.string.you_are_not_login));

                        }
                        Refresh();

                    }
                }, 1000);
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
                if (myApplication.GetLanguage().equals("en")) {
                    ((MainActivity) getActivity()).ChangeTintColors(SettingsIcon, SettingsName, new Settings(), "Settings");

                } else {
                    ((MainActivity) getActivity()).ChangeTintColors(SettingsIcon, SettingsName, new Settings(), getString(R.string.Settings));

                }
                Refresh();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        RemoveTint(SettingsIcon, SettingsName);
                    }
                }, 1000);

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

                if (myApplication.GetLanguage().equals("en")) {
                    ((MainActivity) getActivity()).ChangeTintColors(TestIcon, TestName, new Featured_Test(), "Featured Test");

                } else {
                    ((MainActivity) getActivity()).ChangeTintColors(TestIcon, TestName, new Featured_Test(), getString(R.string.Featured_Test));

                }

                Refresh();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        RemoveTint(TestIcon, TestName);
                    }
                }, 1000);
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
                ChangeTint(HelpIcon, HelpName);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        RemoveTint(HelpIcon, HelpName);
                        Intent intent = new Intent(getActivity(), Help_Screen.class);
                        startActivity(intent);
                        Refresh();
                    }
                }, 500);

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
        try {
            imageView.setColorFilter(getActivity().getResources().getColor(R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);
            textView.setTextColor(getActivity().getResources().getColor(R.color.textColor));
        } catch (Exception Ex) {

        }

    }

    public void ChangeTint(ImageView imageView, TextView textView) {
        try {
            imageView.setColorFilter(getActivity().getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
            textView.setTextColor(getActivity().getResources().getColor(R.color.red));
        } catch (Exception Ex) {

        }

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
