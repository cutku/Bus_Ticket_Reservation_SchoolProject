package com.example.trustbuss.trustbuss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
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

    EditText emailText, passwordText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = findViewById(R.id.editText_email);
        passwordText = findViewById(R.id.editText_password);


    }

    public void signUp(View view) {
    }


    public void signIn(View view) {
        ParseUser user = new ParseUser();

        user.setEmail(emailText.getText().toString());
        user.setPassword(passwordText.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG).show();
                }
            }
        });


    //Database Database Database Database Database Database Database

/*
        //write object
        ParseObject object = new ParseObject("Fruits");

        object.put("name", "banana");
        object.put("calories",150);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Object saved",Toast.LENGTH_LONG).show();
                }
            }
        });
        */

        /*
        //get spesific object

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Fruits");

        query.getInBackground("66Akoq1OUs", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    String objectName = object.getString("name");
                    int objectCalories = object.getInt("calories");

                    System.out.println("object name: " + objectName);
                    System.out.println("object calories" + objectCalories);
                }
            }
        });
        */

        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Fruits");
        //spesific query example

        //query.whereEqualTo("name","banana");

        //query.whereLessThan("calories","130");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    if(objects.size() > 0 ){
                        for (ParseObject object:objects ){
                            String objectName = object.getString("name");
                            int objectCalories = object.getInt("calories");

                            System.out.println("object name: " + objectName);
                            System.out.println("object calories" + objectCalories);
                        }
                    }
                }
            }
        });
        */

    // User User User User User User User User User User User User User User

        /*
        ParseUser user = new ParseUser();
        user.setUsername("James");
        user.setPassword("123456");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    e.printStackTrace();
                }
                else{
                    Toast.makeText(MainActivity.this, "User Signed Up!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */
        /*

        ParseUser.logInInBackground("James", "12456", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Welcome: " + user.getUsername(), Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

}

        public void sendMessage (View view){

            //response to sign in button - to PUSH login information

        }


}