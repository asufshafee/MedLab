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

import com.webmarke8.app.medlab.Fragments.Test_Directory_Details;
import com.webmarke8.app.medlab.Fragments.Test_Result_Screen_2;
import com.webmarke8.app.medlab.Objects.JsonParserLogin;
import com.webmarke8.app.medlab.Objects.JsonParserVisited;
import com.webmarke8.app.medlab.Objects.Login_Object;
import com.webmarke8.app.medlab.Objects.Test;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.GlobalActions;
import com.webmarke8.app.medlab.Session.MyApplication;
import com.webmarke8.app.medlab.Session.SharedPrefrenceKeys;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Test_Result_Adapter extends RecyclerView.Adapter<Test_Result_Adapter.MyHolder> {
    List<Login_Object.PatientsObject> List;
    Context context;
    MyApplication myApplication;

    public Test_Result_Adapter(List<Login_Object.PatientsObject> list, Context context) {
        this.List = list;
        this.context = context;
        myApplication = (MyApplication) context.getApplicationContext();
    }

    @Override
    public Test_Result_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_test_result, parent, false);
        Test_Result_Adapter.MyHolder myHoder = new Test_Result_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Test_Result_Adapter.MyHolder holder, final int position) {


        holder.FileName.setText(List.get(position).getFileNo());
        holder.Name.setText(List.get(position).getP_Name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonParserVisited jsonParserVisited = new JsonParserVisited();
                JsonParserVisited.DeviceObject deviceObject = new JsonParserVisited.DeviceObject();
                jsonParserVisited.setToken(myApplication.getLoginSessionLogin().getToken());
                jsonParserVisited.setUserId(List.get(position).getFileNo());
                jsonParserVisited.setWSPassword("Medl@p$app17");
                jsonParserVisited.setWSUsername("Medlabs");

                deviceObject.setPlatform("Android");
                deviceObject.setResolution(myApplication.RESOLUATION);
                deviceObject.setVersion(myApplication.APP_VERSION);
                jsonParserVisited.setDevice(deviceObject);

                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = Test_Result_Screen_2.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("jsonParserVisited", jsonParserVisited);
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

        TextView Name, FileName;

        public MyHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.Name);
            FileName = (TextView) itemView.findViewById(R.id.FileName);


        }
    }

}