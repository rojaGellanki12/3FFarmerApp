package com.calibrage.a3ffarmerapp.Adapters;



/**
 * Created by system2 on 24/1/17.
 */
import android.content.Context;
import android.media.Image;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.calibrage.a3ffarmerapp.Activities.CustomVolleyRequest;
import com.calibrage.a3ffarmerapp.Model.SuperHeroes;
import com.calibrage.a3ffarmerapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 11/9/2015.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;

    //List of superHeroes
    List<SuperHeroes> superHeroes;

    public CardAdapter(List<SuperHeroes> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_kz_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SuperHeroes superHero =  superHeroes.get(position);

    /*    imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(superHero.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));*/

     //    holder.imageView.setImageUrl(superHero.getImageUrl(), imageLoader);
    //    holder.imageView.setImageDrawable(superHero.getImageId(), imageLoader);

        holder.imageView.setImageResource(superHero.getImageId());
        holder.textViewName.setText(superHero.getName());
        //   holder.textViewRank.setText(String.valueOf(superHero.getRank()));
   //     holder.textViewRealName.setText(superHero.getRealName());
        //  holder.textViewCreatedBy.setText(superHero.getCreatedBy());
        //  holder.textViewFirstAppearance.setText(superHero.getFirstAppearance());

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
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewRank;
        public TextView textViewRealName;
        public TextView textViewCreatedBy;
        public TextView textViewFirstAppearance;
        public TextView  textViewPowers;

        public ViewHolder(View itemView) {
            super(itemView);
           imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewName = (TextView) itemView.findViewById(R.id.text);
            textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);
           /* textViewRank= (TextView) itemView.findViewById(R.id.textViewRank);
            textViewRealName= (TextView) itemView.findViewById(R.id.textViewRealName);
            textViewCreatedBy= (TextView) itemView.findViewById(R.id.textViewCreatedBy);
            textViewFirstAppearance= (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
            textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);*/
        }
    }
}
