package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class SeatSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    ViewGroup layout;
    TextView numberOfSelectedSeatsText;
    int numberOfSelectedSeat=0;

    String seats = "AA____AA/"
                 + "AA____AA/"
                 + "AA____AA/"
                 + "AA____AA/"
                 + "AA____AA/"
                 + "UU____AA/"
                 + "AA____AA/"
                 + "AA____AA/"
                 + "UU____AA/"
                 + "AA____AA/"
                 + "AA____AA/"
                 + "_______/";

    List<TextView> seatViewList = new ArrayList<>();
    int seatSizeFromDatabase;
    int seatSize=44;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 4;
    int STATUS_RESERVED = 3;
    String selectedIds = "";

    String newStringSeatSelection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        layout = findViewById(R.id.layoutSeat);
        numberOfSelectedSeatsText=findViewById(R.id.textViewSelectedSeatsNumber);
        seats = "/" + seats;

        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);

        layout.addView(layoutSeat);

        LinearLayout layout = null;





        //object ID Get Capacity
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newStringSeatSelection= null;
            } else {
                newStringSeatSelection= extras.getString("STRING_I_NEED");
                ParseQuery<ParseObject> queryM = ParseQuery.getQuery("Trips");
                queryM.getInBackground(newStringSeatSelection, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e != null) {
                            e.printStackTrace();
                        } else {
                            seatSizeFromDatabase  = object.getInt("seatSizeFromDatabase");
                            System.out.println("Seatsadadsadadasdsadsadasas===" + seatSizeFromDatabase);
                        }
                    }
                });
            }
        } else {
            newStringSeatSelection= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }



        //Push/Update Capacity
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newStringSeatSelection= null;
            } else {
                newStringSeatSelection= extras.getString("STRING_I_NEED");
                ParseQuery<ParseObject> queryP = ParseQuery.getQuery("Trips");
                queryP.getInBackground(newStringSeatSelection, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e != null) {
                            e.printStackTrace();
                        } else {
                            object.put("seatSizeFromDatabase",seatSize);
                            System.out.println("obj puttet klajdklsjaskld===" + seatSizeFromDatabase);
                        }
                    }
                });
            }
        } else {
            newStringSeatSelection= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        


        int count = 0;

        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_booked);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_BOOKED);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_book);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_AVAILABLE);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_reserved);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_RESERVED);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == '_') {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.TRANSPARENT);
                view.setText("");
                layout.addView(view);
            }
        }
    }

    @Override
    public void onClick(View view) {

        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if(numberOfSelectedSeat>=4){
                Toast.makeText(getApplicationContext(),"You cannot Buy more than 4 seat",Toast.LENGTH_SHORT).show();
            }

            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.ic_seats_book);
                 numberOfSelectedSeat--;
                 numberOfSelectedSeatsText.setText(""+numberOfSelectedSeat);
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.ic_seats_selected);
                numberOfSelectedSeat++;
                numberOfSelectedSeatsText.setText(""+numberOfSelectedSeat);
            }
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }

    }
}
