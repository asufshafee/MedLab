package com.webmarke8.app.medlab.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webmarke8.app.medlab.Objects.News_Object;
import com.webmarke8.app.medlab.Objects.Tips_Object;
import com.webmarke8.app.medlab.R;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Tips_Adapter extends RecyclerView.Adapter<Tips_Adapter.MyHolder> {
    List<Tips_Object> MyMessageList;
    Context context;

    public Tips_Adapter(List<Tips_Object> list, Context context) {
        this.MyMessageList = list;
        this.context = context;
    }

    @Override
    public Tips_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_tips, parent, false);
        Tips_Adapter.MyHolder myHoder = new Tips_Adapter.MyHolder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(Tips_Adapter.MyHolder holder, final int position) {

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