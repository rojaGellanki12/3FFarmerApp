package com.calibrage.a3ffarmerapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.core.app.ActivityCompat;

import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.util.CommonUtil;
import com.calibrage.a3ffarmerapp.util.Constants;

public class Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;
   public  boolean status =true;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !CommonUtil.areAllPermissionsAllowed(this, CommonUtil.PERMISSIONS_REQUIRED)) {
            ActivityCompat.requestPermissions(this, CommonUtil.PERMISSIONS_REQUIRED, CommonUtil.PERMISSION_CODE);
        }


        boolean is_login =  SharedPrefsData.getBool(Splash.this, Constants.IS_LOGIN,"2");

        if(is_login){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(Splash.this, SideMenuActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);

        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(Splash.this, LanguageActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);

        }
        if (status) {
            // MY_PREFS_NAME - a static String variable like:
//public static final String MY_PREFS_NAME = "MyPrefsFile";
            SharedPreferences sp =getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor et = sp.edit();
            et.putBoolean("isLogin", true);
            et.commit();

            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/

        }else{
            SharedPreferences sp =getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor et = sp.edit();
            et.putBoolean("isLogin", true);
            et.commit();

            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CommonUtil.PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //  Log.v(LOG_TAG, "permission granted");

                }
                break;
        }
    }
}
