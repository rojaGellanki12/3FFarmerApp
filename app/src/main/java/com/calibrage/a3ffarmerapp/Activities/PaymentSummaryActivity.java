package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.calibrage.a3ffarmerapp.Fragments.HomeFragment;
import com.calibrage.a3ffarmerapp.R;

import es.dmoral.toasty.Toasty;

public class PaymentSummaryActivity extends AppCompatActivity {
     Boolean aBoolean =false;
    CheckBox checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_summary);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),QuickPayActivity.class);
                startActivity(intent);
            }
        });
        Button confirmBtn=(Button)findViewById(R.id.buttonConfirm);
        checkbox = (CheckBox)findViewById(R.id.checkBox);
     //   loginBtn.setTypeface(faceBold);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkbox.isChecked()){
                    Toasty.success(getApplicationContext(), "Payment Request Submitted Successfully", Toast.LENGTH_LONG).show();
                    PaymentSummaryActivity.this.finish();
                }else{
                    Toast.makeText(getApplicationContext(),R.string.terms_agree,Toast.LENGTH_SHORT).show();
                }

              //  finish();
              /*  Fragment fragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();*/

            }
        });
        CheckBox checkbox = (CheckBox)findViewById(R.id.checkBox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //checked
                    new AlertDialog.Builder(PaymentSummaryActivity.this)
                            .setTitle("Terms& Conditions")
                            .setMessage("We show you ads, offers and other sponsored content to help you discover content, products and services that are offered by the many businesses and organisations that use Facebook and other Facebook Products. Our partners pay us to show their content to you, and we design our services so that the sponsored content you see is as relevant and useful to you as everything else that you see on our Products..")
                            .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d("MainActivity", "Sending atomic bombs to Jupiter");
                                }
                            })
                            /* .setNegativeButton("Abort", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     Log.d("MainActivity", "Aborting mission...");
                                 }
                             })*/
                            .show();
                } else {
                    //not checked
                }
            }
        });
      /* checkbox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (aBoolean = true) {
                   new AlertDialog.Builder(PaymentSummaryActivity.this)
                           .setTitle("Terms& Conditions")
                           .setMessage("We show you ads, offers and other sponsored content to help you discover content, products and services that are offered by the many businesses and organisations that use Facebook and other Facebook Products. Our partners pay us to show their content to you, and we design our services so that the sponsored content you see is as relevant and useful to you as everything else that you see on our Products..")
                           .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Log.d("MainActivity", "Sending atomic bombs to Jupiter");
                               }
                           })
                           *//* .setNegativeButton("Abort", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d("MainActivity", "Aborting mission...");
                                }
                            })*//*
                           .show();
               }

           }


       });*/
       // DisplayActionBar();
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
        textviewTitle.setText("Quick Pay");
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }
}
