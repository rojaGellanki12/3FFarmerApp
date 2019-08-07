package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.calibrage.a3ffarmerapp.Adapters.PagerAdapter;
import com.calibrage.a3ffarmerapp.Fragments.AudioFragment;
import com.calibrage.a3ffarmerapp.Fragments.PhotoFragment;
import com.calibrage.a3ffarmerapp.Fragments.VideoFragment;
import com.calibrage.a3ffarmerapp.Model.GetLookUpModel;
import com.calibrage.a3ffarmerapp.R;
import com.google.android.material.tabs.TabLayout;


import android.net.Uri;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class EncyclopediaActivity extends AppCompatActivity {

    //create class reference
    VideoView vid;
    //  Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    String   receivingString;
    private OnAboutDataReceivedListener mAboutDataListener;
    PagerAdapter pagerAdapter;
    private ProgressDialog dialog;
    String Id;
    String  titleName;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);
        dialog = new ProgressDialog(EncyclopediaActivity.this);

        SharedPreferences pref1 = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
         Id=pref1.getString("Id", "");       // Saving string data of your editext
        Log.d("Id", "Id ki======" + Id);
        titleName=pref1.getString("name", "");       // Saving string data of your editext
        Log.d("name", "name ki======" + titleName);
        /*vid = (VideoView)findViewById(R.id.videoView);
        vid.setBackgroundResource(R.drawable.play_2);*/
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
       /* toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);*/
        tabLayout = (TabLayout) findViewById(R.id.tab);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            receivingString = extras.getString("Id");

            Log.d("Id ", "Id kiran======" + receivingString);
        } else {
            // handle case
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFrag(new VideoFragment(),getString(R.string.videos));
    //   pagerAdapter.addFrag(new AudioFragment(), "Audios");
        pagerAdapter.addFrag(new PhotoFragment(),getString(R.string.doc));
        viewPager.setAdapter(pagerAdapter);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("DATA2", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("EDITEXT1", Id);  // Saving string data of your editext
        editor.commit(); // commit and
        tabLayout.setupWithViewPager(viewPager);



        DisplayActionBar();
     /*   Intent intent=getIntent();
        int loadsPosition = intent.getIntExtra("loadsPosition", -1);
        Log.d("EncyclopediaActivity", "loadsPosition======" + loadsPosition);
*/
      /*  Intent intent=getIntent();
        String loadsPosition = intent.getStringExtra("loadsPosition");
        Log.d("EncyclopediaActivity", "loadsPosition======" + loadsPosition);
*/

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void DisplayActionBar() {
        final ActionBar abar = getSupportActionBar();
        abar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        // abar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));//line under the action bar
        View viewActionBar = getLayoutInflater().inflate(R.layout.toolbar_all, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.custom_action_bar_title);
        textviewTitle.setText(titleName);



/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }
  /*  public void playVideo(View v) {
        MediaController m = new MediaController(this);
        vid.setMediaController(m);

        String path = "android.resource://com.calibrage.a3ffarmerapp/"+R.raw.oilvideo;

        Uri u = Uri.parse(path);

        vid.setVideoURI(u);

        vid.start();
        vid.setBackgroundResource(0);




    }*/

    public interface OnAboutDataReceivedListener {
        void onDataReceived(String model);
    }


    public void setAboutDataListener(OnAboutDataReceivedListener listener) {
        this.mAboutDataListener = listener;
    }

}



