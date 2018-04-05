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
public class News_Details extends Fragment {


    News_Object.NewsObObject sahtakBilDeniaObObject;

    ImageView Image;
    TextView Name, Details, Date;

    MyApplication myApplication;

    public News_Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news__details, container, false);

        myApplication = (MyApplication) getActivity().getApplicationContext();

        Image = (ImageView) view.findViewById(R.id.Image);
        Name = (TextView) view.findViewById(R.id.Name);
        Date = (TextView) view.findViewById(R.id.Date);
        sahtakBilDeniaObObject = (News_Object.NewsObObject) getArguments().getSerializable("data");


        Details = (TextView) view.findViewById(R.id.Details);


        if (!sahtakBilDeniaObObject.getImage().equals(""))
            Picasso.with(getActivity()).load(sahtakBilDeniaObObject.getImage()).into(Image);

        if (myApplication.GetLanguage().equals("en")) {
            Name.setText(sahtakBilDeniaObObject.getName());
            Details.setText(sahtakBilDeniaObObject.getDescription());
            Date.setText(sahtakBilDeniaObObject.getDate());

        } else {
            Name.setText(sahtakBilDeniaObObject.getName());
            Details.setText(sahtakBilDeniaObObject.getDescriptionAr());
            Date.setText(sahtakBilDeniaObObject.getDate());
        }


        return view;
    }

}
