package com.webmarke8.app.medlab.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webmarke8.app.medlab.Fragments.Lab_Locations_Details;
import com.webmarke8.app.medlab.Objects.Locations;
import com.webmarke8.app.medlab.R;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Locations_Adapter extends RecyclerView.Adapter<Locations_Adapter.MyHolder> {
    List<Locations> MyMessageList;
    Context context;

    public Locations_Adapter(List<Locations> list, Context context) {
        this.MyMessageList = list;
        this.context = context;
    }

    @Override
    public Locations_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_locations, parent, false);
        Locations_Adapter.MyHolder myHoder = new Locations_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Locations_Adapter.MyHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = Lab_Locations_Details.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment, "Lab_Locations_Details").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

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