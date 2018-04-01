package com.mcc.medlabs.view.Adapters;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.mcc.medlabs.view.Fragments.Sehtak_Bill_Screen_1;
import com.mcc.medlabs.view.Fragments.Sehtak_Gift_Voucher;
import com.mcc.medlabs.view.Objects.Shakha;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Shekha_Adapter extends RecyclerView.Adapter<Shekha_Adapter.MyHolder> {
    List<Shakha.SahtakBilDeniaObObject> List;
    Context context;
    MyApplication myApplication;


    public Shekha_Adapter(List<Shakha.SahtakBilDeniaObObject> list, Context context) {
        this.List = list;
        myApplication = (MyApplication) context.getApplicationContext();
        this.context = context;
    }

    @Override
    public Shekha_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_shekha, parent, false);
        Shekha_Adapter.MyHolder myHoder = new Shekha_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Shekha_Adapter.MyHolder holder, final int position) {


        if (!List.get(position).getId().equals("no")) {
            if (myApplication.GetLanguage().equals("en")) {
                holder.Name.setText(List.get(position).getProgramName());
            } else {
                holder.Name.setText(List.get(position).getProgramNameAr());
            }
            if (!List.get(position).getImage().equals("")) {
                Picasso.with(context).load(List.get(position).getImage()).into(holder.imageView);

            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment fragment = null;
                    Class fragmentClass = null;

                    fragmentClass = Sehtak_Bill_Screen_1.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", List.get(position));
                        fragment.setArguments(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    if (myApplication.GetLanguage().equals("en"))
                        fragmentManager.beginTransaction().replace(R.id.container, fragment, List.get(position).getProgramName()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
                    else
                        fragmentManager.beginTransaction().replace(R.id.container, fragment, List.get(position).getProgramNameAr()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

                }
            });
        } else {
            if (myApplication.GetLanguage().equals("en")) {
                holder.Checkups.setText(List.get(position).getDescription());
                holder.Name.setText(List.get(position).getProgramName());
            } else {
                holder.Checkups.setText(List.get(position).getDescriptionAr());
                holder.Name.setText(List.get(position).getProgramNameAr());
            }
//            if (!List.get(position).getImage().equals("")) {
//                Picasso.with(context).load(List.get(position).getImage()).into(holder.imageView);
//
//            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = null;
                    Class fragmentClass = null;

                    fragmentClass = Sehtak_Gift_Voucher.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                        Bundle bundle = new Bundle();
                        fragment.setArguments(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment, "Sehtak Gift Voucher").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();


                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView Name, Checkups;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);

            Name = (TextView) itemView.findViewById(R.id.Name);
            Checkups = (TextView) itemView.findViewById(R.id.Checkups);
            imageView = (ImageView) itemView.findViewById(R.id.Image);
        }
    }

}