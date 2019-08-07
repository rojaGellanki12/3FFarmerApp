package com.calibrage.a3ffarmerapp.Activities;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.calibrage.a3ffarmerapp.Adapters.AlbumsAdapter;
import com.calibrage.a3ffarmerapp.Adapters.RecommendationAdapter;
import com.calibrage.a3ffarmerapp.Adapters.listviewAdapter;
import com.calibrage.a3ffarmerapp.Model.Album;
import com.calibrage.a3ffarmerapp.Model.Model;
import com.calibrage.a3ffarmerapp.Model.RecommendationModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecommendationActivity extends AppCompatActivity  {
    private ArrayList<Model> productList;
    private RecyclerView recyclerView;
    private RecommendationAdapter adapter;
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                startActivity(intent);
            }
        });
        RecommendationModel[] myListData = new RecommendationModel[] {
                new RecommendationModel("Plot252019","2 hec ","Chinnakoduru","Near Shiavalayam"),
                new RecommendationModel("Plot242019","1 hec  ","Dundigal","Opposite govt school"),
                new RecommendationModel("Plot232019","3 hec ","Baswapur","Flowers market"),
                new RecommendationModel("Plot222019","4 hec  ","Medipally","Main road"),

        };

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecommendationAdapter adapter = new RecommendationAdapter(this,myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

  //  DisplayActionBar();

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
        textviewTitle.setText(R.string.recommendations);
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }

   /* @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button5:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(RecommendationActivity.this);
                LayoutInflater inflater = this.getLayoutInflater();
                alertDialog.setView(inflater.inflate(R.layout.recommendations_dialog, null));

                *//* When positive (yes/ok) is clicked *//*
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        finish(); // Your custom code
                    }
                });

                *//* When negative (No/cancel) button is clicked*//*
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); // Your custom code
                    }
                });
                alertDialog.show();

                break;

            case R.id.button6:
                AlertDialog.Builder alertDialog6 = new AlertDialog.Builder(RecommendationActivity.this);
                LayoutInflater inflater6 = this.getLayoutInflater();
                alertDialog6.setView(inflater6.inflate(R.layout.recommendations_dialog, null));

                *//* When positive (yes/ok) is clicked *//*
                alertDialog6.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        finish(); // Your custom code
                        dialog.dismiss();
                    }
                });

                *//* When negative (No/cancel) button is clicked*//*
                alertDialog6.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); // Your custom code
                    }
                });
                alertDialog6.show();
                break;

            case R.id.button7:
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(RecommendationActivity.this);
                LayoutInflater inflater2 = this.getLayoutInflater();
                alertDialog2.setView(inflater2.inflate(R.layout.recommendations_dialog, null));

                *//* When positive (yes/ok) is clicked *//*
                alertDialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        finish(); // Your custom code
                    }
                });

                *//* When negative (No/cancel) button is clicked*//*
                alertDialog2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); // Your custom code
                    }
                });
                alertDialog2.show();
                break;
            case R.id.button8:
                AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(RecommendationActivity.this);
                LayoutInflater inflater3 = this.getLayoutInflater();
                alertDialog3.setView(inflater3.inflate(R.layout.recommendations_dialog, null));

                *//* When positive (yes/ok) is clicked *//*
                alertDialog3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        finish(); // Your custom code
                    }
                });

                *//* When negative (No/cancel) button is clicked*//*
                alertDialog3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); // Your custom code
                    }
                });
                alertDialog3.show();
                break;
            default:
                break;
        }
    }*/
}

