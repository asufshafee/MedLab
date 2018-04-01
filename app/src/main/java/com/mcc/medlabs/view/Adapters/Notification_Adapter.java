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
import android.widget.TextView;

import com.mcc.medlabs.view.Fragments.Notifications_Details;
import com.mcc.medlabs.view.Objects.Notification;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.MyHolder> {
    List<Notification.RespOBJObject> List;
    Context context;
    MyApplication myApplication;

    public Notification_Adapter(List<Notification.RespOBJObject> list, Context context) {
        this.List = list;
        this.context = context;
        myApplication = (MyApplication) context.getApplicationContext();
    }

    @Override
    public Notification_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        Notification_Adapter.MyHolder myHoder = new Notification_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Notification_Adapter.MyHolder holder, final int position) {


        if (myApplication.GetLanguage().equals("en")) {
        }
        holder.Name.setText(List.get(position).getText());
        holder.Date.setText(List.get(position).getDate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = Notifications_Details.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", List.get(position));
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment, "Notification").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

            }
        });

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

        TextView Date, Name;

        public MyHolder(View itemView) {
            super(itemView);
            Date = (TextView) itemView.findViewById(R.id.Date);
            Name = (TextView) itemView.findViewById(R.id.Name);


        }
    }

}