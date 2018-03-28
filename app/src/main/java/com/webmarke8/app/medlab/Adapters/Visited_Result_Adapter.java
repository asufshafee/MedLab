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
import android.widget.TextView;

import com.webmarke8.app.medlab.Fragments.Test_Result_Screen_2;
import com.webmarke8.app.medlab.Fragments.Test_Result_Screen_3;
import com.webmarke8.app.medlab.Objects.JsonParserVisited;
import com.webmarke8.app.medlab.Objects.Login_Object;
import com.webmarke8.app.medlab.Objects.Visited_Object;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                JsonParserVisited jsonParserVisited = new JsonParserVisited();

//                JsonParserVisited.DeviceObject deviceObject = new JsonParserVisited.DeviceObject();
//                jsonParserVisited.setToken(myApplication.getLoginSessionLogin().getToken());
//                jsonParserVisited.setUserId(List.get(position).getFileNo());
//                jsonParserVisited.setWSPassword("Medl@p$app17");
//                jsonParserVisited.setWSUsername("Medlabs");
//
//                deviceObject.setPlatform("Android");
//                deviceObject.setResolution(myApplication.RESOLUATION);
//                deviceObject.setVersion(myApplication.APP_VERSION);
//                jsonParserVisited.setDevice(deviceObject);

//                String FileNo, String VisitedID
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

        public MyHolder(View itemView) {
            super(itemView);
            Status = (TextView) itemView.findViewById(R.id.Status);
            Date = (TextView) itemView.findViewById(R.id.Date);


        }
    }

}