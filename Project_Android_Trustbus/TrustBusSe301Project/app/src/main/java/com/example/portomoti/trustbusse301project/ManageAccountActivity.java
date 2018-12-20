package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;

public class ManageAccountActivity extends AppCompatActivity {

    EditText usernameText, surnameText, emailText, passwordText ,dateofBirthText, ssnText  ;
    RadioButton male, female;
    boolean freeze;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_manage_account);


        usernameText= findViewById(R.id.manageAccountActivityNameText);
        surnameText = findViewById(R.id.manageAccountActivitySurnameText);
        emailText = findViewById(R.id.manageAccountActivityEmailText);
        dateofBirthText = findViewById(R.id.manageAccountActivityDateOfBirthText);
        ssnText = findViewById(R.id.addManagerActivitySsnText);
        male = findViewById(R.id.managerAccountActivityMaleRadioButton);
        female = findViewById(R.id.managerAccountActivityFemaleRadioButton);
        passwordText = findViewById(R.id.manageAccountActivityPasswordText);
    }



    public void saveChanges(View view){
        ParseUser user = ParseUser.getCurrentUser();

        if(usernameText != null) {
            user.setUsername(usernameText.getText().toString());
        }
        if (surnameText != null){
            user.put("userSurname", surnameText.getText().toString());
        }
        if (passwordText!=null) {
            user.setPassword(passwordText.getText().toString());
        }
        if (emailText != null) {
            user.setEmail(emailText.getText().toString());
        }
        /*
        if (dateofBirthText != null){
            user.put("dateOfBirth",dateofBirthText.getText());
        }
        */
        if (male != null){
            user.put("sex","Male");
        }
        if ((female!=null)){
            user.put("sex","Female");
        }


//        user.put("ssn", ssnText.getText().toString());

        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_LONG).show();
                    //intent
                    Intent intent = new Intent(getApplicationContext(),CustomerActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
