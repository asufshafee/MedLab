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
import android.view.animation.Animation;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.webmarke8.app.medlab.Adapters.Notification_Adapter;
import com.webmarke8.app.medlab.Adapters.Test_Adapter;
import com.webmarke8.app.medlab.Objects.Notification;
import com.webmarke8.app.medlab.Objects.Test;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Utils.AppUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Test_Directory_Main extends Fragment {


    Dialog Progress;
    RecyclerView recycle;
    java.util.List<Test> List;

    public Test_Directory_Main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test__directory__main, container, false);
        recycle = (RecyclerView) view.findViewById(R.id.recycle);
        Progress = AppUtils.LoadingSpinner(getActivity());
        view.findViewById(R.id.Click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        LoadData();

        return view;
    }


    public void LoadData() {
        Progress.dismiss();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        Test_Adapter Adapter = new Test_Adapter(List, getActivity());
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(Adapter);
    }


    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

}
