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
import com.webmarke8.app.medlab.Objects.Locations;
import com.webmarke8.app.medlab.Objects.News_Object;
import com.webmarke8.app.medlab.R;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class News_Adapter extends RecyclerView.Adapter<News_Adapter.MyHolder> {
    List<News_Object> MyMessageList;
    Context context;

    public News_Adapter(List<News_Object> list, Context context) {
        this.MyMessageList = list;
        this.context = context;
    }

    @Override
    public News_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        News_Adapter.MyHolder myHoder = new News_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(News_Adapter.MyHolder holder, final int position) {

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