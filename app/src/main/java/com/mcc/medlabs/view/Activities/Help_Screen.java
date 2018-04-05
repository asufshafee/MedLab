package com.mcc.medlabs.view.Activities;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.github.paolorotolo.appintro.AppIntro;
import com.mcc.medlabs.view.DatabasePart.MedlabsDelegate;
import com.mcc.medlabs.view.HelpFragments.Help_1;
import com.mcc.medlabs.view.HelpFragments.Help_2;
import com.mcc.medlabs.view.HelpFragments.Help_3;
import com.mcc.medlabs.view.HelpFragments.Help_4;
import com.mcc.medlabs.view.HelpFragments.Help_5;
import com.mcc.medlabs.view.HelpFragments.Help_6;
import com.mcc.medlabs.view.HelpFragments.Help_7;

public class Help_Screen extends AppIntro {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(new Help_1());
        addSlide(new Help_2());
        addSlide(new Help_3());
        addSlide(new Help_4());
        addSlide(new Help_5());
        addSlide(new Help_6());
        addSlide(new Help_7());

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#80000000"));
        setSeparatorColor(Color.parseColor("#80000000"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
