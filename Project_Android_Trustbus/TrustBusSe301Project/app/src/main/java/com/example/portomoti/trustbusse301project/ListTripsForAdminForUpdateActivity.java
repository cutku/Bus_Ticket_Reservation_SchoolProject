package com.example.portomoti.trustbusse301project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class ListTripsForAdminForUpdateActivity extends AppCompatActivity {

    ListView listViewAdmin;
    ArrayList<String> objectId;
    ArrayList<String> fromFromParse;
    ArrayList<String> whereFromParse;
    ArrayList<String> dateFromParse; //String ---> DATE
    PostActivityForAdmin postActivityAdmin ;


    ArrayAdapter<String> adapter;
    Spinner fromSpinner,whereSpinner;
    String Cities[] = {"Istanbul", "Ankara", "Izmir", "Diyarbakır"};
    Button updateTrip,updateCelanderButton;
    EditText objectIdText;

    String recordFrom = "";
    String recordWhere = "";

    String DateALL;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);


        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.Logout) {
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trips_for_admin_for_update_trip);


        //calender
        TextView dateTimeText = findViewById(R.id.listTripActivityForAdminForUpdateTripDateText);

        String calenderDate = getIntent().getStringExtra("calenderDateForUpdate");
        if (calenderDate != null) {
            dateTimeText.setText(calenderDate + " 12:00");
            DateALL = calenderDate + " 12:00"; //now only add 12:00 clock
        }
        fromSpinner = findViewById(R.id.listTripsForAdminForUpdateFromSpinner);
        whereSpinner = findViewById(R.id.listTipsActivityForAdminForUpdateDestinationSpinner);
        updateCelanderButton = findViewById(R.id.listTripForAdminFroUpdateTripDepartureDateButton);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cities);

        fromSpinner.setAdapter(adapter);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
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
                switch (position) {
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




        objectIdText = findViewById(R.id.activityListTripForAdminObjectIdTextUpdateTrip);
        updateTrip = findViewById(R.id.activityListTripsForAdminUpdateTripButton);

        listViewAdmin = findViewById(R.id.listViewAdminUpdateTrip);

        objectId= new ArrayList<>();
        fromFromParse= new ArrayList<>();
        whereFromParse=new ArrayList<>();
        dateFromParse= new ArrayList<>();

        postActivityAdmin= new PostActivityForAdmin(objectId,fromFromParse,whereFromParse,dateFromParse,this);

        listViewAdmin.setAdapter(postActivityAdmin);

        download();

    }

    public void download(){
        ParseQuery<ParseObject> query= ParseQuery.getQuery("Trips");
        query.whereEqualTo("deleted",false); // diğerlerine de eklencek ---->>> 9:31Pm eklenince Silincek
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }else{

                    if(objects.size()>0){
                        for(ParseObject object: objects){

                            objectId.add(object.getObjectId());
                            fromFromParse.add(object.getString("from"));
                            whereFromParse.add(object.getString("destination"));
                            dateFromParse.add(object.getString("date")); // String ---->getDate

                            postActivityAdmin.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    public void goCalender(View view){
        Intent intent = new Intent(getApplicationContext(), CalenderActivityUpdate.class);
        startActivity(intent);
    }
    public void updateTrip(View view) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trips");

        query.getInBackground(objectIdText.getText().toString(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    if(recordFrom != null) {
                        object.put("from", recordFrom);
                    }
                    if(recordWhere != null) {
                        object.put("destination", recordWhere);
                    }
                    if(DateALL != null) {
                        object.put("date", DateALL);
                    }

                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
