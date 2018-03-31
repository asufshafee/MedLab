package com.webmarke8.app.medlab.Adapters;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webmarke8.app.medlab.Fragments.Lab_Locations_Details;
import com.webmarke8.app.medlab.Objects.Locations;
import com.webmarke8.app.medlab.Objects.News_Object;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class News_Adapter extends RecyclerView.Adapter<News_Adapter.MyHolder> {
    List<News_Object.NewsObObject> List;
    Context context;
    int Current = 10;
    MyApplication myApplication;

    public News_Adapter(List<News_Object.NewsObObject> list, Context context) {
        this.List = list;
        this.context = context;
        myApplication = (MyApplication) context.getApplicationContext();
    }

    @Override
    public News_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        News_Adapter.MyHolder myHoder = new News_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(News_Adapter.MyHolder holder, final int position) {


        if (position == Current) {
            holder.Add.setVisibility(View.VISIBLE);
            Current = Current + 10;
        } else {
            holder.Add.setVisibility(View.GONE);

        }
        {

            if (myApplication.GetLanguage().equals("en")) {
                holder.Descriotion.setText(List.get(position).getDescription());
                holder.Date.setText(List.get(position).getDate());
                holder.Name.setText(List.get(position).getName());
            } else {
                holder.Descriotion.setText(List.get(position).getDescriptionAr());
                holder.Date.setText(List.get(position).getDate());
                holder.Date.setGravity(Gravity.END);
                holder.Name.setText(List.get(position).getNameAr());
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


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

        LinearLayout News, Add;
        TextView Name, Date, Descriotion;

        public MyHolder(View itemView) {
            super(itemView);
            News = (LinearLayout) itemView.findViewById(R.id.News);
            Add = (LinearLayout) itemView.findViewById(R.id.Add);
            Name = (TextView) itemView.findViewById(R.id.Name);
            Date = (TextView) itemView.findViewById(R.id.Date);
            Descriotion = (TextView) itemView.findViewById(R.id.Description);


        }
    }

}