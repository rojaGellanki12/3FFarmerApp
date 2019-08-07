package com.calibrage.a3ffarmerapp.Activities;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.calibrage.a3ffarmerapp.Adapters.AlbumsAdapter;
import com.calibrage.a3ffarmerapp.Model.Album;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;
import java.util.List;

public class PoleActivity extends AppCompatActivity {


    TextView mealTotalText;
    private RecyclerView storedOrders;
    private AlbumsAdapter adapter;

    private List<Album> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pole);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                startActivity(intent);
            }
        });
        storedOrders=(RecyclerView)findViewById(R.id.selected_food_list);


        mealTotalText = (TextView)findViewById(R.id.meal_total);

        orders = new ArrayList<>();
        adapter = new AlbumsAdapter(this, orders);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        storedOrders.setLayoutManager(mLayoutManager);
        storedOrders.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        storedOrders.setItemAnimator(new DefaultItemAnimator());
        storedOrders.setAdapter(adapter);
        //  adapter.registerDataSetObserver(observer);
        //   adapter.registerAdapterDataObserver(observer);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                setMealTotal();
            }
        });
        getListItemData();
        Button buttonBarCodeScan = findViewById(R.id.confirm);
       // buttonBarCodeScan.setTypeface(faceBold);

        buttonBarCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initiate scan with our custom scan activity
              /*  Intent intent =new Intent(getApplicationContext(),OrderPlacedActivity.class);
                startActivity(intent);
             */

                startActivity(new Intent(PoleActivity.this, OrderPlacedActivity.class));
                finish();
            }
        });
      //  prepareAlbums();

        /*try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
      //  DisplayActionBar();
    }
    private void getListItemData() {
        orders.clear();
        int[] covers = new int[]{
                R.drawable.sickle,
                R.drawable.grub,
                R.drawable.panga,
                R.drawable.rake,
                /*  R.drawable.album5,
                  R.drawable.album6,
                  R.drawable.album7,
                  R.drawable.album8,
                  R.drawable.album9,
                  R.drawable.album10,
                  R.drawable.album11*/};

        Album a = new Album("SICKLE" ,300,covers[0],"The blade is heavier than that of a normal sickle", "450mm");
        orders.add(a);

        a = new Album("Grub Hoe",   400,covers[1], "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
        orders.add(a);
        a = new Album("panga", 200, covers[2],"Convenient access to all your gear", " 5.2 pounds");
        orders.add(a);

        a = new Album("Rake", 500, covers[3], "a long-handled tool with a row of teeth at its head", "58 wagons");
        orders.add(a);
        adapter.notifyDataSetChanged();

        //  adapter.registerDataSetObserver(observer);

    }

    public int calculateMealTotal(){
        int mealTotal = 0;
        for(Album order : orders){
            mealTotal += order.getmAmount() * order.getmQuantity();
            Log.e("mealTotal==", String.valueOf(order.getmAmount()));
            Log.e("mealTotal==", String.valueOf(order.getmQuantity()));
        }
        return mealTotal;
    }
    public void setMealTotal(){
        mealTotalText.setText("Rs"+" "+ calculateMealTotal());
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
        textviewTitle.setText(R.string.pole);
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

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
   /* private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.sickle,
                R.drawable.grub,
               R.drawable.panga,
                R.drawable.rake,
              *//*  R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11*//*};

        Album a = new Album("SICKLE" , covers[0], 100, "The blade is heavier than that of a normal sickle", "450mm");
        orders.add(a);

        a = new Album("Grub Hoe",  covers[1], 200, "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
        orders.add(a);

        a = new Album("panga", covers[2], 200, "Convenient access to all your gear", " 5.2 pounds");
        orders.add(a);

        a = new Album("Rake", covers[3], 500, "a long-handled tool with a row of teeth at its head", "58 wagons");
        orders.add(a);

       *//* a = new Album("Honeymoon",  covers[4]);
        albumList.add(a);
*//*
      *//*  a = new Album("I Need a Doctor",  covers[5]);
        albumList.add(a);

        a = new Album("Loud",  covers[6]);
        albumList.add(a);

        a = new Album("Legend",  covers[7]);
        albumList.add(a);

        a = new Album("Hello", covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", covers[9]);
        albumList.add(a);*//*

        adapter.notifyDataSetChanged();
    }*/

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
