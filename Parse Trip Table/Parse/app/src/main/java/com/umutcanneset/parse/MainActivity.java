package com.umutcanneset.parse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*

        ParseObject object= new ParseObject("Trips");
        object.put("from","Ankara");
        object.put("where","İzmir");
        object.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Object saved",Toast.LENGTH_LONG).show();
                }
            }
        });

        */

        ParseQuery<ParseObject> query=ParseQuery.getQuery("Trips");
        query.getInBackground("3yDRK5xny6", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }else{
                    String objectFrom= object.getString("from");
                    String objectWhere=object.getString("where");

                    System.out.println("Object from: "+objectFrom);
                    System.out.println("Object where: "+objectWhere);
                }
            }
        });

    }
}
