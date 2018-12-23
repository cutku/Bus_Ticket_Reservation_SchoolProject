package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpSignUpActivity extends AppCompatActivity {

    EditText usernameTextFake, surnameText, emailText, passwordText ,dateofBirthText, ssnText,usernameText  ;
    RadioButton male, female, acceptTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  ParseUser.getCurrentUser().logOut();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_up_page);

        usernameText =  findViewById(R.id.signUpSignUpActivityNameText);
        usernameTextFake = findViewById(R.id.signUpSignUpActivityEmailText);
        surnameText = findViewById(R.id.signUpSignUpActivityActivitySurnameText);
        emailText = findViewById(R.id.signUpSignUpActivityEmailText);
        dateofBirthText = findViewById(R.id.signUpSignUpActivityDateOfBirthText);
        ssnText = findViewById(R.id.signUpSignUpActivitySSNText);
        male = findViewById(R.id.signUpSignUpAccountActivityMaleRadioButton);
        female = findViewById(R.id.signUpSignUpAccountActivityFemaleRadioButton);
        passwordText = findViewById(R.id.signUpSignUpActivityPasswordText);



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


    public void signUpSignUp(View view) {
        ParseUser user = new ParseUser();
        user.put("name",usernameText.getText().toString());
        user.setUsername(usernameTextFake.getText().toString());
        user.setEmail(emailText.getText().toString());
        user.setPassword(passwordText.getText().toString());
        user.put("userSurname", surnameText.getText().toString());
        user.put("freeze", false);
        user.put("userType", 0);

        if (male != null) {
            user.put("sex", "Male");
        }
        if ((female != null)) {
            user.put("sex", "Female");
        }

     //   if (acceptTerms != null){
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "successfully Signed Up", Toast.LENGTH_LONG).show();
                        //intent
                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intent);
                    }
                }
            });
       // }

    }
}
