package com.umutcanneset.parse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class FeedActivity extends AppCompatActivity {



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
    }
}
