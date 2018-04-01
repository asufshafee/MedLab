package com.mcc.medlabs.view.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcc.medlabs.view.Objects.Tips_Object;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class Tips_Adapter extends RecyclerView.Adapter<Tips_Adapter.MyHolder> {
    List<Tips_Object.TipsObObject> List;
    Context context;
    TextView Date, Name, Details;
    MyApplication myApplication;


    public Tips_Adapter(List<Tips_Object.TipsObObject> list, Context context, TextView Name, TextView Details, TextView Date) {
        this.List = list;
        this.Name = Name;
        this.Date = Date;
        this.Details = Details;
        myApplication = (MyApplication) context.getApplicationContext();
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


        if (myApplication.GetLanguage().equals("en")) {
            holder.Date.setText(List.get(position).getDate());
            holder.Name.setText(List.get(position).getTitle());
        } else {
            holder.Date.setText(List.get(position).getDate());
            holder.Name.setText(List.get(position).getTextAr());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myApplication.GetLanguage().equals("en")) {
                    Date.setText(List.get(position).getDate());
                    Details.setText(List.get(position).getText());
                    Name.setText(List.get(position).getTitle());
                } else {
                    Date.setText(List.get(position).getDate());
                    Details.setText(List.get(position).getTextAr());
                    Name.setText(List.get(position).getTextAr());
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

        TextView Date, Name;

        public MyHolder(View itemView) {
            super(itemView);

            Date = (TextView) itemView.findViewById(R.id.Date);
            Name = (TextView) itemView.findViewById(R.id.Name);
        }
    }

}