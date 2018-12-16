package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameText, emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  ParseUser.getCurrentUser().logOut();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        usernameText= findViewById(R.id.signUpActivityNameText);
        emailText = findViewById(R.id.signUpActivityEmailText);
        passwordText = findViewById(R.id.signupActivityPasswordText);


        //Remember User
        ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser!= null){
            Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
            startActivity(intent);
        }


    }

    public void signIn (View view) {
        ParseUser.logInInBackground(emailText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Welcome" + user.getUsername(), Toast.LENGTH_LONG).show();

                    int userType = user.getInt("userType");


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

        });

    }

    public void signUp(View view) {
        ParseUser user = new ParseUser();

        user.setUsername(usernameText.getText().toString());
        user.setEmail(emailText.getText().toString());
        user.setPassword(passwordText.getText().toString());

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
