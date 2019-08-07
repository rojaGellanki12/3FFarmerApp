package com.calibrage.a3ffarmerapp.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.calibrage.a3ffarmerapp.Activities.RecommendationActivity;
import com.calibrage.a3ffarmerapp.Activities.RecommendationListActivity;
import com.calibrage.a3ffarmerapp.Model.Album;
import com.calibrage.a3ffarmerapp.Model.MyReqModel;
import com.calibrage.a3ffarmerapp.Model.RecommendationModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.HashMap;
import java.util.List;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MyReqAdapter extends RecyclerView.Adapter<MyReqAdapter.ViewHolder>{
    private MyReqModel[] listdata;
    public Context mContext;

    // RecyclerView recyclerView;
    public MyReqAdapter(Context ctx,MyReqModel[] listdata) {
        this.listdata = listdata;
        this.mContext=ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.my_req_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyReqModel myListData = listdata[position];

        //      holder.imageView.setImageResource(listdata[position].getImgId());

        holder.txtPlotId.setText(listdata[position].getPlotId()+"");
        holder.txtDate.setText(listdata[position].getDate());
        holder.txtTime.setText(listdata[position].getTime()+"");
        holder.txtComments.setText(listdata[position].getComments()+"");
        holder.txtDateNTime.setText(listdata[position].getDateNTime()+"");
        holder.txtReqDate.setText(listdata[position].getReqDate()+"");
        holder.txtApproveDate.setText(listdata[position].getApproveDate()+"");
        holder.txtStatus.setText(listdata[position].getStatus()+"");
        holder.txtname.setText(listdata[position].getName()+"");
        holder.txtMobileNo.setText(listdata[position].getMobileNumber()+"");
        holder.txtPin.setText(listdata[position].getPin()+"");
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {



        public TextView txtPlotId;
        public TextView txtDate;
        public TextView txtTime;
        public TextView txtComments;
        public TextView txtDateNTime;
        public TextView txtReqDate;
        public TextView txtApproveDate;
        public TextView txtStatus;
        public TextView txtname;
        public TextView txtMobileNo;
        public TextView txtPin;


        public ViewHolder(View itemView) {
            super(itemView);



            txtPlotId = itemView.findViewById(R.id.plotId);
            txtDate = itemView.findViewById(R.id.date);
            txtTime = itemView.findViewById(R.id.time);
            txtComments = itemView.findViewById(R.id.comments);
            txtDateNTime = itemView.findViewById(R.id.dateNTime);
            txtReqDate = itemView.findViewById(R.id.reqDate);
            txtApproveDate = itemView.findViewById(R.id.approveDate);
            txtStatus = itemView.findViewById(R.id.status);
            txtname = itemView.findViewById(R.id.name);
            txtMobileNo = itemView.findViewById(R.id.mobileNo);
            txtPin = itemView.findViewById(R.id.pin);


        }


    }
}



