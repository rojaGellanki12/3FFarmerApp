package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.calibrage.a3ffarmerapp.Model.UserDetails;
import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

import static com.calibrage.a3ffarmerapp.util.UrlConstants.BASE_URL;

public class OtpActivity extends AppCompatActivity {
    String farmerId;
    public static  String TAG="OtpActivity";
    public PinEntryEditText pinEntry;
    public  SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Typeface faceBold = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Bold.ttf");
        Typeface faceRegular = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
        TextView otpDesc=(TextView)findViewById(R.id.otp_desc);
        otpDesc.setTypeface(faceRegular);
         editor = getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE).edit();
       /* TextView resend=(TextView)findViewById(R.id.resendTxt);
        resend.setTypeface(faceBold);*/
        Button submitBtn=(Button)findViewById(R.id.buttonSubmit);
        submitBtn.setTypeface(faceBold);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetOtp();
             /*   Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                startActivity(intent);
*/
            }
        });

        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent in = getIntent();
        farmerId= in.getExtras().getString("Farmer id");
        Log.d("Otp","Farmer id======"+ farmerId);
/*
if(farmerId==null){

}*/
        pinEntry = findViewById(R.id.txt_pin_entry);
        pinEntry.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        if (pinEntry != null) {
          //  pinEntry.setTypeface(ResourcesCompat.getFont(this, R.font.charmonman_regular));
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                   /* if (str.toString().equals("1234")) {
                        Toast.makeText(OtpActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        pinEntry.setError(true);
                        Toast.makeText(OtpActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                        pinEntry.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pinEntry.setText(null);
                            }
                        }, 1000);
                    }*/
                    /*Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                    startActivity(intent);
*/
                }
            });
        }

    }
    private void GetOtp() {
        //  String id="APWGBDAB00010001";
          String otpText=pinEntry.getText().toString();
        if (otpText.matches("")) {
            Toasty.error(getApplicationContext(), "Please enter Otp", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG,"RESPONSE======"+ otpText);
        String url =BASE_URL+"Farmer/"+farmerId+"/"+otpText;
     //   String url ="http://183.82.111.111/3FFarmerAPI/api/Farmer/"+farmerId+"/"+otpText;
        Log.d("Otp","url======"+ url);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,"RESPONSE======"+ response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG,"RESPONSE======"+ jsonObject);
                    String success=jsonObject.getString("isSuccess");
                    Log.d(TAG,"success======"+ success);
                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    if(alsoKnownAsArray.length()>0)
                    {
                        SharedPrefsData.putString(OtpActivity.this,Constants.USER_DETAILS,alsoKnownAsArray.getString(0),"2");

                    }



                   // Toasty.success(getApplicationContext(), userDetails.getAddress(), Toast.LENGTH_LONG).show();

//                    jsonString = gson.toJson(student);
//                    System.out.println(jsonString);
                    if (success.equals("true")){

                        SharedPrefsData.putBool(OtpActivity.this,Constants.IS_LOGIN,true,"2");

                        editor.putBoolean(Constants.IS_LOGIN,true);
                        editor.apply();
                        Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                        startActivity(intent);
                        Toasty.success(getApplicationContext(), "OTP Valided Successfully", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
                    }else{
                        Toasty.error(getApplicationContext(), "OTP Invalid", Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

}
