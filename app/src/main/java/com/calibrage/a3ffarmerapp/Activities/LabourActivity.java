package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.calibrage.a3ffarmerapp.Adapters.SpinnerAdapter;
import com.calibrage.a3ffarmerapp.Model.StateVO;
import com.calibrage.a3ffarmerapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.integration.android.IntentIntegrator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import es.dmoral.toasty.Toasty;
import fr.ganfra.materialspinner.MaterialSpinner;


public class LabourActivity extends AppCompatActivity implements OnItemSelectedListener {
   Calendar myCalendar;
    EditText edittext;
    EditText chooseTime,ageTxt,villageTxt;
    List<String> categories;
    TextInputEditText age,village;
    final String[] select_labour_type = {
             "hired labour","farm labour "};
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent =new Intent(getApplicationContext(),LabourRecommendationsActivity.class);
              startActivity(intent);
            }
        });
        myCalendar = Calendar.getInstance();
      /*  String[] ITEMS = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        categories = new ArrayList<String>();
        categories.add("APWGT13718234003");
        categories.add("APWGT13718234004");
        categories.add("APWGT13718234005");
        categories.add("APWGT13718234006");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      Spinner  spinner1 = (MaterialSpinner) findViewById(R.id.spinner);
        spinner1.setAdapter(adapter);*/

        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.spinner);
        list.add("hired labour");
        list.add("farm labour");


        //set items to spinner from list
        multiSelectionSpinner.setItems(list);

    //    spinner.setAdapter(arrayAdapter);
      /*  ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < select_labour_type.length; i++) {

            StateVO stateVO = new StateVO();
            stateVO.setTitle(select_labour_type[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }
        SpinnerAdapter myAdapter = new SpinnerAdapter(LabourActivity.this, 0,
                listVOs);
        spinner.setAdapter(myAdapter);*/


        Spinner frequencySpinner = (Spinner) findViewById(R.id.frequency);

        // Spinner click listener
        frequencySpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories3 = new ArrayList<String>();
        categories3.add("1 week");
        categories3.add("2 week");
        categories3.add("3 week");
        categories3.add("4 week");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        frequencySpinner.setAdapter(dataAdapter3);

        Spinner labourSpinner = (Spinner) findViewById(R.id.labour_duration);

        // Spinner click listener
        labourSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories2 = new ArrayList<String>();
        categories2.add("3 months");
        categories2.add("6 months");
        categories2.add("9 months");
        categories2.add("12 months");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        labourSpinner.setAdapter(dataAdapter2);

       /* ageTxt= (EditText) findViewById(R.id.ageEditText);
        villageTxt= (EditText) findViewById(R.id.villageEditText);*/
         edittext= (EditText) findViewById(R.id.date_display);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
/*edittext.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        new DatePickerDialog(LabourActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        return false;
    }
});*/
edittext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        new DatePickerDialog(LabourActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      //  return false;
    }
});

        /* chooseTime = findViewById(R.id.time);
         chooseTime.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View view, MotionEvent motionEvent) {
                 TimePickerDialog timePickerDialog = new TimePickerDialog(LabourActivity.this, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                         chooseTime.setText(hourOfDay + ":" + minutes);
                     }
                 }, 0, 0, false);
                 timePickerDialog.show();
                 return false;
             }
         });*/
        Button buttonBarCodeScan = findViewById(R.id.buttonSubmit);


        buttonBarCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initiate scan with our custom scan activity
           //   finish();
                Toasty.success(getApplicationContext(), "Labour Request Submitted Successfully", Toast.LENGTH_LONG).show();
                LabourActivity.this.finish();


            }
        });
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
        textviewTitle.setText(R.string.labour);
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
       /* ageTxt.setText("25");
        villageTxt.setText("Markook, Telangana");*/

        // Showing selected spinner item
      //  Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
      /*  ageTxt.setText("");
        villageTxt.setText("");*/
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}

