package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameText, emailText, passwordText;
    boolean freeze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  ParseUser.getCurrentUser().logOut();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        usernameText= findViewById(R.id.signUpActivityNameText);
        emailText = findViewById(R.id.signUpActivityEmailText);
        passwordText = findViewById(R.id.signupActivityPasswordText);



        /*
            //Remember User
            ParseUser parseUser = ParseUser.getCurrentUser();

            if (parseUser!= null){
                Toast.makeText(getApplicationContext(), "User Logged-In Automaticly", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(intent);
            }
        */



    }

    public void signIn (View view) {

        final ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email",emailText.getText().toString());
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if (e==null){
                    if (objects.size()>0) {
                        freeze = objects.get(0).getBoolean("freeze");
                    }
                }else{

                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                   //Toast.makeText(getApplicationContext(), "User Freezed", Toast.LENGTH_LONG).show();
                   // e.printStackTrace();

                }
            }
        });


                ParseUser.logInInBackground(emailText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        } else {


                            if (freeze==true){
                                Toast.makeText(getApplicationContext(), "User Freezed", Toast.LENGTH_LONG).show();
                                ParseUser.getCurrentUser().logOut();
                                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Welcome" + user.getUsername(), Toast.LENGTH_LONG).show();

                                int userType = user.getInt("userType");
                                /*
                                //intent This will be delete
                                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                startActivity(intent);
                                */
                                //this area will come after all functions done


                    if (userType == 0){
                        //intent
                        Intent intent = new Intent(getApplicationContext(),CustomerActivity.class);
                        startActivity(intent);

                    }
                    if (userType == 1){
                        //intent
                        Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                        startActivity(intent);

                    }
                    if (userType == 2){
                        //intent
                        Intent intent = new Intent(getApplicationContext(),ManagerActivity.class);
                        startActivity(intent);

                    }
                    else{
                        //intent
                        Intent intent = new Intent(getApplicationContext(),CustomerActivity.class);
                        startActivity(intent);

                    }



                            }



                        }


                    }
                });


         }


    public void signUp(View view) {
        ParseUser user = new ParseUser();

        user.setUsername(usernameText.getText().toString());
        user.setEmail(emailText.getText().toString());
        user.setPassword(passwordText.getText().toString());
        user.put("freeze", false);
        user.put("userType",0);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG).show();
                    //intent
                    Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
