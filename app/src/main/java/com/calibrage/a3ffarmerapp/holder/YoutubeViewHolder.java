package com.calibrage.a3ffarmerapp.holder;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.R;
import com.google.android.youtube.player.YouTubeThumbnailView;

/**
 * Created by sonu on 10/11/17.
 */

public class YoutubeViewHolder extends RecyclerView.ViewHolder {

    public YouTubeThumbnailView videoThumbnailImageView;
    public TextView videoTitle, videoDuration;

    public YoutubeViewHolder(View itemView) {
        super(itemView);
        videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
       videoTitle = itemView.findViewById(R.id.video_title);
       // videoDuration = itemView.findViewById(R.id.video_title_label);
    }
}
