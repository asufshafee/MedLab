package com.webmarke8.app.medlab.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webmarke8.app.medlab.Fragments.Lab_Locations_Details;
import com.webmarke8.app.medlab.Fragments.Test_Directory_Details;
import com.webmarke8.app.medlab.Objects.Test;
import com.webmarke8.app.medlab.Objects.Tips_Object;
import com.webmarke8.app.medlab.R;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Test_Adapter extends RecyclerView.Adapter<Test_Adapter.MyHolder> {
    List<Test> MyMessageList;
    Context context;

    public Test_Adapter(List<Test> list, Context context) {
        this.MyMessageList = list;
        this.context = context;
    }

    @Override
    public Test_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_test, parent, false);
        Test_Adapter.MyHolder myHoder = new Test_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Test_Adapter.MyHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = Test_Directory_Details.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment, "Test Directory").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyHolder extends RecyclerView.ViewHolder {


        public MyHolder(View itemView) {
            super(itemView);

        }
    }

}