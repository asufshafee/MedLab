package com.webmarke8.app.medlab.Activities;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.webmarke8.app.medlab.Fragments.Home;
import com.webmarke8.app.medlab.Fragments.Labs_locations;
import com.webmarke8.app.medlab.Fragments.Login;
import com.webmarke8.app.medlab.Fragments.More;
import com.webmarke8.app.medlab.Fragments.Notifications;
import com.webmarke8.app.medlab.Fragments.Sehtak_Bill;
import com.webmarke8.app.medlab.Fragments.Test_Directory;
import com.webmarke8.app.medlab.Fragments.Test_Result_Screen;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView Home, Heart, More, Location, Result;
    Boolean HomeSelected = true, HeartSelected = false, MoreSelected = false, LocationSelected = false, ResultSelected = false;

    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myApplication = (MyApplication) getApplicationContext();

        Home = (ImageView) findViewById(R.id.Home);
        Heart = (ImageView) findViewById(R.id.Heart);
        More = (ImageView) findViewById(R.id.More);
        Location = (ImageView) findViewById(R.id.Location);
        Result = (ImageView) findViewById(R.id.Result);
        HideToolbarWithBack();

        Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon_selected));
        findViewById(R.id.Notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragment(new Notifications(), "Notifications");
            }
        });
        findViewById(R.id.Search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragment(new Test_Directory(), "Test Directory");


            }
        });
        Back();
        Refresh();
    }

    public void More(View view) {
        MoreSelected = true;
        HomeSelected = false;
        HeartSelected = false;
        LocationSelected = false;
        ResultSelected = false;
        Refresh();
    }

    public void Location(View view) {
        MoreSelected = false;
        HomeSelected = false;
        HeartSelected = false;
        LocationSelected = true;
        ResultSelected = false;
        Refresh();
    }

    public void Result(View view) {
        MoreSelected = false;
        HomeSelected = false;
        HeartSelected = false;
        LocationSelected = false;
        ResultSelected = true;
        Refresh();
    }

    public void Heart(View view) {
        MoreSelected = false;
        HomeSelected = false;
        HeartSelected = true;
        LocationSelected = false;
        ResultSelected = false;
        Refresh();
    }

    public void Home(View view) {
        MoreSelected = false;
        HomeSelected = true;
        HeartSelected = false;
        LocationSelected = false;
        ResultSelected = false;
        Refresh();
    }

    public void Refresh() {
        if (HomeSelected) {
            if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("HOME PAGE")) {

            } else
                ShowTopFragment(new Home(), "HOME PAGE");
            Change_Tittle("HOME PAGE");
            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon_selected));
            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon));
            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon));
            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon));
            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_iconn));
        }


        if (HeartSelected) {
            if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("SEHTAK BIL DENIA")) {

            } else
                ShowTopFragment(new Sehtak_Bill(), "SEHTAK BIL DENIA");
            Change_Tittle("SEHTAK BIL DENIA");
            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_icon_selected));
            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon));
            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon));
            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon));
            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon));
        }


        if (ResultSelected) {
            if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("MedLabs")) {

            } else {
                if (!myApplication.isLoggedIn()) {
                    Change_Tittle("MedLabs");
                    ShowTopFragment(new Login(), "MedLabs");
                } else {
                    Change_Tittle("Test Results");
                    ShowTopFragment(new Test_Result_Screen(), "Test Results");
                }
            }

            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon_selected));
            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon));
            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon));
            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon));
            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_iconn));
        }

        if (LocationSelected) {
            if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("Labs Locations")) {

            } else ShowTopFragment(new Labs_locations(), "Labs Locations");
            Change_Tittle("Labs Locations");
            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon_selected));
            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon));
            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon));
            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon));
            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_iconn));
        }

        if (MoreSelected) {
            if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("More")) {

            } else ShowTopFragment(new More(), "More");
            Change_Tittle("More");
            More.setImageDrawable(getResources().getDrawable(R.drawable.more_icon_selected));
            Location.setImageDrawable(getResources().getDrawable(R.drawable.location_icon));
            Result.setImageDrawable(getResources().getDrawable(R.drawable.result_icon));
            Home.setImageDrawable(getResources().getDrawable(R.drawable.home_icon));
            Heart.setImageDrawable(getResources().getDrawable(R.drawable.heart_iconn));
        }

    }

    public void HideToolbarWithBack() {
        View view = (View) findViewById(R.id.toolbar_with_back);
        view.setVisibility(View.INVISIBLE);
    }

    public void Change_Tittle(String Text) {
        TextView Tittle = (TextView) findViewById(R.id.Tittle);
        Tittle.setText(Text);
        TextView Tittle_Back = (TextView) findViewById(R.id.Tittle_Back);
        Tittle_Back.setText(Text);


    }

    public void ShowTopFragment(Fragment fragment, String Tag) {

        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, Tag).setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack("").commit();
    }

    private Fragment getCurrentFragment() {
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getUserVisibleHint())
                return fragment;
        }
        return null;
    }

    public void ShowBack_toolbar() {

        View view = (View) findViewById(R.id.toolbar_with_back);
        view.setVisibility(View.VISIBLE);
    }

    public void ShowShare_toolbar() {

        ImageView Share = (ImageView) findViewById(R.id.Share);
        Share.setImageDrawable(getResources().getDrawable(R.drawable.share_icon));

    }

    public void HideShare_toolbar() {

        ImageView Share = (ImageView) findViewById(R.id.Share);
        Share.setImageDrawable(getResources().getDrawable(R.drawable.menu_dot));

    }


    public void Back() {

        findViewById(R.id.Beck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                if (getCurrentFragment() != null) {

                    if (getCurrentFragment().getTag().equals("Lab_Locations_Details")) {


                        Change_Tittle("Labs Locations");

                    } else {

                        Change_Tittle(getCurrentFragment().getTag());

                    }
                    String Tittle = getCurrentFragment().getTag();
                    if (getCurrentFragment().getTag().equals("More") || getCurrentFragment().getTag().equals("Labs Locations") || getCurrentFragment().getTag().equals("MedLabs") || getCurrentFragment().getTag().equals("SEHTAK BIL DENIA") || getCurrentFragment().getTag().equals("HOME PAGE") || getCurrentFragment().getTag().equals("Test Results")) {
                        HideToolbarWithBack();
                    }
                }
            }
        });
    }

    public void ShowFragment(Fragment fragment, String Tag) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, Tag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("Home")) {
        } else {
            super.onBackPressed();
        }
    }

    public void ChangeTintColors(ImageView imageView, TextView textView, Fragment fragment, String Tag) {
        imageView.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
        textView.setTextColor(getResources().getColor(R.color.red));
        ShowFragment(fragment, Tag);
    }
}
