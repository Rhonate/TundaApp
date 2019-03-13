package com.example.rhona.tundaapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class SignUp extends AppCompatActivity {

    Button next;
    private static EditText fnametext, lnametext, tele, location;
    private static String fname, lname, phone, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //name tele loaction edittext
        fnametext=(EditText)findViewById(R.id.fnametxt);
        lnametext=(EditText)findViewById(R.id.lnametxt);
        tele=(EditText)findViewById(R.id.teletxt);
        location=(EditText)findViewById(R.id.locationtxt);

        fnametext.addTextChangedListener(nextTextWatcher);
        lnametext.addTextChangedListener(nextTextWatcher);
        tele.addTextChangedListener(nextTextWatcher);
        location.addTextChangedListener(nextTextWatcher);

        //submit button
        next=(Button)findViewById(R.id.signupnextbtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    fname = fnametext.getText().toString();
                    lname = lnametext.getText().toString();
                    phone = tele.getText().toString();
                    address = location.getText().toString();

                    Intent back = new Intent(SignUp.this, SignUp2.class);
                    back.putExtra("firstname", fname);
                    back.putExtra("lastname", lname);
                    back.putExtra("phone", phone);
                    back.putExtra("address", address);

                    startActivity(back);
//
//                    finish();
//
            }
        });


    }
    private TextWatcher nextTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            fname = fnametext.getText().toString().trim();
            lname = lnametext.getText().toString().trim();
            phone = tele.getText().toString().trim();
            address = location.getText().toString().trim();
            next.setEnabled(!fname.isEmpty() && !lname.isEmpty() && !phone.isEmpty() && !address.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
