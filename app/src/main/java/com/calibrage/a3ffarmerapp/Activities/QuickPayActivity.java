package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.calibrage.a3ffarmerapp.Adapters.QuickPayDataAdapter;
import com.calibrage.a3ffarmerapp.Adapters.TableViewAdapter;
import com.calibrage.a3ffarmerapp.Model.MovieModal;
import com.calibrage.a3ffarmerapp.Model.QuickPayModel;
import com.calibrage.a3ffarmerapp.R;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickPayActivity extends AppCompatActivity implements QuickPayDataAdapter.OnClickAck {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuickPayDataAdapter adapter;
    private  int checkedcount=0 ;

    private List<QuickPayModel> studentList;

    private Button btnSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_pay);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                startActivity(intent);
            }
        });
         btnSelection=(Button)findViewById(R.id.btn_submit);
     //   submitBtn.setTypeface(faceBold);
        btnSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkedcount!=0){
                    startActivity(new Intent(QuickPayActivity.this, PaymentSummaryActivity.class));
                    finish();

                }else
                {
                    Toast.makeText(QuickPayActivity.this,"Please check at least one collection",Toast.LENGTH_SHORT).show();
                }



            }
        });
      //  toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  btnSelection = (Button) findViewById(R.id.btn_show);

        studentList = new ArrayList<QuickPayModel>();


       /* if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Android Students");

        }*/
        QuickPayModel[] myListData = new QuickPayModel[] {

                new QuickPayModel("Collection ID1", "20 tons", "06/19/2019", "Kukatpally",false),
                new QuickPayModel("Collection ID2", "40 tons", "08/19/2019", "Ameerpet",false),
                new QuickPayModel("Collection ID3", "50 tons", "09/19/2019", "Moosapet",false),
                new QuickPayModel("Collection ID4", "30 tons", "10/19/2019", "Jntu",false),




        };

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
         adapter = new QuickPayDataAdapter(this, Arrays.asList(myListData));
         adapter.setOnListener(QuickPayActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

      /*  for (int i = 1; i <= 15; i++) {
            QuickPayModel st = new QuickPayModel("Collection ID " + i, "","","", false);

            studentList.add(st);
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an Object for Adapter
        mAdapter = new QuickPayDataAdapter(studentList);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
*/
      /*  btnSelection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              *//* String data = "";
                List<Student> stList = ((CardViewDataAdapter) mAdapter)
                        .getStudentist();

                for (int i = 0; i < stList.size(); i++) {
                    Student singleStudent = stList.get(i);
                    if (singleStudent.isSelected() == true) {

                        data = data + "\n" + singleStudent.getName().toString();

                    }

                }

                Toast.makeText(MainActivity.this,
                        "Selected Students: \n" + data, Toast.LENGTH_LONG)
                        .show();*//*
            }
        });*/
   //     DisplayActionBar();
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
        textviewTitle.setText(R.string.quickPay);
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }

    @Override
    public void setOnClickAckListener(String status, int position, Boolean ischecked) {

        if(ischecked){


            checkedcount+=1;
        }else {

            checkedcount-=1;
        }

    }
}
