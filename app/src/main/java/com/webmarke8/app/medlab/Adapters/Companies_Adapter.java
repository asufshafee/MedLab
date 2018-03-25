package com.webmarke8.app.medlab.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webmarke8.app.medlab.Objects.Company;
import com.webmarke8.app.medlab.Objects.News_Object;
import com.webmarke8.app.medlab.R;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Companies_Adapter extends RecyclerView.Adapter<Companies_Adapter.MyHolder> {
    List<Company> MyMessageList;
    Context context;

    public Companies_Adapter(List<Company> list, Context context) {
        this.MyMessageList = list;
        this.context = context;
    }

    @Override
    public Companies_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false);
        Companies_Adapter.MyHolder myHoder = new Companies_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Companies_Adapter.MyHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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