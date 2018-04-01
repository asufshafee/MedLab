package com.mcc.medlabs.view.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.squareup.picasso.Picasso;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.Locations;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.GPSTracker;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lab_Locations_Details extends Fragment {


    Locations.BranchObObject branchObObject;

    public Lab_Locations_Details() {
        // Required empty public constructor
    }

    MyApplication myApplication;
    GPSTracker gpsTracker;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lab__locations__details, container, false);
        ((MainActivity) getActivity()).ShowBack_toolbar();
        myApplication = (MyApplication) getActivity().getApplicationContext();
        gpsTracker = new GPSTracker(getActivity());

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
        view.findViewById(R.id.Directions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions((Activity) getActivity(),
                            new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
                            1);
                    return;
                }

                GPSTracker gps = new GPSTracker(getActivity());


//				 check if GPS enabled
                if (gps.canGetLocation()) {
                    Location location = gps.getLocation();
                    if (location != null) {
                        final Intent intent = new
                                Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                                "saddr=" + location.getLatitude() + "," + location.getLongitude() + "&daddr=" + branchObObject.getLatitude() + "," +
                                branchObObject.getLongitude()));
                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                        startActivity(intent);
                    }
                } else {
                    showSettingsAlert();
                }

            }
        });

        view.findViewById(R.id.Call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions((Activity) getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + branchObObject.getTelephone()));
                startActivity(callIntent);
            }
        });

        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        // Setting Dialog Title
        alertDialog.setTitle(R.string.app_name);

        // Setting Dialog Message
        alertDialog.setMessage(R.string.gps_setting_enable_message);

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // On pressing Settings button
        alertDialog.setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
