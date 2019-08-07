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

import com.calibrage.a3ffarmerapp.Model.RecommendationListModel;

import com.calibrage.a3ffarmerapp.R;




public class RecommendationListAdapter extends RecyclerView.Adapter<RecommendationListAdapter.ViewHolder>{
    private RecommendationListModel[] listdata;
    public Context mContext;

    // RecyclerView recyclerView;
    public RecommendationListAdapter(Context ctx,RecommendationListModel[] listdata) {
        this.listdata = listdata;
        this.mContext=ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recommended_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RecommendationListModel myListData = listdata[position];
        holder.txtPlotCode.setText(listdata[position].getPlotCode());
        holder.txtRecommendedChemical.setText(listdata[position].getRecommendedChemical());
        holder.txtDosage.setText(listdata[position].getDosage());
        holder.txtComments.setText(listdata[position].getComments());
        holder.txtIssue.setText(listdata[position].getIssue());
        holder.txtRecommendedBy.setText(listdata[position].getRecommendedBy());
        holder.txtRecommendedOn.setText(listdata[position].getRecommendedOn());


    /*    holder.card_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               *//* Intent intent = new Intent(mContext, RecommendationListActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);*//*

            }

        });*/
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public  CardView card_view;
        public TextView txtPlotCode;
        public TextView txtRecommendedChemical;
        public TextView txtDosage;
        public TextView txtComments;
        public TextView txtIssue;
        public TextView txtRecommendedBy;
        public TextView txtRecommendedOn;

        public ViewHolder(View itemView) {
            super(itemView);
            txtPlotCode = itemView.findViewById(R.id.plotCode);
            txtRecommendedChemical = itemView.findViewById(R.id.recommendedChemical);
            txtComments = itemView.findViewById(R.id.comments);
            txtDosage = itemView.findViewById(R.id.dosage);
            txtIssue = itemView.findViewById(R.id.issue);
            txtRecommendedBy = itemView.findViewById(R.id.recommendedBy);
            txtRecommendedOn = itemView.findViewById(R.id.recommendedOn);
      //      this.card_view =  (CardView) itemView.findViewById(R.id.card_view);

        }


    }
}

