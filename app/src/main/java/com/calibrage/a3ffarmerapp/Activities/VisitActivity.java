package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.calibrage.a3ffarmerapp.Adapters.VisitAdapter;
import com.calibrage.a3ffarmerapp.Model.visitModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;


public class VisitActivity extends AppCompatActivity {
    private ArrayList<visitModel> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        productList = new ArrayList<visitModel>();
        ListView lview = (ListView) findViewById(R.id.listview);
        VisitAdapter adapter = new VisitAdapter(this, productList);
        lview.setAdapter(adapter);
        lview.setBackgroundColor(Color.WHITE);
        populateList();

        adapter.notifyDataSetChanged();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String plotId = ((TextView)view.findViewById(R.id.plotId)).getText().toString();
                String farmerId = ((TextView)view.findViewById(R.id.farmerId)).getText().toString();
                String farmerName = ((TextView)view.findViewById(R.id.farmerName)).getText().toString();
                String plotArea = ((TextView)view.findViewById(R.id.plotArea)).getText().toString();

                String plotStatus = ((TextView)view.findViewById(R.id.plotStatus)).getText().toString();
                String plotVillage = ((TextView)view.findViewById(R.id.plotVillage)).getText().toString();
                String plotMandal = ((TextView)view.findViewById(R.id.plotMandal)).getText().toString();
                String plotDistrict = ((TextView)view.findViewById(R.id.plotDistrict)).getText().toString();
                String comments = ((TextView)view.findViewById(R.id.comments)).getText().toString();
                String dateOfRequests = ((TextView)view.findViewById(R.id.dateOfRequests)).getText().toString();


               /* Toast.makeText(getApplicationContext(),
                        "S no : " + sno +"\n"
                                +"Product : " + product +"\n"
                                +"Category : " +category +"\n"
                                +"Price : " +price, Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    private void populateList() {

        visitModel item1, item2, item3, item4, item5;

        item1 = new visitModel("", "", "", "","","","","","","");
        productList.add(item1);

       /* item2 = new Model("2", "Orange (Sunkist navel)", "Fruits", "₹. 100");
        productList.add(item2);

        item3 = new Model("3", "Tomato", "Vegetable", "₹. 50");
        productList.add(item3);

        item4 = new Model("4", "Carrot", "Vegetable", "₹. 80");
        productList.add(item4);

        item5 = new Model("5", "Banana (Cavendish)", "Fruits", "₹. 100");
        productList.add(item5);*/
    }

}

