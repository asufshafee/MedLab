package com.mcc.medlabs.view.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.barteksc.pdfviewer.util.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Adapters.Locations_Adapter;
import com.mcc.medlabs.view.Objects.JsonParserLocation;
import com.mcc.medlabs.view.Objects.Locations;
import com.mcc.medlabs.view.Objects.Locations_on_Map;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;
import com.mcc.medlabs.view.Utils.GPSTracker;
import com.medialablk.easytoast.EasyToast;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lab_Locations_Map extends Fragment implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {


    GoogleMap googleMap;
    Locations locations;
    Locations_on_Map LocationsALL;
    MyApplication myApplication;
    GPSTracker gpsTracker;
    private HashMap<Marker, Integer> mHashMapLabs = new HashMap<Marker, Integer>();
    private HashMap<Marker, String> mHashMapStore = new HashMap<Marker, String>();
    private HashMap<Marker, Locations_on_Map.BranchObObject> markerLabs = new HashMap<Marker, Locations_on_Map.BranchObObject>();

    Dialog Progress;

    public Lab_Locations_Map() {
        // Required empty public constructor
    }

    Marker lastClicked = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lab__locations__map, container, false);
        gpsTracker = new GPSTracker(getActivity());
        Progress = AppUtils.LoadingSpinner(getActivity());
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
        googleMap.setInfoWindowAdapter(this);
        GetLocations();

        Location location = new Location("");
        GPSTracker gpsTracker = new GPSTracker(getActivity());
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()),
                13));
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
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String medicalCentersObject = (String) marker.getTag();
                if (medicalCentersObject != null && medicalCentersObject.equals("medical")) {
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.medical_pin));

                } else {
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.lab_pin));
                }

                lastClicked = marker;
                return false;
            }
        });


        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                String medicalCentersObject = (String) marker.getTag();
                if (medicalCentersObject != null && medicalCentersObject.equals("medical")) {
                    OpenDirections(marker);
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.medical_pin));

                } else {
                    try {
                        Locations_on_Map.BranchObObject place = markerLabs.get(marker);


                        Fragment fragment = null;
                        Class fragmentClass = null;

                        fragmentClass = Lab_Locations_Details.class;
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("Branch", place);
                            fragment.setArguments(bundle);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment, "Lab_Locations_Details").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();


                    } catch (Exception Ex) {

                    }

                }


            }
        });

    }


    private void GetLocations() {

        Progress.show();
        Gson gson = new Gson();
        final JsonParserLocation jsonParserLocation = new JsonParserLocation();
        JsonParserLocation.DeviceObject deviceObject = new JsonParserLocation.DeviceObject();
        deviceObject.setPlatform("Android");
        deviceObject.setResolution(myApplication.RESOLUATION);
        deviceObject.setVersion(myApplication.APP_VERSION);
        jsonParserLocation.setWSPassword("Medl@p$app17");
        jsonParserLocation.setWSUsername("Medlabs");
        jsonParserLocation.setUserId(Integer.parseInt(myApplication.USER_ID));
        jsonParserLocation.setDevice(deviceObject);
        jsonParserLocation.setLocationLatitude(gpsTracker.getLatitude());
        jsonParserLocation.setLocationLongitude(gpsTracker.getLongitude());
        jsonParserLocation.setPageIndex(1);
        jsonParserLocation.setPageSize(100);
        final String request = gson.toJson(jsonParserLocation);

        String Url = myApplication.GetPinLocations;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    if (response.contains("Success")) {


                        Gson gson = new Gson();
                        Locations_on_Map locations = new Locations_on_Map();
                        locations = gson.fromJson(response, Locations_on_Map.class);
                        Progress.dismiss();
                        LocationsALL = locations;


                        AddMarker();


                    } else {
                        if (myApplication.GetLanguage().equals("en"))
                            EasyToast.error(getActivity(), "Something Went Wrong!!");
                        else
                            EasyToast.error(getActivity(), " هناك خطأ ما");
                    }

                } catch (Exception a) {

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Progress.dismiss();
                        if (AppUtils.isNetworkAvailable(getActivity())) {
                            EasyToast.error(getActivity(), getString(R.string.NO_INTERNET_CONNECTION));
                        } else {
                            EasyToast.error(getActivity(), getString(R.string.something_went_wrong));
                        }

                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ServerError) {
                        } else if (error instanceof NetworkError) {
                        } else if (error instanceof ParseError) {
                        }
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return request == null ? null : request.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", request, "utf-8");
                    return null;
                }
            }


            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    public void OpenDirections(Marker marker) {
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
                        "saddr=" + location.getLatitude() + "," + location.getLongitude() + "&daddr=" + marker.getPosition().latitude + "," +
                        marker.getPosition().longitude));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        } else {
            showSettingsAlert();
        }

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

    @Override
    public View getInfoWindow(Marker marker) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.custominfowindow, null, false);
        TextView reviewTextView = (TextView) view.findViewById(R.id.Name);
        reviewTextView.setText(marker.getTitle());
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }


    public void AddMarker() {


        for (Locations_on_Map.MedicalCentersObject branchObObject : LocationsALL.getMedicalCenters()) {
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.medical_pin))
                    .position(new LatLng(Double.parseDouble(branchObObject.getLatitude()), Double.parseDouble(branchObObject.getLongitude()))));

            if (myApplication.GetLanguage().equals("en"))
                marker.setTitle(branchObObject.getBranch());
            else marker.setTitle(branchObObject.getBranchAr());
            marker.setTag("medical");
            mHashMapStore.put(marker, marker.getId());

        }


        for (Locations_on_Map.BranchObObject branchObObject : LocationsALL.getBranchOb()) {

            Boolean Check = true;
            for (Locations_on_Map.MedicalCentersObject branchObObjectTst : LocationsALL.getMedicalCenters()) {

                if (branchObObjectTst.getLatitude().equals(branchObObject.getLatitude())) {
                    if (branchObObjectTst.getLongitude().equals(branchObObject.getLongitude()))
                        Check = false;
                }
            }

            if (Check) {
                Marker marker1 = googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.lab_pin))
                        .position(new LatLng(Double.parseDouble(branchObObject.getLatitude()), Double.parseDouble(branchObObject.getLongitude()))));
                if (myApplication.GetLanguage().equals("en"))
                    marker1.setTitle(branchObObject.getBranch());
                else marker1.setTitle(branchObObject.getBranchAr());
                markerLabs.put(marker1, branchObObject);
            }


        }

    }


}
