package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;

public class AddTripActivity extends AppCompatActivity {
    Spinner fromSpinner,whereSpinner;
    EditText dateText;
    String Cities[] = {"Istanbul", "Ankara", "Izmir", "Diyarbakır"};
    ArrayAdapter<String> adapter;
    Button calenderButton;
    String recordFrom = "";
    String recordWhere = "";

    String DateALL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);





        //calender activity get date from calender
        TextView dateTimeText = findViewById(R.id.addTripActivityTextView);

        String calenderDate = getIntent().getStringExtra("calenderDate");
        if (calenderDate!= null) {
            dateTimeText.setText(calenderDate + " 12:00");
                    DateALL = calenderDate + " 12:00"; //now only add 12:00 clock
        }

        //calender Activity End
        fromSpinner = findViewById(R.id.addTripActivityFromSpinner);
        whereSpinner = findViewById(R.id.addTripActivityWhereSpinner);
        calenderButton = findViewById(R.id.addTripActivityDateButton);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Cities);

        fromSpinner.setAdapter(adapter);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        recordFrom = "Istanbul";
                        break;
                    case 1:
                        recordFrom = "Ankara";
                        break;
                    case 2:
                        recordFrom = "Izmir";
                        break;
                    case 3:
                        recordFrom = "Diyarbakır";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        whereSpinner.setAdapter(adapter);
        whereSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        recordWhere = "Istanbul";
                        break;
                    case 1:
                        recordWhere = "Ankara";
                        break;
                    case 2:
                        recordWhere = "Izmir";
                        break;
                    case 3:
                        recordWhere = "Diyarbakır";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void goCalender(View view){
        Intent intent = new Intent(getApplicationContext(), CalenderActivity.class);
        startActivity(intent);
    }


    public void addTrip(View view) throws java.text.ParseException {

     //   String textFrom = fromText.getText().toString();
     //   String textDestionation = destinationText.getText().toString();
      //  String textDate = dateText.getText().toString();

//        Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(textDate);


        //IF EKLENCEK AYNI DESTİNATIONA IZIN VERİLMEYECEK.


        ParseObject object = new ParseObject("Trips");
        object.put("from", recordFrom);
        object.put("destination", recordWhere);
        object.put("date", DateALL);
        object.put("delete",false);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Trip uploaded", Toast.LENGTH_LONG).show();


                    ParseUser usr = ParseUser.getCurrentUser();

                    int usrType = usr.getInt("userType");
//HATA

                    //intent for Admin Login
                    if (usrType == 1) {
                        //intent
                        Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                        startActivity(intent);

                    }
                    //intent for Manager Login
                    else if (usrType == 2) {
                        //intent
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "User Type Undefined", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}