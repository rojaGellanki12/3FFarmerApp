package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Model.GetRecommendationsModel;
import com.calibrage.a3ffarmerapp.Model.SuperHeroes;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;

public class GetRecommendationsAdapter extends RecyclerView.Adapter<GetRecommendationsAdapter.ViewHolder> {


    private Context context;

    //List of superHeroes
    List<GetRecommendationsModel> superHeroes;

    public GetRecommendationsAdapter(List<GetRecommendationsModel> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.get_recommendations_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GetRecommendationsModel superHero =  superHeroes.get(position);

        holder.fertilizer.setText(superHero.getFertilizer());
         holder.uoM.setText(superHero.getUoM());
         holder.year1.setText(superHero.getYear1());
         holder.year17Above.setText(superHero.getYear17Above());
         holder.year2.setText(superHero.getYear2());
        holder.year3.setText(superHero.getYear3());
        holder.year4And5.setText(superHero.getYear4And5());
        holder.year6And7.setText(superHero.getYear6And7());
      //  holder.year8To17.setText(superHero.getYear8To17());
     //   holder.remarks.setText(superHero.getRemarks());
        String powers = "";

        for(int i = 0; i<superHero.getPowers().size(); i++){
            powers+= superHero.getPowers().get(i);
        }

        holder.textViewPowers.setText(powers);
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView fertilizer;
        public TextView uoM;
        public TextView year1;
        public TextView year17Above;
        public TextView year2;
        public TextView  year3;
        public TextView year4And5;
        public TextView year6And7;
        public TextView year8To17;
        public TextView  remarks,textViewPowers;

        public ViewHolder(View itemView) {
            super(itemView);

            fertilizer = (TextView) itemView.findViewById(R.id.fertilizer);
            uoM= (TextView) itemView.findViewById(R.id.uoM);
            year1= (TextView) itemView.findViewById(R.id.year1);
            year17Above= (TextView) itemView.findViewById(R.id.year17Above);
            year2= (TextView) itemView.findViewById(R.id.year2);
            year3 = (TextView) itemView.findViewById(R.id.year3);
            year4And5= (TextView) itemView.findViewById(R.id.year4And5);
            year6And7= (TextView) itemView.findViewById(R.id.year6And7);
         //   year8To17= (TextView) itemView.findViewById(R.id.year8To17);
          //  remarks= (TextView) itemView.findViewById(R.id.remarks);


            textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);
        }
    }
}