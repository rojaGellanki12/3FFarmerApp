package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.calibrage.a3ffarmerapp.Adapters.TableViewAdapter;
import com.calibrage.a3ffarmerapp.Model.MovieModal;
import com.calibrage.a3ffarmerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static com.calibrage.a3ffarmerapp.util.UrlConstants.BASE_URL;

public class CollectionsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatePickerDialog picker;
    EditText fromText,toText;
    String[] country = { "Last 15 Days", "Last 30 Days", "Full Financial year", "Since April 2017", "Custom Time Period"};
    RelativeLayout timePeroidLinear;
    Spinner spin;
    //  Button subBtn;
    private RecyclerView recyclerView;
    String currentDate;
    //TableViewAdapter adapter;
    //Creating Views
    String financiyalYearFrom="";
    String financiyalYearTo="";
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    //Creating a List of superheroes
    private List<MovieModal> listSuperHeroes;
    String fromString,toString;
    String reformattedStrFrom,reformattedStrTo;
    TextView text;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);
        dialog = new ProgressDialog(this);
        text=(TextView)findViewById(R.id.text);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                startActivity(intent);
            }
        });
        fromText=(EditText) findViewById(R.id.from_date);
        fromText.setInputType(InputType.TYPE_NULL);
        fromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CollectionsActivity.this,
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
       /* fromText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CollectionsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
                return false;
            }
        });*/

    /*    subBtn=(Button)findViewById(R.id.buttonScan);
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        toText=(EditText) findViewById(R.id.to_date);
        toText.setInputType(InputType.TYPE_NULL);
        toText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CollectionsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                toText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });
       /* toText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CollectionsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                toText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
                return false;
            }
        });*/
        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();
     /*   MovieModal[] myListData = new MovieModal[] {

                new MovieModal(),
        new MovieModal(),
        new MovieModal(),
        new MovieModal(),

        };*/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        timePeroidLinear=(RelativeLayout) findViewById(R.id.relative1);
        // Spinner element
        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        // DisplayActionBar();
        //current date
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.i("LOG_RESPONSE date ", currentDate);







        //get april 2017 date


       // calendar.setTime(date);
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String Text = String.valueOf(spin.getSelectedItem());
        //    subBtn.setVisibility(View.VISIBLE);
        // Toast.makeText(getApplicationContext(),Text , Toast.LENGTH_LONG).show();

       int index = spin.getSelectedItemPosition();
        if(index == 0)
        {
            //  String Month = MonthArray[index];
            if (spin.getSelectedItem().toString().equals("Last 15 Days")) {

                recyclerView.setVisibility(View.VISIBLE); //
                get15days();
            }
            else{
                recyclerView.setVisibility(View.GONE);

            }
        }
        if(index == 1)
        {
          //  String Month = MonthArray[index];
            if (spin.getSelectedItem().toString().equals("Last 30 Days")) {

                recyclerView.setVisibility(View.VISIBLE); //
                get30days();
            }
            else{
                recyclerView.setVisibility(View.GONE);

            }
        }
        if(index == 2)
        {
            //  String Month = MonthArray[index];
            if (spin.getSelectedItem().toString().equals("Full Financial year")) {

                recyclerView.setVisibility(View.VISIBLE); //
                getFullFinancialYear();


            }
            else{
                recyclerView.setVisibility(View.GONE);

            }
        }
        if(index == 3)
        {
            //  String Month = MonthArray[index];
            if (spin.getSelectedItem().toString().equals("Since April 2017")) {
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE); //
                getSinceApril2017Data();

            }
            else{
                recyclerView.setVisibility(View.GONE);

            }
        }
        if(index == 4)
        {
            //  String Month = MonthArray[index];
            if (spin.getSelectedItem().toString().equals("Custom Time Period")) {
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.GONE); //
                text.setVisibility(View.GONE);
                Button buttonSubmit=(Button)findViewById(R.id.buttonSubmit);

                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fromString = fromText.getText().toString().trim();
                        toString = toText.getText().toString().trim();

                        if(fromString.equalsIgnoreCase("")||toString.equalsIgnoreCase(""))
                        {
                            Toasty.error(CollectionsActivity.this, "Please Enter From/To Date", Toast.LENGTH_SHORT).show();
                            timePeroidLinear.setVisibility(View.VISIBLE); //
                            recyclerView.setVisibility(View.GONE);
                        }
                        else
                        {
                           // Toast.makeText(CollectionsActivity.this, "kiran", Toast.LENGTH_SHORT).show();
                            timePeroidLinear.setVisibility(View.GONE);
                       //     text.setVisibility(View.VISIBLE);
                            try {
                                adapter.notifyDataSetChanged();
                                recyclerView.invalidate();
                         //       recyclerView.setVisibility(View.VISIBLE);
                                getCustomCollections(fromString,toString);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
/*
                        if (fromString.length() <= 0) {
                            Toast.makeText(CollectionsActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
                        }
                        if (toString.length() <= 0) {
                            Toast.makeText(CollectionsActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
                        }
                      getCollections();*/

                        fromText.setText(null);
                        toText.setText(null);
                    }

                });

            }
            else{
                recyclerView.setVisibility(View.GONE);

            }
        }
        if(spin.getSelectedItem().toString().equals("Custom Time Period")){
            // Toast.makeText(getApplicationContext(),"hiddd" , Toast.LENGTH_LONG).show();
            timePeroidLinear.setVisibility(View.VISIBLE); //

            //  subBtn.setVisibility(View.VISIBLE);

//do something
        }else {
            timePeroidLinear.setVisibility(View.GONE);
            //   subBtn.setVisibility(View.VISIBLE);
        }


       /* switch (i){
            case 0:
                Toasty.success(getApplicationContext(), "1", Toast.LENGTH_LONG).show();
                break;
            case 1:
                if(spin.getSelectedItem().toString().equals("Last 30 Days")){
                    // Toast.makeText(getApplicationContext(),"hiddd" , Toast.LENGTH_LONG).show();
                    recyclerView.setVisibility(View.VISIBLE); //
                    getCollections();

                }else {
                    recyclerView.setVisibility(View.GONE);
                }
                break;
            case 2:
                if(spin.getSelectedItem().toString().equals("Full Financial year")){
                    // Toast.makeText(getApplicationContext(),"hiddd" , Toast.LENGTH_LONG).show();
                    recyclerView.setVisibility(View.VISIBLE); //
                    getFullFinancialYear();

                }else {
                    recyclerView.setVisibility(View.GONE);
                }
                break;
            case 3:
                Toasty.success(getApplicationContext(), "4", Toast.LENGTH_LONG).show();
                break;
            case 4:
                if(spin.getSelectedItem().toString().equals("Custom Time Period")){
                    // Toast.makeText(getApplicationContext(),"hiddd" , Toast.LENGTH_LONG).show();
                    timePeroidLinear.setVisibility(View.VISIBLE); //
                }else {
                    timePeroidLinear.setVisibility(View.GONE);
                }

                break;
        }*/
    }




    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        spin.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
    }
    private void get15days() {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        //15 days date
        long yourDateMillis = System.currentTimeMillis() - (15 * 24 * 60 * 60 * 1000);
        Time yourDate = new Time();
        yourDate.set(yourDateMillis);
        final String formattedDate = yourDate.format("%Y-%m-%d");
        Log.i(" formattedDate ", formattedDate);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String URL = BASE_URL+"Collection";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.i("LOG_RESPONSE ", response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("", "RESPONSE Encyclopedia======" + jsonObject);

                    parseData(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("farmerCode", "APNLVMVM00110008");
                MyData.put("fromDate", formattedDate);
                MyData.put("toDate", currentDate);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }
    private void get30days() {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        //30 days date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final String dateOutput = format.format(date);
        Log.i("LOG_RESPONSE formattedDate2 ", dateOutput);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String URL = BASE_URL+"Collection";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.i("LOG_RESPONSE ", response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("", "RESPONSE Encyclopedia======" + jsonObject);

                    parseData(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("farmerCode", "APNLVMVM00110008");
                MyData.put("fromDate", dateOutput);
                MyData.put("toDate", currentDate);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }
    private void getCustomCollections(final String fromString, final String toString) throws ParseException {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

             reformattedStrFrom = myFormat.format(fromUser.parse(fromString));
             reformattedStrTo = myFormat.format(fromUser.parse(toString));
            Log.d("collection", "RESPONSE reformattedStr======" + reformattedStrFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String URL = "http://183.82.111.111/3FFarmerAPI/api/Collection";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.i("LOG_RESPONSE ", response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("", "RESPONSE Encyclopedia======" + jsonObject);

                    parseData(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("farmerCode", "APNLVMVM00110008");
                MyData.put("fromDate", reformattedStrFrom);
                MyData.put("toDate", reformattedStrTo);
                Log.i("fromDate ", fromString);
                Log.i("toDate ", toString);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }
    private void getFullFinancialYear() {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        //financial year
        int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
        int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);

        if(CurrentMonth<4)
        {
            financiyalYearFrom=(CurrentYear-1)+"-04-01";
            financiyalYearTo=(CurrentYear)+"-03-31";
            Log.i("LOG_RESPONSE financiyalYearFrom ", financiyalYearFrom);
            Log.i("LOG_RESPONSE financiyalYearTo ", financiyalYearTo);
        }
        else
        {
            financiyalYearFrom=(CurrentYear)+"-04-01";
            financiyalYearTo=(CurrentYear+1)+"-03-31";;
            Log.i("LOG_RESPONSE financiyalYearFrom2 ", financiyalYearFrom);
            Log.i("LOG_RESPONSE financiyalYearTo2 ", financiyalYearTo);
        }
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String URL = "http://183.82.111.111/3FFarmerAPI/api/Collection";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.i("LOG_RESPONSE ", response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("", "RESPONSE Encyclopedia======" + jsonObject);

                    parseData(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("farmerCode", "APNLVMVM00110008");
                MyData.put("fromDate", financiyalYearFrom);
                MyData.put("toDate", financiyalYearTo);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }
    private void getSinceApril2017Data() {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        final String aprilDate = "2017/04/01";
        Log.i("LOG_RESPONSE april 2017 date ", aprilDate);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String URL = BASE_URL+"Collection";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.i("LOG_RESPONSE ", response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("", "RESPONSE Encyclopedia======" + jsonObject);

                    parseData(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("farmerCode", "APNLVMVM00110008");
                MyData.put("fromDate", aprilDate);
                MyData.put("toDate", currentDate);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);

    }
    private void parseData(JSONObject jsonObject) throws JSONException {
        JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
        Log.i("LOG_RESPONSE alsoKnownAsArray", String.valueOf(alsoKnownAsArray.length()));

        if(alsoKnownAsArray.length()>0){
            recyclerView.setVisibility(View.VISIBLE);
            text.setVisibility(View.GONE);
        }else{
            recyclerView.setVisibility(View.GONE);
            text.setVisibility(View.VISIBLE);
        }
        ArrayList<MovieModal> powers = new ArrayList<MovieModal>();
        for (int i = 0; i < alsoKnownAsArray.length(); i++) {
            MovieModal superHero = new MovieModal();
            JSONObject json = null;
            try {
                json = alsoKnownAsArray.getJSONObject(i);
               /* for (int j = 0; j < json.length(); i++) {
                    json = alsoKnownAsArray.getJSONObject(j);*/


                    superHero.setCollectionId(json.getString("u_colnid"));
                    superHero.setDate(json.getString("date"));
                    superHero.setWeight(json.getString("quantity"));
                    superHero.setCc(json.getString("whsName"));
                    superHero.setStatus(json.getString("docStatus"));
                    //   superHero.setCreatedBy(json.getString(Config.TAG_CREATED_BY));
                    // superHero.setFirstAppearance(json.getString(Config.TAG_FIRST_APPEARANCE));



                    //  JSONArray jsonArray = json.getJSONArray(Config.TAG_POWERS);

//                for(int j = 0; j<jsonArray.length(); j++){
//                    powers.add(((String) jsonArray.get(j))+"\n");
//                }
                   // superHero.setPowers(powers);
                    powers.add(superHero);


               // }
                //   System.out.println("kiran"+superHero);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            listSuperHeroes.add(superHero);
        }

        //Finally initializing our adapter
        adapter = new TableViewAdapter(powers, this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
