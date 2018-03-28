package com.webmarke8.app.medlab.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webmarke8.app.medlab.Objects.Featured_Test;
import com.webmarke8.app.medlab.Objects.Tips_Object;
import com.webmarke8.app.medlab.R;
import com.webmarke8.app.medlab.Session.MyApplication;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Featered_Test_Adapter extends RecyclerView.Adapter<Featered_Test_Adapter.MyHolder> {
    List<Featured_Test.SahtakBilDeniaObObject> List;
    Context context;
    MyApplication myApplication;


    public Featered_Test_Adapter(List<Featured_Test.SahtakBilDeniaObObject> list, Context context) {
        this.List = list;
        myApplication = (MyApplication) context.getApplicationContext();
        this.context = context;
    }

    @Override
    public Featered_Test_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_featured_test, parent, false);
        Featered_Test_Adapter.MyHolder myHoder = new Featered_Test_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Featered_Test_Adapter.MyHolder holder, final int position) {


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

        TextView Name;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);

            Name = (TextView) itemView.findViewById(R.id.Name);
            imageView = (ImageView) itemView.findViewById(R.id.Image);
        }
    }

}