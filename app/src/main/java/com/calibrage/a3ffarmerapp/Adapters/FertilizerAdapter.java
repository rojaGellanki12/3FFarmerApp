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
import com.calibrage.a3ffarmerapp.Model.FertilizerModel;
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


public class FertilizerAdapter extends RecyclerView.Adapter<FertilizerAdapter.ViewHolder>{
    private FertilizerModel[] listdata;
    public Context mContext;

    // RecyclerView recyclerView;
    public FertilizerAdapter(Context ctx,FertilizerModel[] listdata) {
        this.listdata = listdata;
        this.mContext=ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.fertilizer_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FertilizerModel myListData = listdata[position];
        holder.txtPlotCode.setText(listdata[position].getPlotCode());
        holder.txtAge.setText(listdata[position].getAge());
        holder.txtSize.setText(listdata[position].getSize());
        holder.txtVillage.setText(listdata[position].getVillage());
        holder.txtLandmark.setText(listdata[position].getLandmark());
        holder.txtFertilizer1.setText(listdata[position].getFertilizer1());
        holder.txtFertilizer2.setText(listdata[position].getFertilizer2());
        holder.txtFertilizer3.setText(listdata[position].getFertilizer3());
        //      holder.imageView.setImageResource(listdata[position].getImgId());

    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView txtPlotCode;
        public TextView txtAge;
        public TextView txtSize;
        public TextView txtVillage;
        public TextView txtLandmark;
        public TextView txtFertilizer1;
        public TextView txtFertilizer2;
        public TextView txtFertilizer3;

        public ViewHolder(View itemView) {
            super(itemView);

            txtPlotCode = itemView.findViewById(R.id.plot_code);
            txtAge = itemView.findViewById(R.id.age);
            txtSize = itemView.findViewById(R.id.size);
            txtVillage = itemView.findViewById(R.id.village);
            txtLandmark = itemView.findViewById(R.id.landmark);

            txtFertilizer1 = itemView.findViewById(R.id.fertilizer1);
            txtFertilizer2 = itemView.findViewById(R.id.fertilizer2);
            txtFertilizer3 = itemView.findViewById(R.id.fertilizer3);

        }


    }
}


