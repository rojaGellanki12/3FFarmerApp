package com.calibrage.a3ffarmerapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.calibrage.a3ffarmerapp.R;
import com.google.android.exoplayer2.ui.PlayerView;


public class PlayerActivity extends AppCompatActivity implements CallBacks.playerCallBack {
    String mFilePath = null;
    private static final String FILE_PATH = "PlayerActivity";

    public static Intent getStartIntent(Context context, String mFilePath) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(FILE_PATH, mFilePath);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        if (getIntent().hasExtra(FILE_PATH)) {
            mFilePath = getIntent().getStringExtra(FILE_PATH);
        }
        PlayerView mPlayerView = findViewById(R.id.mPlayerView);
        mPlayerView.setPlayer(PlayerManager.getSharedInstance(PlayerActivity.this).getPlayerView().getPlayer());
        PlayerManager.getSharedInstance(PlayerActivity.this).playStream(mFilePath);
        PlayerManager.getSharedInstance(this).setPlayerListener(this);

        Log.d("PlayerActivity","mFilePath"+mFilePath);
    }


    @Override
    public void onItemClickOnItem(Integer albumId) {

    }

    @Override
    public void onPlayingEnd() {
        PlayerManager.getSharedInstance(PlayerActivity.this).pausePlayer();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        PlayerManager.getSharedInstance(PlayerActivity.this).pausePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PlayerManager.getSharedInstance(PlayerActivity.this).resumePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayerManager.getSharedInstance(PlayerActivity.this).pausePlayer();
    }

}

