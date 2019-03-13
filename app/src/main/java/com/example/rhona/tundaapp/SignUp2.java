package com.example.rhona.tundaapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class SignUp2 extends AppCompatActivity {

    private static String seller_email, seller_password;
    Button back,submit;
    EditText Seller_email, Seller_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        //email and password
        Seller_email=(EditText)findViewById(R.id.email);
        Seller_password=(EditText)findViewById(R.id.passwd);

        //back button
        back=(Button)findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SignUp2.this, SignUp.class);
                startActivity(back);

                finish();
            }
        });

        //submit button
        submit=(Button)findViewById(R.id.submitbtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Seller_email.getText().toString();

                String validemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

//                if (email.matches(validemail)) {

                Intent intent = getIntent();
                String fname = intent.getStringExtra("firstname");
                String lname = intent.getStringExtra("lastname");
                String phone = intent.getStringExtra("phone");
                String address = intent.getStringExtra("address");
//
                    Toast.makeText(SignUp2.this, fname+"\n"+lname+"\n"+phone+"\n"+address+"\n"+seller_email+"\n"+seller_password, Toast.LENGTH_LONG).show(); //a toast
//
//                    Intent back = new Intent(SignUp2.this, LoginPage.class);
//                    startActivity(back);
//
//                    finish();
//                } else {
//
//                    Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_LONG).show();
//                }


            }
        });

        //Log.i(" ",fname);

        Seller_email.addTextChangedListener(submitTextWatcher);
        Seller_password.addTextChangedListener(submitTextWatcher);
}

    private TextWatcher submitTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            seller_email = Seller_email.getText().toString().trim();
            seller_password = Seller_password.getText().toString().trim();

            submit.setEnabled(!seller_email.isEmpty() && !seller_password.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
//https://www.thecrazyprogrammer.com/2016/12/pass-data-one-activity-another-in-android.html