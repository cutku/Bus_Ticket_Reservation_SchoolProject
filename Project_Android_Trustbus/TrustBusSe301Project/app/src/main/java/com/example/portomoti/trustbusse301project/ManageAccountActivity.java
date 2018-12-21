package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ManageAccountActivity extends AppCompatActivity {

    EditText usernameText, surnameText, emailText, passwordText ,dateofBirthText, ssnText  ;
    RadioButton male, female;
    Switch freezeSwitch;
    boolean freeze;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_manage_account);


        usernameText= findViewById(R.id.signUpSignUpActivityNameText);
        surnameText = findViewById(R.id.signUpSignUpAccountActivitySurnameText);
        emailText = findViewById(R.id.signUpSignUpAccountActivityEmailText);
        dateofBirthText = findViewById(R.id.signUpSignUpAccountActivityDateOfBirthText);
        ssnText = findViewById(R.id.addManagerActivitySsnText);
        male = findViewById(R.id.managerAccountActivityMaleRadioButton);
        female = findViewById(R.id.managerAccountActivityFemaleRadioButton);
        passwordText = findViewById(R.id.manageAccountActivityPasswordText);
        freezeSwitch = findViewById(R.id.activityManageAccountFreezeSwitch);
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
        if (freezeSwitch.isChecked()==true){
            user.put("freeze" , true);
        }

        /*
        if (dateofBirthText != null){
            user.put("dateOfBirth",dateofBirthText.getText());
        }
        */
        if (male.isChecked() == true){
            user.put("sex","Male");
        }
        if ((female.isChecked() == true)){
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
