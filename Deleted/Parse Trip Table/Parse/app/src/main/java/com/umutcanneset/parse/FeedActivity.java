package com.umutcanneset.parse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

ListView listView;
ArrayList<String> fromFromParse;
ArrayList<String> whereFromParse;
ArrayList<Date> dateFromParse;
    PostClass postClass;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       if(item.getItemId()==R.id.add_trip){
           //intent

           Intent intent= new Intent(getApplicationContext(),UploadActivity.class);
           startActivity(intent);
       }else if(item.getItemId()==R.id.signout){
           ParseUser.logOutInBackground(new LogOutCallback() {
               @Override
               public void done(ParseException e) {
                   if(e!=null){
                       Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                   }else{
                       Intent intent= new Intent(getApplicationContext(),MainActivity.class);
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
        setContentView(R.layout.activity_feed);

        listView=findViewById(R.id.listView);

            fromFromParse= new ArrayList<>();
            whereFromParse=new ArrayList<>();
            dateFromParse= new ArrayList<>();

            postClass= new PostClass(fromFromParse,whereFromParse,dateFromParse,this);

            listView.setAdapter(postClass);

            download();
    }
    public void download(){
        ParseQuery<ParseObject> query= ParseQuery.getQuery("Trips");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }else{

                    if(objects.size()>0){
                        for(ParseObject object: objects){


                            fromFromParse.add(object.getString("from"));
                            whereFromParse.add(object.getString("where"));
                            dateFromParse.add(object.getDate("date"));

                            postClass.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }
}
