package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.calibrage.a3ffarmerapp.Adapters.RecommendationAdapter;
import com.calibrage.a3ffarmerapp.Adapters.ReqVisitAdapter;
import com.calibrage.a3ffarmerapp.Model.Model;
import com.calibrage.a3ffarmerapp.Model.RecommendationModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;

public class RequestVisitActivity extends AppCompatActivity {
    private ArrayList<Model> productList;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_visit);

        RecommendationModel[] myListData = new RecommendationModel[] {
                new RecommendationModel("Plot252019","Siddipet","Chinnakoduru","Active"),
                new RecommendationModel("Plot242019","Medchal ","Dundigal","Active"),
                new RecommendationModel("Plot232019","Siddipet","Baswapur","InActive"),
                new RecommendationModel("Plot222019","Medchal ","Medipally","Active"),

        };

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ReqVisitAdapter adapter = new ReqVisitAdapter(this,myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        DisplayActionBar();
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
        textviewTitle.setText(R.string.req_visit);
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }
    public void alert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RequestVisitActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        alertDialog.setView(inflater.inflate(R.layout.custom_layout_dialog, null));

        /* When positive (yes/ok) is clicked */
        alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                finish(); // Your custom code
            }
        });

        /* When negative (No/cancel) button is clicked*/
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); // Your custom code
            }
        });
        alertDialog.show();



    }
}
