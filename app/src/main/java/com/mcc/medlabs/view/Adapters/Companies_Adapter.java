package com.mcc.medlabs.view.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.mcc.medlabs.view.Objects.Company;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Companies_Adapter extends RecyclerView.Adapter<Companies_Adapter.MyHolder> {
    List<Company.InsuranceCompanyObObject> List;
    Context context;
    MyApplication myApplication;


    public Companies_Adapter(List<Company.InsuranceCompanyObObject> list, Context context) {
        this.List = list;
        this.context = context;
        myApplication = (MyApplication) context.getApplicationContext();

    }

    @Override
    public Companies_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false);
        Companies_Adapter.MyHolder myHoder = new Companies_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Companies_Adapter.MyHolder holder, final int position) {

        if (myApplication.GetLanguage().equals("en")) {
            holder.Name.setText(List.get(position).getName());
            holder.Details.setText("Contact Numbers: " + List.get(position).getPhoneNumber());
        } else {
            holder.Name.setText(List.get(position).getNameAr());
            holder.Details.setText(" ارقام التواصل: " + List.get(position).getPhoneNumber());
        }

        Picasso.with(context).load(List.get(position).getImage()).into(holder.Image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        TextView Name, Details;
        ImageView Image;

        public MyHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.Name);
            Details = (TextView) itemView.findViewById(R.id.Detais);
            Image = (ImageView) itemView.findViewById(R.id.Image);

        }
    }

}