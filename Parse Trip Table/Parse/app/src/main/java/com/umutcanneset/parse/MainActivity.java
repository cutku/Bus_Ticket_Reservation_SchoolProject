package com.umutcanneset.parse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText usernameText, passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usernameText = findViewById(R.id.sign_up_activity_name_text);
        passwordText = findViewById(R.id.sign_up_activity_password_text);


        ParseUser parseUser=ParseUser.getCurrentUser();

        if(parseUser !=null){
            Intent intent= new Intent(getApplicationContext(),FeedActivity.class);
            startActivity(intent);
        }

    }
    public void signIn(View view){


        ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Welcome "+user.getUsername(),Toast.LENGTH_SHORT).show();

                    //intent
                    Intent intent= new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void signUp(View view){

        ParseUser user= new ParseUser();
        user.setUsername(usernameText.getText().toString());
        user.setPassword(usernameText.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"User Created!",Toast.LENGTH_SHORT).show();
                    // intent
                    Intent intent= new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}


        // DB DB DB DB DB DB DB

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

        /*
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

        */


        /*

        ParseQuery<ParseObject> query= ParseQuery.getQuery("Trips");
        query.whereEqualTo("from","Ankara");
        //query.whereLessThan("calories", 130) --- belli bir integer altındaki değerleri çekmek için
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e !=null){
                    e.printStackTrace();

                }else{

                    if(objects.size()>0){
                        for(ParseObject object: objects){
                            String objectFrom=object.getString("from");
                            String objectWhere= object.getString("where");

                            System.out.println("Object from: "+ objectFrom);
                            System.out.println("Object where: "+ objectWhere);

                        }
                    }
                }
            }
        });

        */


        // USER USER USER USER USER USER USER


        /*

        ParseUser user= new ParseUser();
        user.setUsername("Umutcan");
        user.setPassword("12345");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }else{
                    Toast.makeText(MainActivity.this, "User Signed Up!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        */

        /*

        ParseUser.logInInBackground("Umutcan", "12345", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if(e!=null){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "Welcome: "+user.getUsername(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        */


