package com.example.portomoti.trustbusse301project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    //String objectId = getIntent().getStringExtra("objectId");
    Button buy;
    TextView cardnumber,cvc,name,surname;
    String newString;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        buy = findViewById(R.id.activityPaymentPayButton);
        cardnumber = findViewById(R.id.activityPaymentCardNumber);
        cvc= findViewById(R.id.activityPaymentCVC);
        surname = findViewById(R.id.activityPaymentSurname);
        name = findViewById(R.id.activityPaymentName);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
                name.setText(newString,TextView.BufferType.EDITABLE);
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

    }

    public void makePayment(View view){


        ParseQuery<ParseObject> query= ParseQuery.getQuery("creditCard");
        query.whereEqualTo("name",name.getText().toString()); // diğerlerine de eklencek ---->>> 9:31Pm eklenince Silincek
        query.whereEqualTo("surname",surname.getText().toString());
        query.whereEqualTo("cvc",cvc.getText().toString());
        query.whereEqualTo("creditCardNumber",cardnumber.getText().toString());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }else{

                    ParseUser usr= ParseUser.getCurrentUser();
                    ParseObject obj = new ParseObject("ticketUser");
                    obj.put("email",usr.getEmail());
                    obj.put("from","Istanbul");
                    obj.put("destination","Ankara");

                    obj.saveInBackground();
                    Toast.makeText(getApplicationContext(),"OKKKKK",Toast.LENGTH_LONG).show();
                }
            }
        });





    }




}
