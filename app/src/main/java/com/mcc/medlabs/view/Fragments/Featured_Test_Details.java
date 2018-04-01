package com.mcc.medlabs.view.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcc.medlabs.view.Objects.*;
import com.mcc.medlabs.view.Objects.Featured_Test;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class Featured_Test_Details extends Fragment {


    Featured_Test.SahtakBilDeniaObObject sahtakBilDeniaObObject;

    ImageView Image;
    TextView Name;

    MyApplication myApplication;

    public Featured_Test_Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_featured__test__details, container, false);

        myApplication = (MyApplication) getActivity().getApplicationContext();

        Image = (ImageView) view.findViewById(R.id.Image);
        Name = (TextView) view.findViewById(R.id.Name);
        sahtakBilDeniaObObject = (Featured_Test.SahtakBilDeniaObObject) getArguments().getSerializable("data");


        WebView wv = (WebView) view.findViewById(R.id.Details);


        if (!sahtakBilDeniaObObject.getImage().equals(""))
            Picasso.with(getActivity()).load(sahtakBilDeniaObObject.getImage()).into(Image);

        if (myApplication.GetLanguage().equals("en")) {
            Name.setText(sahtakBilDeniaObObject.getProgramName());
            String html = sahtakBilDeniaObObject.getDescription();
            wv.loadData(html, "text/html", "utf-8");

        } else {
            Name.setText(sahtakBilDeniaObObject.getProgramNameAr());
            String html = sahtakBilDeniaObObject.getDescriptionAr();
            wv.loadData(html, "text/html", "utf-8");
        }


        return view;
    }

}
