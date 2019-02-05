package com.umutcanneset.parse;

import android.content.Intent;
import android.icu.text.DateTimePatternGenerator;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Date;


public class UploadActivity extends AppCompatActivity {

    EditText fromText;
    EditText whereText;
    EditText dateText;
    EditText timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        fromText= findViewById(R.id.upload_activity_from);
        whereText=findViewById(R.id.upload_activity_where);
        dateText=findViewById(R.id.upload_activity_date);

    }
    public void addTrip(View view) throws java.text.ParseException {

        String textFrom=fromText.getText().toString();
        String textWhere=whereText.getText().toString();
        String textDate=dateText.getText().toString();

        Date date1=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(textDate);

        ParseObject object=new ParseObject("Trips");
        object.put("from",textFrom);
        object.put("where",textWhere);
        object.put("date",date1);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Trip uploaded",Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
