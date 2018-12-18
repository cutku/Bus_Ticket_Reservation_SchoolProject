package com.example.portomoti.trustbusse301project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class DeleteManagerActivity extends AppCompatActivity {

    EditText managerEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_manager);

        managerEmail =findViewById(R.id.deleteManagerActivityManagerEmailText);


    }


    public void deleteManager(View view){

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("email", managerEmail);


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            String objectName = object.getString("username");
                            String objectCalories = object.getString("userSurname");

                            System.out.println("object name: " + objectName);
                            System.out.println("object calories" + objectCalories);
                        }
                    }
                }
            }
        });

        }
    }

