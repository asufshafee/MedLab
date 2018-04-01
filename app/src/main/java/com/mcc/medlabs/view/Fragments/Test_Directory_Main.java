package com.mcc.medlabs.view.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;

import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Adapters.Test_Adapter;
import com.mcc.medlabs.view.DatabasePart.LabwiseBean;
import com.mcc.medlabs.view.DatabasePart.MedlabsDelegate;
import com.mcc.medlabs.view.Objects.Test;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Test_Directory_Main extends Fragment {


    Dialog Progress;
    RecyclerView recycle;
    java.util.List<Test> List;
    private MedlabsDelegate delegate;
    private ArrayList<LabwiseBean> labwiseList;
    Test_Adapter Adapter;
    MyApplication myApplication;


    public Test_Directory_Main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test__directory__main, container, false);
        recycle = (RecyclerView) view.findViewById(R.id.recycle);

        myApplication = (MyApplication) getActivity().getApplicationContext();
//        ((MainActivity) getActivity()).Change_Tittle("Test Directory");

        if (myApplication.GetLanguage().equals("ar")) {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Test_Directory));
        } else {
            ((MainActivity) getActivity()).Change_Tittle("Test Directory");
        }

        delegate = new MedlabsDelegate();
        labwiseList = new ArrayList<>();
        Progress = AppUtils.LoadingSpinner(getActivity());
        try {
            labwiseList = delegate.getLabwiseList(getActivity());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        view.findViewById(R.id.Click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });

        final EditText Search = (EditText) view.findViewById(R.id.Search);
        Search.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                labwiseList = new ArrayList<>();
                Progress.show();
                if (!Search.getText().equals("")) {
                    try {
                        labwiseList = delegate.getLabwiseListAfterSearch(getActivity(), Search.getText().toString());
                        LoadData();
                        Progress.dismiss();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        labwiseList = delegate.getLabwiseList(getActivity());
                        LoadData();
                        Progress.dismiss();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });

        LoadData();

        return view;
    }


    public void LoadData() {
        Progress.dismiss();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        Adapter = new Test_Adapter(labwiseList, getActivity());
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(Adapter);
    }


    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

}
