package com.calibrage.a3ffarmerapp.Activities;

/**
 * Created on : Jan 21, 2019
 * Author     : AndroidWave
 * Email    : info@androidwave.com
 */
public interface CallBacks {

    void callbackObserver(Object obj);

    public interface playerCallBack {
        void onItemClickOnItem(Integer albumId);

        void onPlayingEnd();
    }
}
