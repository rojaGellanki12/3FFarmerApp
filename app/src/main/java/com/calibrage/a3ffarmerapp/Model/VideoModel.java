package com.calibrage.a3ffarmerapp.Model;

/**
 * Created on : Jan 24, 2019
 * Author     : AndroidWave
 * Email    : info@androidwave.com
 */
public class VideoModel {
    private String mFilePath, mVideoThumb;
    private boolean isSelected;

    public String getFilePath() {
        return mFilePath;
    }

    public void setFilePath(String mFilePath) {
        this.mFilePath = mFilePath;
    }

    public String getVideoThumb() {
        return mVideoThumb;
    }

    public void setVideoThumb(String mVideoThumb) {
        this.mVideoThumb = mVideoThumb;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
