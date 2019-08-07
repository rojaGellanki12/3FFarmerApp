package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Model.Album;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;

public class KnowledgeZoneCategoryAdapter extends RecyclerView.Adapter<KnowledgeZoneCategoryAdapter.ViewHolder>{
    private List<Album> orders;
    private Context mContext;
    public KnowledgeZoneCategoryAdapter(Context mContext,List<Album> orders){
        this.mContext = mContext;
        this.orders = orders;

    }


    @NonNull
    @Override
    public KnowledgeZoneCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_kz_home, parent, false);

        return new KnowledgeZoneCategoryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KnowledgeZoneCategoryAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(orders.get(holder.getAdapterPosition()).getThumbnail());
        holder.textView.setText(orders.get(holder.getAdapterPosition()).getmAmount());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
