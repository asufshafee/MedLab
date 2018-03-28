package com.webmarke8.app.medlab.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webmarke8.app.medlab.Activities.MainActivity;
import com.webmarke8.app.medlab.Adapters.Locations_Adapter;
import com.webmarke8.app.medlab.Adapters.News_Adapter;
import com.webmarke8.app.medlab.Objects.Locations;
import com.webmarke8.app.medlab.Objects.News_Object;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Utils.AppUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class News extends Fragment {


    Dialog Progress;
    RecyclerView recycle;
    java.util.List<News_Object> List;

    public News() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
//        ((MainActivity) getActivity()).Change_Tittle("News");
        ((MainActivity) getActivity()).ShowBack_toolbar();


        recycle = (RecyclerView) view.findViewById(R.id.recycle);
        Progress = AppUtils.LoadingSpinner(getActivity());
        LoadData();

        return view;
    }

    public void LoadData() {
        Progress.dismiss();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        News_Adapter Adapter = new News_Adapter(List, getActivity());
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(Adapter);
    }




}
