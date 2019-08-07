package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.calibrage.a3ffarmerapp.Adapters.FertilizerAdapter;
import com.calibrage.a3ffarmerapp.Adapters.RecommendationAdapter;
import com.calibrage.a3ffarmerapp.Model.FertilizerModel;
import com.calibrage.a3ffarmerapp.Model.RecommendationModel;
import com.calibrage.a3ffarmerapp.R;

        import java.util.ArrayList;
        import java.util.List;

public class FertilizerRecomendations extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer_recomendations);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),FertilizerActivity.class);
                startActivity(intent);
            }
        });
        FertilizerModel[] myListData = new FertilizerModel[] {


                new FertilizerModel("CAM0003MING0", "20", "Adilabad", "1.4 hectors","Near shiavalyam","Fertilizer1","Fertilizer2","Fertilizer3"),
        new FertilizerModel("CAM0003MING2", "25", "Jagtial","2 hectors","Near sbi bank" ,"Fertilizer1","Fertilizer2","Fertilizer3"),
        new FertilizerModel("CAM0003MING3", "15", "Jangaon","3 hectors","opp:Market","Fertilizer1","Fertilizer2","Fertilizer3"),
        new FertilizerModel("CAM0003MING4", "22", "Kamareddy", "4 hectors","x road","Fertilizer1","Fertilizer2","Fertilizer3"),


        };

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        FertilizerAdapter adapter = new FertilizerAdapter(this,myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


      //   DisplayActionBar();
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
        textviewTitle.setText("Fertilizer");
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }

}
