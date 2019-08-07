package com.calibrage.a3ffarmerapp.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.bumptech.glide.load.HttpException;
import com.calibrage.a3ffarmerapp.Activities.CollectionsActivity;
import com.calibrage.a3ffarmerapp.Activities.EncyclopediaActivity;
import com.calibrage.a3ffarmerapp.Activities.FertilizerActivity;
import com.calibrage.a3ffarmerapp.Activities.GetRecommendationByAges;
import com.calibrage.a3ffarmerapp.Activities.GetRecommendationsActivity;
import com.calibrage.a3ffarmerapp.Activities.LabourRecommendationsActivity;
import com.calibrage.a3ffarmerapp.Activities.LoanActivity;
import com.calibrage.a3ffarmerapp.Activities.PaymentActivity;
import com.calibrage.a3ffarmerapp.Activities.PoleActivity;
import com.calibrage.a3ffarmerapp.Activities.QuickPayActivity;
import com.calibrage.a3ffarmerapp.Activities.RecommendationActivity;
import com.calibrage.a3ffarmerapp.Activities.RecyclerTouchListener;
import com.calibrage.a3ffarmerapp.Activities.RequestVisitActivity;
import com.calibrage.a3ffarmerapp.Adapters.CardAdapter;
import com.calibrage.a3ffarmerapp.Adapters.KnowledgeZoneBaseAdapter;
import com.calibrage.a3ffarmerapp.Model.Album;
import com.calibrage.a3ffarmerapp.Model.GetLookUpModel;
import com.calibrage.a3ffarmerapp.Model.SuperHeroes;
import com.calibrage.a3ffarmerapp.NetworkService.APIConstants;
import com.calibrage.a3ffarmerapp.NetworkService.MyServices;
import com.calibrage.a3ffarmerapp.NetworkService.ServiceFactory;
import com.calibrage.a3ffarmerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.dmoral.toasty.Toasty;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;
import static com.calibrage.a3ffarmerapp.util.UrlConstants.BASE_URL;
import static com.calibrage.a3ffarmerapp.util.UrlConstants.learing_videos_pdfs;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ImageButton dail;
    RecyclerView recyclerView;
    private List<Album> albumList;
    private List<SuperHeroes> listSuperHeroes;
    String id;
    //Creating Views
    String[] strArray;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    public static  String TAG="HomeFragment";
    ImageView offersImg;
    private Subscription mRegisterSubscription;
    private GridView gridView;
    private  KnowledgeZoneBaseAdapter knowledgeZoneBaseAdapter;
    private List<GetLookUpModel.ListResult> getCategoryList;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
       // setHasOptionsMenu(true);
        dialog = new ProgressDialog(getActivity());

        view.findViewById(R.id.collections_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), CollectionsActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.recommendations_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), GetRecommendationByAges.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.visit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), RequestVisitActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.loan_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), LoanActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.labour_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), LabourRecommendationsActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.pole_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), PoleActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.fertilizer_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), FertilizerActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.payments_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.quickPay_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), QuickPayActivity.class);
                startActivity(intent);
            }
        });
     /*   view.findViewById(R.id.more_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.success(getActivity(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });*/
//        view.findViewById(R.id.knowledge_zone).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getContext(), EncyclopediaActivity.class);
//                startActivity(intent);
//            }
//        });
//        view.findViewById(R.id.collection_center).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getContext(), CollectionCenterActivity.class);
//                startActivity(intent);
//            }
//        });
//        view.findViewById(R.id.fertilizer_center).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getContext(), FertilizerCenterActivity.class);
//                startActivity(intent);
//            }
//        });
        offersImg=(ImageView)view.findViewById(R.id.offers);
        albumList = new ArrayList<>();
        prepareAlbums();
//         recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        KnowledgeZoneCategoryAdapter adapter = new KnowledgeZoneCategoryAdapter(getActivity(),albumList);
//        recyclerView.setHasFixedSize(true);
//
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
//      //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(4), true));
//       recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);

        gridView = (GridView)view.findViewById(R.id.gridview);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {


                SharedPreferences pref1 = getContext().getSharedPreferences("DATA", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = pref1.edit();
                editor1.putString("Id", String.valueOf(getCategoryList.get(position).getId()));
                editor1.putString("name", String.valueOf(getCategoryList.get(position).getName()));// Saving string data of your editext
                editor1.commit();// commit and

                Intent intent =new Intent(getContext(), EncyclopediaActivity.class);
                intent.putExtra("Id", getCategoryList.get(position).getId());
                intent.putExtra("name", getCategoryList.get(position).getName());
                Log.d(TAG, "Id kiran: "+ getCategoryList.get(position).getId());
                Log.d(TAG, "Id kiran name: "+ getCategoryList.get(position).getName());
                startActivity(intent);
           //     Album book = albumList.get(position);
               // book.toggleFavorite();

                // This tells the GridView to redraw itself
                // in turn calling your BooksAdapter's getView method again for each cell
//                knowledgeZoneBaseAdapter.notifyDataSetChanged();
            }
        });

