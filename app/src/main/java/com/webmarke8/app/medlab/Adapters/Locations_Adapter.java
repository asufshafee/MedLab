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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webmarke8.app.medlab.Fragments.Lab_Locations_Details;
import com.webmarke8.app.medlab.Objects.Locations;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Locations_Adapter extends RecyclerView.Adapter<Locations_Adapter.MyHolder> {
    List<Locations.BranchObObject> List;
    Context context;
    MyApplication myApplication;

    public Locations_Adapter(List<Locations.BranchObObject> list, Context context) {
        this.List = list;
        this.context = context;
        myApplication = (MyApplication) context.getApplicationContext();
    }

    @Override
    public Locations_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_locations, parent, false);
        Locations_Adapter.MyHolder myHoder = new Locations_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Locations_Adapter.MyHolder holder, final int position) {


        if (myApplication.GetLanguage().equals("en")) {
            holder.Name.setText(List.get(position).getBranch());
            holder.Address.setText(List.get(position).getAddress());
        } else {

            holder.Name.setText(List.get(position).getBranchAr());
            holder.Address.setText(List.get(position).getAddressAr());
        }

        Picasso.with(context).load(List.get(position).getImage()).into(holder.Image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = Lab_Locations_Details.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Branch", List.get(position));
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

        TextView Name, Address;
        ImageView Image;


        public MyHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.Name);
            Address = (TextView) itemView.findViewById(R.id.Address);
            Image = (ImageView) itemView.findViewById(R.id.Image);


        }
    }

}