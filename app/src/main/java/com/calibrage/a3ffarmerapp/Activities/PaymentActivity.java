package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.calibrage.a3ffarmerapp.util.UrlConstants.BASE_URL;

public class PaymentActivity extends AppCompatActivity {
    public static  String TAG="PaymentActivity";
    private  TextView accoontHolderName,accoontNumber,bankNamee,branchName;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        dialog = new ProgressDialog(this);
        accoontHolderName=(TextView)findViewById(R.id.tvtext_item_three);
        accoontNumber=(TextView)findViewById(R.id.tvtext_item_five);
        bankNamee=(TextView)findViewById(R.id.tvtext_item_seven);
        branchName=(TextView)findViewById(R.id.tvtext_item_nine);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                startActivity(intent);
            }
        });
       Button submitBtn=(Button)findViewById(R.id.nextButton);
       // submitBtn.setTypeface(faceBold);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),PaymentHistoryActivity.class);
                startActivity(intent);

            }
        });
        getBankDetails();
    }
    private void getBankDetails()  {
      //  listSuperHeroes.clear();
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
       // String URL = "http://183.82.111.111/3FFarmerAPI/api/Payment/GetVendorLedger";
        String URL = BASE_URL+"Payment/GetVendorLedger";

        RequestQueue queue= Volley.newRequestQueue(this);


        Map<String, String> jsonParams = new HashMap<String, String>();
       /* jsonParams.put( "plotCode","APAB0001000001");
        jsonParams.put( "fromDate","2016-01-01T10:10:52.5116115+05:30");
        jsonParams.put( "toDate","2019-08-01T10:10:52.5116115+05:30");*/
        jsonParams.put( "fromDate","2019-04-02T10:57:42.62339+05:30");
        jsonParams.put( "toDate","2019-08-02T10:57:42.62339+05:30");

        String vendor= Constants.FARMER_CODE;
        String splitVendor = vendor.replace("AP", "V");
        Log.d(TAG,"newString:"+ splitVendor);
        jsonParams.put( "vendorCode",splitVendor);
        Log.d(TAG,"Json==slot:"+ new JSONObject(jsonParams));

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, URL,new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String result = response.toString();

                        Log.d("result=====",result);
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(result);
                            Log.d(TAG,"RESPONSE getBankDetails======"+ jsonObject);
                            JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                            for(int i = 0; i<alsoKnownAsArray.length(); i++) {
                                JSONObject leagueData = alsoKnownAsArray.getJSONObject(i);
                                String cardName = leagueData.getString("cardName");
                                String bank_Account = leagueData.getString("bank_Account");
                                String bankName = leagueData.getString("bankName");
                                String branch = leagueData.getString("branch");
                                Log.d(TAG, "RESPONSE cardName======" + cardName);
                                Log.d(TAG, "RESPONSE bank_Account======" + bank_Account);
                                accoontHolderName.setText(cardName);
                                accoontNumber.setText(bank_Account);
                                bankNamee.setText(bankName);
                                branchName.setText(branch);
                            }

                            String affectedRecords=jsonObject.getString("affectedRecords");
                            Log.d(TAG,"RESPONSE getBankDetails======"+ affectedRecords);
                           /* if(affectedRecords.contains("0") ){
                                recyclerView.setVisibility(View.GONE);
                                text.setVisibility(View.VISIBLE);

                            }else{
                                recyclerView.setVisibility(View.VISIBLE);
                                text.setVisibility(View.GONE);
                            }
                            JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                            Log.d(TAG,"RESPONSE result======"+ alsoKnownAsArray);

                            parseData(alsoKnownAsArray);*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onResponse(String response) {
//                //This code is executed if the server responds, whether or not the response contains data.
//                //The String 'response' contains the server's response.
//                Log.i("LOG_RESPONSE ", response);
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d(TAG, "RESPONSE PLOT======" + jsonObject);
//                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
//                    Log.i("LOG_RESPONSE PLOT", String.valueOf(alsoKnownAsArray.length()));
//                    Log.d("data2===", "APAB0001000001");
//                 //   parseData(jsonObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }



                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
// Handle Error

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String,String>();
// headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }



        };

        queue.add(postRequest);

    }
}
