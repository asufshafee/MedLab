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

import com.mcc.medlabs.view.Fragments.Test_Result_Screen_3;
import com.mcc.medlabs.view.Objects.Visited_Object;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.medialablk.easytoast.EasyToast;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Visited_Result_Adapter extends RecyclerView.Adapter<Visited_Result_Adapter.MyHolder> {
    List<Visited_Object.RespOBJObject> List;
    Context context;
    MyApplication myApplication;
    Visited_Object visited_object;

    public Visited_Result_Adapter(List<Visited_Object.RespOBJObject> list, Context context, Visited_Object visited_object) {
        this.List = list;
        this.context = context;
        this.visited_object = visited_object;
        myApplication = (MyApplication) context.getApplicationContext();
    }

    @Override
    public Visited_Result_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_visited, parent, false);
        Visited_Result_Adapter.MyHolder myHoder = new Visited_Result_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Visited_Result_Adapter.MyHolder holder, final int position) {


        holder.Date.setText(List.get(position).getVisitDate());
        holder.Status.setText(List.get(position).getStatus());

        if (!myApplication.GetLanguage().equals("en"))
            holder.Arrow.setRotationY(180);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (List.get(position).isIsPaid()) {
                    Fragment fragment = null;
                    Class fragmentClass = null;

                    fragmentClass = Test_Result_Screen_3.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                        Bundle bundle = new Bundle();
                        bundle.putString("FileNo", visited_object.getFileNo());
                        bundle.putString("VisitedID", List.get(position).getVisitId());
                        bundle.putString("Date", List.get(position).getVisitDate());
                        fragment.setArguments(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment, "Test Results").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
                } else {
                    EasyToast.warning(context, context.getString(R.string.TEST_RESULTS_YOU_ARE_NOT_PAYING));
                }

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

        TextView Status, Date;
        ImageView Arrow;

        public MyHolder(View itemView) {
            super(itemView);

            Arrow = (ImageView) itemView.findViewById(R.id.Arrow);
            Status = (TextView) itemView.findViewById(R.id.Status);
            Date = (TextView) itemView.findViewById(R.id.Date);


        }
    }

}