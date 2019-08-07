package com.calibrage.a3ffarmerapp.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.calibrage.a3ffarmerapp.Adapters.GetRecommendationsByAgeAdapter;
import com.calibrage.a3ffarmerapp.Model.GetRecommendationsByAgeModel;
import com.calibrage.a3ffarmerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static com.calibrage.a3ffarmerapp.util.UrlConstants.BASE_URL;

public class GetRecommendationByAges extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static String TAG = "GetRecommendationByAges";
    ArrayList<String> listdata;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<GetRecommendationsByAgeModel> listSuperHeroes;
    private RecyclerView.Adapter adapter;
    Spinner spin;
    public static String text_year;
    boolean isLoading = false;
    private ProgressDialog dialog;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_recommendation_by_ages);
        dialog = new ProgressDialog(this);
        text=(TextView)findViewById(R.id.noData);
        ImageView backImg = (ImageView) findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SideMenuActivity.class);
                startActivity(intent);
            }
        });
        Button loginBtn=(Button)findViewById(R.id.view_recommendations);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), GetRecommendationsActivity.class);
                startActivity(intent);


            }
        });
        // Spinner element
        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listSuperHeroes = new ArrayList<>();
        GetRecommendation();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listSuperHeroes.size() - 1) {
                        //bottom of list!
                       // loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }


    private void loadMore() {
        listSuperHeroes.add(null);
        adapter.notifyItemInserted(listSuperHeroes.size() - 1);

        parseData(text_year,true);


//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                listSuperHeroes.remove(listSuperHeroes.size() - 1);
//                int scrollPosition = listSuperHeroes.size();
//                adapter.notifyItemRemoved(scrollPosition);
//                int currentSize = scrollPosition;
//                int nextLimit = currentSize + 10;
//
//                while (currentSize - 1 < nextLimit) {
//                   // listSuperHeroes.add("Item " + currentSize);
//                    currentSize++;
//                }
//
//                adapter.notifyDataSetChanged();
//                isLoading = false;
//            }
//        }, 2000);


    }

    private void GetRecommendation() {
        //  String id="APWGBDAB00010001";
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        String url = BASE_URL + "GetRecommendationAges";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "RESPONSE GetRecommendationAges======" + response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG, "RESPONSE GetRecommendationAges======" + jsonObject);
                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    Log.i("LOG_RESPONSE GetRecommendationAges", String.valueOf(alsoKnownAsArray));
                    listdata = new ArrayList<String>();
                    //  JSONArray jArray = (JSONArray)jsonObject;
                    if (alsoKnownAsArray != null) {
                        for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                            listdata.add(alsoKnownAsArray.getString(i));
                            Log.d(TAG, "RESPONSE GetRecommendationAges listdata======" + listdata);
                            //Creating the ArrayAdapter instance having the country list
                            ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, listdata);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            spin.setAdapter(aa);
                        }
                    }
                    String success = jsonObject.getString("isSuccess");
                    Log.d(TAG, "success======" + success);
                    if (success.equals("true")) {

                    } else {

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if (error instanceof NetworkError) {
                    Log.i("one:" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Log.i("two:" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Log.i("three:" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "AuthFailure Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Log.i("four::" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Log.i("five::" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "NoConnection Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Log.i("six::" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("Checking error in else");
                }
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int index = spin.getSelectedItemPosition();
        text_year = spin.getItemAtPosition(spin.getSelectedItemPosition()).toString();
        Log.d("data===", text_year);
        parseData(text_year,false);

    }


    //This method will parse json data
    private void parseData(String text_year, final boolean lazyloading) {

        //  String id="APWGBDAB00010001";
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        listSuperHeroes.clear();

        String URL_TOKEN = BASE_URL + "GetRecommendationsByAge/" + GetRecommendationByAges.text_year;

        Log.d("URL_TOKEN====",URL_TOKEN);
        Log.d("text_year====",text_year);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Doctor_Json" + response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                try {

if(lazyloading){
    listSuperHeroes.remove(listSuperHeroes.size() - 1);
    int scrollPosition = listSuperHeroes.size();
    adapter.notifyItemRemoved(scrollPosition);
}

                    JSONArray jsonArray = new JSONArray(response);
                    Log.e("jsonArray==", jsonArray.toString());

        for(int i = 0; i<jsonArray.length(); i++) {

            GetRecommendationsByAgeModel superHero = new GetRecommendationsByAgeModel();
            JSONObject json = null;
            try {
                json = jsonArray.getJSONObject(i);
                superHero.setFertilizer(json.getString("fertilizer"));
                superHero.setUoM(json.getString("uoM"));
                superHero.setYear(json.getString("quantity"));
                superHero.setRemarks(json.getString("remarks"));
                ArrayList<String> powers = new ArrayList<String>();

                Log.e("json===",json.toString());
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
        adapter = new GetRecommendationsByAgeAdapter(listSuperHeroes, GetRecommendationByAges.this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Finally initializing our adapter
//                adapter = new GetRecommendationsByAgeAdapter(listSuperHeroes, GetRecommendationByAges.this);
//
//                //Adding adapter to recyclerview
//                recyclerView.setAdapter(adapter);


if(lazyloading){

//    listSuperHeroes.remove(listSuperHeroes.size() - 1);
//    int scrollPosition = listSuperHeroes.size();
//    adapter.notifyItemRemoved(scrollPosition);
   // int currentSize = scrollPosition;
   // int nextLimit = currentSize + 10;
    adapter.notifyDataSetChanged();
    isLoading = false;

}else {

    adapter = new GetRecommendationsByAgeAdapter(listSuperHeroes, GetRecommendationByAges.this);

    //Adding adapter to recyclerview
    recyclerView.setAdapter(adapter);

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




    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
