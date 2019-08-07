package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.calibrage.a3ffarmerapp.Model.Album;
import com.calibrage.a3ffarmerapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;






public  class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> orders;
    int minteger = 0;
    HashMap<String,Integer> positiveNumbers = new HashMap<String,Integer>() ;

    HashMap<String,Integer> rs = new HashMap<String,Integer>() ;
    Integer man=0;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView currentFoodName,
                currentCost,
                quantityText,
        actual_amt,
                removeMeal;
        ImageView addMeal,subtractMeal;
        public ImageView thumbnail;
        public TextView disc,size;


        public MyViewHolder(View view) {
            super(view);

            currentFoodName = (TextView) view.findViewById(R.id.selected_food_name);
            currentCost = (TextView) view.findViewById(R.id.selected_food_amount);
            subtractMeal = (ImageView) view.findViewById(R.id.minus_meal);
            quantityText = (TextView) view.findViewById(R.id.quantity);
            addMeal = (ImageView) view.findViewById(R.id.plus_meal);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            actual_amt = (TextView) view.findViewById(R.id.actual_amt);
            disc = (TextView) view.findViewById(
                    R.id.desc);
            size = (TextView) view.findViewById(
                    R.id.size);
            //  removeMeal = (TextView) view.findViewById(R.id.delete_item);


        }
    }
    public AlbumsAdapter(Context mContext, List<Album> orders) {
        this.mContext = mContext;
        this.orders = orders;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Album currentFood = orders.get(position);

        holder.currentFoodName.setText(currentFood.getmName());
        holder.currentCost.setText(mContext.getString(R.string.Rs)+ (currentFood.getmAmount() * currentFood.getmQuantity()));
        holder.quantityText.setText(""+ currentFood.getmQuantity());

        holder.disc.setText(currentFood.getDisc());
        holder.size.setText(currentFood.getSize());
        //   holder.count.setText(album.getNumOfSongs() + " songs");

        holder.actual_amt.setPaintFlags(holder.actual_amt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        // loading album cover using Glide library
        Glide.with(mContext).load(currentFood.getThumbnail()).into(holder.thumbnail);

        //OnClick listeners for all the buttons on the ListView Item
        holder.addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFood.addToQuantity();
                holder.quantityText.setText("x "+ currentFood.getmQuantity());
                holder.currentCost.setText("GH"+ (currentFood.getmAmount() * currentFood.getmQuantity()));
                notifyDataSetChanged();
            }
        });

        holder.subtractMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFood.removeFromQuantity();
                holder. quantityText.setText("x "+currentFood.getmQuantity());
                holder.currentCost.setText("GH"+ (currentFood.getmAmount() * currentFood.getmQuantity()));
                notifyDataSetChanged();
            }
        });

       /* holder.removeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orders.remove(position);
                notifyDataSetChanged();
            }
        });*/

        // loading album cover using Glide library
        //  Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);



    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}


