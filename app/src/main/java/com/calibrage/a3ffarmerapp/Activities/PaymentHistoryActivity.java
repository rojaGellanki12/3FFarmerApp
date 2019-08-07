package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.calibrage.a3ffarmerapp.Adapters.PaymentHistoryAdapter;
import com.calibrage.a3ffarmerapp.Model.PaymentHistoryModel;
import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static com.calibrage.a3ffarmerapp.util.CommonUtil.ConvertDatetoTime;
import static com.calibrage.a3ffarmerapp.util.UrlConstants.BASE_URL;


public class PaymentHistoryActivity extends AppCompatActivity  {
    private Animation animationUp, animationDown;
    private RecyclerView recyclerView;
    public static  String TAG="PaymentHistoryActivity";
    String[] country = { "Last 15 days", "Last 30 days", "Full Financial year", "Since April 2017", "Custom Time Period"};
    Spinner spin;
    private ProgressDialog dialog;
    EditText fromText,toText;
    String fromString,toString;
    DatePickerDialog picker;
    LinearLayout timePeroidLinear;
    private RecyclerView.Adapter adapter;
    private List<PaymentHistoryModel> listSuperHeroes;
    private RecyclerView.LayoutManager layoutManager;
    String selected_datep;Button submit;
    String reformattedStrFrom,reformattedStrTo;
    TextView text;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        dialog = new ProgressDialog(this);
        text=(TextView)findViewById(R.id.text);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(intent);
            }
        });
         submit=(Button)findViewById(R.id.buttonSubmit);

        listSuperHeroes = new ArrayList<>();

        timePeroidLinear=(LinearLayout)findViewById(R.id.linear2);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fromText=(EditText) findViewById(R.id.from_date);
        fromText.setInputType(InputType.TYPE_NULL);
        fromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
             //   picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker = new DatePickerDialog(PaymentHistoryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);


                            }
                        }, year, month, day);
                picker.show();
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });

        toText=(EditText) findViewById(R.id.to_date);
        toText.setInputType(InputType.TYPE_NULL);
        toText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Calendar  calendar1 = Calendar.getInstance();
                int day = calendar1.get(Calendar.DAY_OF_MONTH);
                int month = calendar1.get(Calendar.MONTH);
                int year = calendar1.get(Calendar.YEAR);
                // date picker dialog
             DatePickerDialog   picker1 = new DatePickerDialog(PaymentHistoryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                                toText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                String selected_date=(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                int month = (monthOfYear + 1);

                                Log.e("selected_date===",selected_date);
                            }
                        }, year, month, day);

                picker1.show();
            //    picker1.getDatePicker().setMinDate(ConvertDatetoTime(fromText.getText().toString()));
                picker1.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                listSuperHeroes.clear();
                fromString = fromText.getText().toString().trim();
                toString = toText.getText().toString().trim();
                Log.d("fromString==", fromString);
                Log.d("toString==", toString);
                if(fromString.equalsIgnoreCase("")||toString.equalsIgnoreCase(""))
                {
                    Toasty.error(PaymentHistoryActivity.this, "Please Enter From/To Date", Toast.LENGTH_SHORT).show();
                    /*timePeroidLinear.setVisibility(View.VISIBLE); //
                    recyclerView.setVisibility(View.GONE);*/
                }
                else {

                    // Toast.makeText(CollectionsActivity.this, "kiran", Toast.LENGTH_SHORT).show();
                    //  timePeroidLinear.setVisibility(View.GONE);
                    //     text.setVisibility(View.VISIBLE);


                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date d1 = null;
                    java.util.Date d2 = null;
                    try {
                        d1 = sdf.parse(fromString);

                        d2 = sdf.parse(toString);


                        System.out.println("1. " + sdf.format(d1).toUpperCase());
                        System.out.println("2. " + sdf.format(d2).toUpperCase());

                        if (compareTo(d1, d2) < 0) {

                            System.out.println("proceed");
                        } else if (compareTo(d1, d2) > 0) {
                            System.out.println("invalid");
                        } else {
                            System.out.println("equal");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long diff = d2.getTime() - d1.getTime();

                    Log.e("diff===", String.valueOf(diff));

                    float dayCount = (float) diff / (24 * 60 * 60 * 1000);

                    Log.e("dayCount===", String.valueOf(dayCount));
                    if (dayCount <= 31.0) {
                        System.out.println("next==");
                        getPaymentDetails(fromString,toString);
                    } else {
                        System.out.println("invalid");
                        Toasty.error(getApplicationContext(), "Please select date with in one month only", Toast.LENGTH_SHORT).show();
                    }

                }

               // return (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

//                if (fromString.compareTo(toString) <= 31) {
//                    System.out.println("earlier");
//                    Toast.makeText(getApplicationContext(), "earlier", Toast.LENGTH_SHORT).show();
//                }
//
//               /* if (fromString.compareTo(toString) > 31)
//                {
//                    Toast.makeText(getApplicationContext(), "31", Toast.LENGTH_SHORT).show();
//                }*/
//                else
//                {
//                    Toast.makeText(getApplicationContext(), "kkk", Toast.LENGTH_SHORT).show();
//                    Log.d("Return","getMyTime older than getCurrentDateTime ");
//                }


            }
        });



    }

    public static long compareTo( java.util.Date date1, java.util.Date date2 )
    {
//returns negative value if date1 is before date2
//returns 0 if dates are even
//returns positive value if date1 is after date2
        return date1.getTime() - date2.getTime();

    }
    private void getPaymentDetails(String fromString, String toString)  {
        //  listSuperHeroes.clear();
   listSuperHeroes.clear();
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            reformattedStrFrom = myFormat.format(fromUser.parse(this.fromString));
            reformattedStrTo = myFormat.format(fromUser.parse(this.toString));
            Log.d("recommendations", "RESPONSE reformattedStr======" + reformattedStrFrom);
            Log.d("recommendations", "RESPONSE reformattedStr2======" + reformattedStrTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
     //   String URL = "http://183.82.111.111/3FFarmerAPI/api/Payment/GetVendorLedger";
        String URL = BASE_URL+"Payment/GetVendorLedger";

        RequestQueue queue= Volley.newRequestQueue(this);


        Map<String, String> jsonParams = new HashMap<String, String>();
       /* jsonParams.put( "plotCode","APAB0001000001");
        jsonParams.put( "fromDate","2016-01-01T10:10:52.5116115+05:30");
        jsonParams.put( "toDate","2019-08-01T10:10:52.5116115+05:30");*/
       /* jsonParams.put( "fromDate","2019-04-02T10:57:42.62339+05:30");
        jsonParams.put( "toDate","2019-08-02T10:57:42.62339+05:30");*/
        jsonParams.put( "fromDate", reformattedStrFrom);
        jsonParams.put( "toDate", reformattedStrTo);
      //  jsonParams.put( "vendorCode","VWGBDAB00010001");
        String vendor= Constants.FARMER_CODE;
        String splitVendor = vendor.replace("AP", "V");
        Log.d(TAG,"newString:"+ splitVendor);
        jsonParams.put( "vendorCode",splitVendor);
        Log.d(TAG,"Json==slot:"+ new JSONObject(jsonParams));

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, URL,new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {
                        String result = response.toString();

                        Log.d("getPaymentDetails=====",result);
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(result);
                            Log.d(TAG,"RESPONSE getPaymentDetails======"+ jsonObject);
                            JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                            Log.d(TAG,"RESPONSE result======"+ alsoKnownAsArray);
                            if(alsoKnownAsArray.length()>0){
                                recyclerView.setVisibility(View.VISIBLE);
                                text.setVisibility(View.GONE);
                            }else{
                                recyclerView.setVisibility(View.GONE);
                                text.setVisibility(View.VISIBLE);
                            }
                            parseData(alsoKnownAsArray);

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
    private void parseData(JSONArray array){
        for(int i = 0; i<array.length(); i++) {

            PaymentHistoryModel superHero = new PaymentHistoryModel();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                superHero.setDateOfPayment(json.getString("quantity"));
                superHero.setAmount(json.getString("adhoc_Rate"));
                superHero.setBankAccountNumber(json.getString("invoice_Rate"));
                superHero.setBankHolderName(json.getString("gR_Amount"));
                superHero.setBankName(json.getString("memo"));
                superHero.setFinalAmount(json.getString("amount"));
              /*  superHero.setYear3(json.getString("issuIdentified"));
                superHero.setYear4And5(json.getString("recommendedBy"));
                superHero.setYear6And7("");*/



                //     superHero.setYear8To17(json.getString("plotSize"));
                //    superHero.setRemarks(json.getString("location"));
                ArrayList<String> powers = new ArrayList<String>();

                //  JSONArray jsonArray = json.getJSONArray(Config.TAG_POWERS);

//                for(int j = 0; j<jsonArray.length(); j++){
//                    powers.add(((String) jsonArray.get(j))+"\n");
//                }
                superHero.setPowers(powers);
                //   System.out.println("kiran"+superHero);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            listSuperHeroes.add(superHero);
        }

        //Finally initializing our adapter
        adapter = new PaymentHistoryAdapter(listSuperHeroes, this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
  /*  @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String Text = String.valueOf(spin.getSelectedItem());
        //    subBtn.setVisibility(View.VISIBLE);
        // Toast.makeText(getApplicationContext(),Text , Toast.LENGTH_LONG).show();
        if(spin.getSelectedItem().toString().equals("Custom Time Period")){
            // Toast.makeText(getApplicationContext(),"hiddd" , Toast.LENGTH_LONG).show();
            timePeroidLinear.setVisibility(View.VISIBLE); //
            //  subBtn.setVisibility(View.VISIBLE);

//do something
        }else {
            timePeroidLinear.setVisibility(View.GONE);
            //   subBtn.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        spin.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
    }*/

}
