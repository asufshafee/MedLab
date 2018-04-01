package com.mcc.medlabs.view.Fragments;


import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.medialablk.easytoast.EasyToast;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Activities.Splash;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {


    public Settings() {
        // Required empty public constructor
    }

    Switch Notification;
    MyApplication myApplication;
    TextView NotificationName, AboutName, TermsName, languageName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        NotificationName = (TextView) view.findViewById(R.id.NotificationName);
        AboutName = (TextView) view.findViewById(R.id.AboutName);
        TermsName = (TextView) view.findViewById(R.id.TermsName);
        languageName = (TextView) view.findViewById(R.id.LanguageName);
        myApplication = (MyApplication) getActivity().getApplicationContext();


        TextView LanguageNameSelected = (TextView) view.findViewById(R.id.LanguageNameSelected);

        if (myApplication.GetLanguage().equals("ar")) {
            ((MainActivity) getActivity()).Change_Tittle("إعدادات");
            AboutName.setText(getString(R.string.About));
            TermsName.setText("الأحكام والشروط");
            NotificationName.setText(getString(R.string.Notification));
            LanguageNameSelected.setText("عربى");
        } else {
            ((MainActivity) getActivity()).Change_Tittle("Settings");
            LanguageNameSelected.setText("English(United Kingdom)");
        }
        ((MainActivity) getActivity()).ShowBack_toolbar();

        Notification = (Switch) view.findViewById(R.id.Notification);

        Notification.setChecked(myApplication.isNotificationIn());
        Notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

                NotificationName.setTextColor(getActivity().getResources().getColor(R.color.red));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        myApplication.setIsNotification(isChecked);
                        EasyToast.success(getActivity(), "Success");
                        NotificationName.setTextColor(getActivity().getResources().getColor(R.color.textColor));

                    }
                }, 1000);



            }
        });
        view.findViewById(R.id.Terms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TermsName.setTextColor(getActivity().getResources().getColor(R.color.red));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        TermsName.setTextColor(getActivity().getResources().getColor(R.color.textColor));

                    }
                }, 1000);

            }
        });
        view.findViewById(R.id.About).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AboutName.setTextColor(getActivity().getResources().getColor(R.color.red));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        AboutName.setTextColor(getActivity().getResources().getColor(R.color.textColor));

                    }
                }, 1000);
                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = About_Us.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment, "About Us").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();


            }
        });

        view.findViewById(R.id.Language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                // inflate the layout
                dialog.setContentView(R.layout.language_change);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                languageName.setTextColor(getActivity().getResources().getColor(R.color.red));
                final RadioButton Arabic = (RadioButton) dialog.findViewById(R.id.Arabic);
                RadioButton English = (RadioButton) dialog.findViewById(R.id.English);

                if (myApplication.GetLanguage().equals("en")) {
                    English.setChecked(true);
                } else {
                    Arabic.setChecked(true);
                }


                // Set the dialog text -- this is better done in the XML
                dialog.findViewById(R.id.Cross).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        languageName.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                        dialog.hide();


                    }
                });

                dialog.findViewById(R.id.Change).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        languageName.setTextColor(getActivity().getResources().getColor(R.color.textColor));


                        if (Arabic.isChecked()) {

                            if (!myApplication.GetLanguage().equals("ar")) {
                                myApplication.setLanguage("ar");
                                String languageToLoad = "ar";
                                Locale locale = new Locale(languageToLoad);
                                Locale.setDefault(locale);
                                Configuration configuration = new Configuration();
                                configuration.setLocale(locale);
                                getActivity().getResources().updateConfiguration(configuration, getActivity().getResources().getDisplayMetrics());
                                Intent intent = new Intent(getActivity(), Splash.class);
                                startActivity(intent);
                                getActivity().finish();
                            }

                        } else {
                            if (myApplication.GetLanguage().equals("ar")) {
                                String languageToLoad = "en";
                                myApplication.setLanguage("en");
                                Locale locale = new Locale(languageToLoad);
                                Locale.setDefault(locale);
                                Configuration configuration = new Configuration();
                                configuration.setLocale(locale);
                                getActivity().getResources().updateConfiguration(configuration, getActivity().getResources().getDisplayMetrics());
                                Intent intent = new Intent(getActivity(), Splash.class);
                                startActivity(intent);
                                getActivity().finish();
                            }

                        }
                        dialog.hide();

                    }
                });
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do stuff
                        languageName.setTextColor(getActivity().getResources().getColor(R.color.textColor));

                    }
                }, 1000);
            }
        });
        return view;

    }

    @Override
    public void onResume() {
        AboutName.setTextColor(getActivity().getResources().getColor(R.color.textColor));
        TermsName.setTextColor(getActivity().getResources().getColor(R.color.textColor));
        super.onResume();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

}
