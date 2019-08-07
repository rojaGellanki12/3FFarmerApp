package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Model.GetRecommendationsByAgeModel;
import com.calibrage.a3ffarmerapp.Model.GetRecommendationsModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;



public class GetRecommendationsByAgeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    //List of superHeroes
    List<GetRecommendationsByAgeModel> superHeroes;

    public GetRecommendationsByAgeAdapter(List<GetRecommendationsByAgeModel> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_recommendations_by_age_list, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.get_recommendations_by_age_list, parent, false);
//        ViewHolder viewHolder = new ViewHolder(v);
        //return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof ItemViewHolder) {

            populateItemRows((ItemViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }





    }

    @Override
    public int getItemCount() {
        return superHeroes == null ? 0 : superHeroes.size();
    }

    public int getItemViewType(int position) {
        return superHeroes.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

//    class ViewHolder extends RecyclerView.ViewHolder{
//
//        public TextView fertilizer;
//        public TextView uoM;
//        public TextView year;
//
//        public TextView  remarks,textViewPowers;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            fertilizer = (TextView) itemView.findViewById(R.id.fertilizer);
//            uoM= (TextView) itemView.findViewById(R.id.uoM);
//            year= (TextView) itemView.findViewById(R.id.year);
//            remarks= (TextView) itemView.findViewById(R.id.remarks);
//
//
//          //  textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);
//        }
//    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView fertilizer;
        public TextView uoM;
        public TextView year;

        public TextView  remarks,textViewPowers;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            fertilizer = (TextView) itemView.findViewById(R.id.fertilizer);
            uoM= (TextView) itemView.findViewById(R.id.uoM);
            year= (TextView) itemView.findViewById(R.id.year);
            remarks= (TextView) itemView.findViewById(R.id.remarks);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }


    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder holder, int position) {
        GetRecommendationsByAgeModel superHero =  superHeroes.get(position);
        if(superHero!=null){
            holder.fertilizer.setText(superHero.getFertilizer());
            holder.uoM.setText(superHero.getUoM());
            holder.year.setText(superHero.getYear());
            holder.remarks.setText(superHero.getRemarks());
            String powers = "";
        }


    }
}