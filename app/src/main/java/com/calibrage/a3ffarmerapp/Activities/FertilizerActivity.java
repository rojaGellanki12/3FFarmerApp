package com.calibrage.a3ffarmerapp.Activities;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Adapters.AlbumsAdapter;
import com.calibrage.a3ffarmerapp.Adapters.FertilizerAdapter;
import com.calibrage.a3ffarmerapp.Model.Album;
import com.calibrage.a3ffarmerapp.Model.FertilizerModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;
import java.util.List;

public class FertilizerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    final Context context = this;
    Button button;
    TextView mealTotalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();*/
      ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mealTotalText = (TextView)findViewById(R.id.meal_total);
        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        Button buttonBarCodeScan = findViewById(R.id.confirm);
        // buttonBarCodeScan.setTypeface(faceBold);

  /*      buttonBarCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initiate scan with our custom scan activity
                Intent intent =new Intent(getApplicationContext(),OrderPlacedActivity.class);
                startActivity(intent);
            }
        });*/
        prepareAlbums();

        button = (Button) findViewById(R.id.button);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                setMealTotal();
            }
        });

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent =new Intent(getApplicationContext(), FertilizerRecomendations.class);
                startActivity(intent);

             /*   // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                RecyclerView recyclerView = promptsView.findViewById(R.id.recyclerViewFertlizer);

                FertilizerAdapter adapter = new FertilizerAdapter(getMovieList());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(linearLayoutManager);

                recyclerView.setAdapter(adapter);
*//*
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);*//*

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        // result.setText(userInput.getText());

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();*/
            }
        });
    }
    public int calculateMealTotal(){

        int mealTotal = 0;
        for(Album order : albumList){
            mealTotal += order.getmAmount() * order.getmQuantity();
            Log.e("mealTotal==", String.valueOf(order.getmAmount()));
            Log.e("mealTotal==", String.valueOf(order.getmQuantity()));
        }
        return mealTotal;
    }
    public void setMealTotal(){
        mealTotalText.setText("Rs"+" "+ calculateMealTotal());
    }
    private List<FertilizerModel> getMovieList() {
        List<FertilizerModel> movieList = new ArrayList<>();
        // src Wikipedia
        movieList.add(new FertilizerModel("CAM0003MING0", "20", "Adilabad", "1.4 hectors","Near shiavalyam","Fertilizer1","Fertilizer2","Fertilizer3"));
        movieList.add(new FertilizerModel("CAM0003MING2", "25", "Jagtial","2 hectors","Near sbi bank" ,"Fertilizer1","Fertilizer2","Fertilizer3"));
        movieList.add(new FertilizerModel("CAM0003MING3", "15", "Jangaon","3 hectors","opp:Market","Fertilizer1","Fertilizer2","Fertilizer3"));
        movieList.add(new FertilizerModel("CAM0003MING4", "22", "Kamareddy", "4 hectors","x road","Fertilizer1","Fertilizer2","Fertilizer3"));        movieList.add(new FertilizerModel("CAM0003MING0", "20", "Adilabad", "1.4 hectors","Near shiavalyam","Fertilizer1","Fertilizer2","Fertilizer3"));
        movieList.add(new FertilizerModel("CAM0003MING2", "25", "Jagtial","2 hectors","Near sbi bank" ,"Fertilizer1","Fertilizer2","Fertilizer3"));
        movieList.add(new FertilizerModel("CAM0003MING3", "15", "Jangaon","3 hectors","opp:Market","Fertilizer1","Fertilizer2","Fertilizer3"));
        movieList.add(new FertilizerModel("CAM0003MING4", "22", "Kamareddy", "4 hectors","x road","Fertilizer1","Fertilizer2","Fertilizer3"));


        return movieList;
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
   /* private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }*/

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.f1,
                R.drawable.f2,
                R.drawable.f3,
                R.drawable.f4,
                /*  R.drawable.album5,
                  R.drawable.album6,
                  R.drawable.album7,
                  R.drawable.album8,
                  R.drawable.album9,
                  R.drawable.album10,
                  R.drawable.album11*/};
        Album a = new Album("SICKLE" ,  100,covers[0], "The blade is heavier than that of a normal sickle", "450mm");
        albumList.add(a);

        a = new Album("Grub Hoe",  200, covers[1], "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
        albumList.add(a);

        a = new Album("panga",  200, covers[2],"Convenient access to all your gear", " 5.2 pounds");
        albumList.add(a);

        a = new Album("Rake",  500,covers[3], "a long-handled tool with a row of teeth at its head", "58 wagons");
        albumList.add(a);

         a = new Album("SICKLE" ,  100,covers[0], "The blade is heavier than that of a normal sickle", "450mm");
        albumList.add(a);

         a = new Album("Grub Hoe",  200, covers[1], "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
        albumList.add(a);

         a = new Album("panga",  200, covers[2],"Convenient access to all your gear", " 5.2 pounds");
        albumList.add(a);

          a = new Album("Rake",  500,covers[3], "a long-handled tool with a row of teeth at its head", "58 wagons");
        albumList.add(a);
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

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
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
