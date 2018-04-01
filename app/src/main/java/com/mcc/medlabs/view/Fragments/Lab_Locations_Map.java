package com.mcc.medlabs.view.Fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.Locations;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lab_Locations_Map extends Fragment implements OnMapReadyCallback {


    GoogleMap googleMap;
    Locations locations;
    MyApplication myApplication;

    public Lab_Locations_Map() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lab__locations__map, container, false);
        ((MainActivity) getActivity()).ShowBack_toolbar();
        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("Labs Locations");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Labs_Locations));

        }

        locations = (Locations) getArguments().getSerializable("Locations");

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

    @Override
    public void onMapReady(GoogleMap googleMa) {
        googleMap = googleMa;
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setInfoWindowAdapter(new WindowMarker());
//        try {
//            // Customise the styling of the base map using a JSON object defined
//            // in a raw resource file.
//            boolean success = googleMap.setMapStyle(
//                    MapStyleOptions.loadRawResourceStyle(
//                            getActivity(), R.raw.style));
//
//            if (!success) {
//                Log.e("MapsActivityRaw", "Style parsing failed.");
//            }
//        } catch (Resources.NotFoundException e) {
//            Log.e("MapsActivityRaw", "Can't find style.", e);
//        }
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        for (Locations.BranchObObject branchObObject : locations.getBranchOb()) {
            googleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.lab_pin))
                    .position(new LatLng(Double.parseDouble(branchObObject.getLatitude()), Double.parseDouble(branchObObject.getLongitude()))))
            ;
        }

    }

    class WindowMarker implements GoogleMap.InfoWindowAdapter {
        private final View mymarkerview;

        WindowMarker() {
            mymarkerview = getLayoutInflater()
                    .inflate(R.layout.custominfowindow, null);
        }

        public View getInfoWindow(Marker marker) {
            render(marker, mymarkerview);
            return mymarkerview;
        }

        public View getInfoContents(Marker marker) {
            return null;
        }

        private void render(Marker marker, View view) {
            // Add the code to set the required values
            // for each element in your custominfowindow layout file
        }
    }

}
