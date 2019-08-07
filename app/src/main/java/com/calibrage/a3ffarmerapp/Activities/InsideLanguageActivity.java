package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.calibrage.a3ffarmerapp.R;

import es.dmoral.toasty.Toasty;

import static com.calibrage.a3ffarmerapp.util.CommonUtil.updateResources;

public class InsideLanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_language);
        final TextView rbEng = findViewById(R.id.english);
        final TextView rbTelugu = findViewById(R.id.telugu);

        rbEng.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                /**
                 * "en" is the localization code for our default English language.
                 */
                rbEng.setBackgroundColor(Color.rgb(60,180,110));
                updateResources(InsideLanguageActivity.this, "en-US");
                SharedPrefsData.getInstance(InsideLanguageActivity.this).updateIntValue(InsideLanguageActivity.this, "lang", 1);
                Intent refresh = new Intent(getApplicationContext(), SideMenuActivity.class);
                startActivity(refresh);
                finish();
                Toasty.success(getApplicationContext(),R.string.language_notification,Toast.LENGTH_SHORT).show();

            }
        });

/**
 * @param OnClickListner
 */
        rbTelugu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                /**
                 * "te" is the localization code for our default Telugu language.
                 */

                rbTelugu.setBackgroundColor(Color.rgb(60,180,110));
                updateResources(InsideLanguageActivity.this, "te");
                SharedPrefsData.getInstance(InsideLanguageActivity.this).updateIntValue(InsideLanguageActivity.this, "lang", 2);
                Intent refresh = new Intent(getApplicationContext(), SideMenuActivity.class);
                startActivity(refresh);
                finish();
                Toasty.success(getApplicationContext(),R.string.language_notification,Toast.LENGTH_SHORT).show();

              /*  LocaleHelper.setLocale(LanguageActivity.this, mLanguageCode);

                //It is required to recreate the activity to reflect the change in UI.
                recreate();*/

            }
        });
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
        textviewTitle.setText(R.string.choose_language);
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }
    public void onBackPressed() {
        //  super.onBackPressed();
        moveTaskToBack(true);

    }
}
