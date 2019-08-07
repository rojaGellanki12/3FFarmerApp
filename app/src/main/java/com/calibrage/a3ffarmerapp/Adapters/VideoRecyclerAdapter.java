package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.calibrage.a3ffarmerapp.Activities.PlayerActivity;
import com.calibrage.a3ffarmerapp.Model.VideoModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;

/**
 * Created on : Jan 24, 2019
 * Author     : AndroidWave
 * Email    : info@androidwave.com
 */
public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.VideoViewHolder> {

    private List<VideoModel> videoList;

    public VideoRecyclerAdapter(List<VideoModel> videoList) {
        this.videoList = videoList;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewThumbnail;

        VideoViewHolder(View view) {
            super(view);
            imageViewThumbnail = view.findViewById(R.id.imageViewThumbnail);

        }
    }


    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list_row, parent, false);

        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, int position) {
        final VideoModel mVideo = videoList.get(position);
        Glide.with(holder.itemView.getContext()).load(mVideo.getVideoThumb()).into(holder.imageViewThumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mPlayerIntent = PlayerActivity.getStartIntent(holder.itemView.getContext(), mVideo.getFilePath());
                holder.itemView.getContext().startActivity(mPlayerIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}

