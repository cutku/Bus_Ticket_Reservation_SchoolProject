package com.example.portomoti.trustbusse301project;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class FilterTripActivity extends AppCompatActivity {

    Spinner fromSpinner,toSpinner;
    TextView dateChosen;
    Button setDate,list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_trip);

        fromSpinner=findViewById(R.id.fromSpinner);
        toSpinner=findViewById(R.id.toSpinner);
        setDate=findViewById(R.id.setDateButton);
        list=findViewById(R.id.listButton);
        dateChosen=findViewById(R.id.dateTextView);

      setDate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              final Calendar calendar = Calendar.getInstance();
              int year = calendar.get(Calendar.YEAR);
              int month = calendar.get(Calendar.MONTH);
              int day = calendar.get(Calendar.DAY_OF_MONTH);

              DatePickerDialog dpd = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                      month+=1;
                      dateChosen.setText(dayOfMonth+ "/" +month+ "/" +year);
                  }
              },year,day,month);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Selecet",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Cancel",dpd);
                dpd.show();
          }
         });

      }
    }