//        recyclerView = (RecyclerView)view.findViewById(R.id.gridview);
//        //  recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();
        dail = (ImageButton)view.findViewById(R.id.dail);

        dail.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {


                // show() method display the toast with message
                // "clicked"
            /*    Toast.makeText(getContext(), "clicked", Toast.LENGTH_LONG)
                        .show();*/

                // Use format with "tel:" and phoneNumber created is
                // stored in u.
                Uri u = Uri.parse("tel:" + "123456789");

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try
                {
                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    // show() method display the toast with
                    // exception message.
                    Toasty.error(getContext(), "SecurityException", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        Getstate();

//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        // TODO Handle item click
//
//                        if(position==0){
//                            int pos = new ArrayList<String>(Arrays.asList(strArray)).indexOf("1004");
//                            Intent intent = new Intent(getActivity(), EncyclopediaActivity.class);
//                            intent.putExtra("loadsPosition",strArray[0]);
//                            startActivity(intent);
//                        }else  if(position==1){
//                            Intent intent = new Intent(getActivity(), EncyclopediaActivity.class);
//                            intent.putExtra("loadsPosition",id);
//                            startActivity(intent);
//                        } else  if(position==2){
//                            Intent intent = new Intent(getActivity(), EncyclopediaActivity.class);
//                            intent.putExtra("loadsPosition",id);
//                            startActivity(intent);
//                        }
//                        else  if(position==3){
//                            Intent intent = new Intent(getActivity(), EncyclopediaActivity.class);
//                            intent.putExtra("loadsPosition",id);
//                            startActivity(intent);
//                        }
//                        else  if(position==4){
//                            Intent intent = new Intent(getActivity(), EncyclopediaActivity.class);
//                            intent.putExtra("loadsPosition",id);
//                            startActivity(intent);
//                        } else   if(position==5){
//                            Intent intent = new Intent(getActivity(), EncyclopediaActivity.class);
//                            intent.putExtra("loadsPosition",id);
//                            startActivity(intent);
//                        }
//                    }
//                }));
        GetBanner();
        getCategory();
        return view;
    }
    private void getCategory() {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        MyServices service = ServiceFactory.createRetrofitService(getActivity(), MyServices.class);
        mRegisterSubscription = service.GetActiveLookUp(APIConstants.LookUpCategory)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetLookUpModel>() {
                    @Override
                    public void onCompleted() {
                        // Toast.makeText(getActivity(), "check", Toast.LENGTH_SHORT).show();
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }

                        if (e instanceof HttpException) {
                            ((HttpException) e).getStatusCode();
                            ((HttpException) e).getMessage();
                         //   ((HttpException) e).get.response().errorBody();
                            /*try {
                              //  ((HttpException) e).getStackTrace();response().errorBody().string();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();*/
                        }
                        Toasty.error(getActivity(), "fail", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(GetLookUpModel getLookUpModel) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Log.d(TAG, "onNext: "+getLookUpModel);
                        getCategoryList = getLookUpModel.getListResult();
                        final KnowledgeZoneBaseAdapter knowledgeZoneBaseAdapter = new KnowledgeZoneBaseAdapter(getActivity(), getLookUpModel.getListResult());
                        gridView.setAdapter(knowledgeZoneBaseAdapter);

                    }
                });

    }
    private void Getstate() {
        //  String id="APWGBDAB00010001";Id kiran

        String Id = "9";

       String url = BASE_URL+"GetActiveLookUp/" + Id;
      //  String url="http://183.82.103.171:9096/API/api/GetActiveLookUp/9";


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "RESPONSE Home======" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG, "RESPONSE======" + jsonObject);
                    String success = jsonObject.getString("isSuccess");
                    Log.d(TAG, "success======" + success);


                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    parseData(alsoKnownAsArray);

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
                    Toasty.error(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Log.i("two:" + TAG, error.toString());
                    Toasty.error(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Log.i("three:" + TAG, error.toString());
                    Toasty.error(getContext(), "AuthFailure Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Log.i("four::" + TAG, error.toString());
                    Toasty.error(getContext(), "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Log.i("five::" + TAG, error.toString());
                    Toasty.error(getContext(), "NoConnection Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Log.i("six::" + TAG, error.toString());
                    Toasty.error(getContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
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
    private void GetBanner() {
        //  String id="APWGBDAB00010001";



        String url = BASE_URL+"Banner";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "RESPONSE Banner======" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG, "RESPONSE======" + jsonObject);
                    String success = jsonObject.getString("isSuccess");
                    Log.d(TAG, "success======" + success);
                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                        String alsoKnown = alsoKnownAsArray.getString(i);
                        JSONObject leagueData = alsoKnownAsArray.getJSONObject(i);
                        String banner = leagueData.getString("bannerImageUrl");
                        Log.v("TAG --banner", banner);
                        // Image link from internet
                        // Image link from internet
                        new DownloadImageFromInternet(offersImg)
                                .execute(banner);
                        //  parseData(alsoKnownAsArray);
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
                    Toasty.error(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Log.i("two:" + TAG, error.toString());
                    Toasty.error(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Log.i("three:" + TAG, error.toString());
                    Toasty.error(getContext(), "AuthFailure Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Log.i("four::" + TAG, error.toString());
                    Toasty.error(getContext(), "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Log.i("five::" + TAG, error.toString());
                    Toasty.error(getContext(), "NoConnection Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Log.i("six::" + TAG, error.toString());
                    Toasty.error(getContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
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
    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
          //  Toast.makeText(getContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }


    //This method will parse json data
    private void parseData(JSONArray array){
        int[] covers = new int[]{
                R.drawable.encylopedia,
                R.drawable.warehouse,
                R.drawable.warehouse,
                R.drawable.category1,
                R.drawable.category2,
                R.drawable.category3,
                /*  R.drawable.album5,
                  R.drawable.album6,
                  R.drawable.album7,
                  R.drawable.album8,
                  R.drawable.album9,
                  R.drawable.album10,
                  R.drawable.album11*/};
        for(int i = 0; i<array.length(); i++) {
            SuperHeroes superHero = new SuperHeroes();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                 id = json.getString("id");
                Log.d(TAG, "id======" + id);
                strArray=new String[] {id};
                superHero.setName(json.getString("name"));

                superHero.setImageId( R.drawable.encylopedia);
               /* superHero.setImageId( R.drawable.warehouse);
                superHero.setImageId( R.drawable.warehouse);
                superHero.setImageId( R.drawable.category1);
                superHero.setImageId( R.drawable.category2);*/

                //                superHero.setRank(json.getInt(Config.TAG_RANK));
              //  superHero.setRealName(json.getString(Config.TAG_REAL_NAME));
                //   superHero.setCreatedBy(json.getString(Config.TAG_CREATED_BY));
                // superHero.setFirstAppearance(json.getString(Config.TAG_FIRST_APPEARANCE));

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
        adapter = new CardAdapter(listSuperHeroes, getContext());

        //Adding adapter to recyclerview
//        recyclerView.setAdapter(adapter);
    }
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.encylopedia,
                R.drawable.warehouse,
                R.drawable.warehouse,
                R.drawable.category1,
                R.drawable.category2,
                R.drawable.category3,
                /*  R.drawable.album5,
                  R.drawable.album6,
                  R.drawable.album7,
                  R.drawable.album8,
                  R.drawable.album9,
                  R.drawable.album10,
                  R.drawable.album11*/};
        Album a = new Album("SICKLE" ,  R.string.knowledge_zone,covers[0], "The blade is heavier than that of a normal sickle", "450mm");
        albumList.add(a);

        a = new Album("Grub Hoe",  R.string.collection_center, covers[1], "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
        albumList.add(a);

        a = new Album("panga",  R.string.fertilizer_center, covers[2],"Convenient access to all your gear", " 5.2 pounds");
        albumList.add(a);

        a = new Album("Rake",  R.string.category1,covers[3], "a long-handled tool with a row of teeth at its head", "58 wagons");
        albumList.add(a);

        a = new Album("SICKLE" ,  R.string.category2,covers[4], "The blade is heavier than that of a normal sickle", "450mm");
        albumList.add(a);

        a = new Album("Grub Hoe",  R.string.category3, covers[5], "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
        albumList.add(a);
//        a = new Album("SICKLE" ,  R.string.category2,covers[4], "The blade is heavier than that of a normal sickle", "450mm");
//        albumList.add(a);
//
//        a = new Album("Grub Hoe",  R.string.category3, covers[5], "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
//        albumList.add(a);

       /* a = new Album("Honeymoon",  covers[4]);
        albumList.add(a);
*/
      /*  a = new Album("I Need a Doctor",  covers[5]);
        albumList.add(a);

        a = new Album("Loud",  covers[6]);
        albumList.add(a);

        a = new Album("Legend",  covers[7]);
        albumList.add(a);

        a = new Album("Hello", covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", covers[9]);
        albumList.add(a);*/

        //adapter.notifyDataSetChanged();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add("Menu item")
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("Krishna")
                .setActionView(R.layout.toolbar)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
