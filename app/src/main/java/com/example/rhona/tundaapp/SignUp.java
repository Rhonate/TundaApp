package com.example.rhona.tundaapp;

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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    Button submit;
    private static EditText fnametext, lnametext, tele, location, Seller_email, Seller_password;
    private static String fname, lname, phone, address, seller_email, seller_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //name tele loaction edittext
        fnametext=(EditText)findViewById(R.id.fnametxt);
        lnametext=(EditText)findViewById(R.id.lnametxt);
        tele=(EditText)findViewById(R.id.teletxt);
        location=(EditText)findViewById(R.id.locationtxt);
        Seller_email=(EditText)findViewById(R.id.email);
        Seller_password=(EditText)findViewById(R.id.passwd);

        fnametext.addTextChangedListener(nextTextWatcher);
        lnametext.addTextChangedListener(nextTextWatcher);
        tele.addTextChangedListener(nextTextWatcher);
        location.addTextChangedListener(nextTextWatcher);
        Seller_email.addTextChangedListener(nextTextWatcher);
        Seller_password.addTextChangedListener(nextTextWatcher);

        //submit button
        submit=(Button)findViewById(R.id.submitbtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Seller_email.getText().toString();

                String validemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

//                if (email.matches(validemail)) {
//
//
//                    Toast.makeText(SignUp2.this, "Successfully Submitted", Toast.LENGTH_LONG).show(); //a toast
//
//                    Intent back = new Intent(SignUp2.this, LoginPage.class);
//                    startActivity(back);
//
//                    finish();
//                } else {
//
//                    Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_LONG).show();
//                }

                Call<ResponseBody> call = RetrofitClient
                        .getmInstance()
                        .getApi()
                        .addSeller(fname, lname, phone, address, seller_email, seller_password);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                        try {
                            String url = response.body().string();
                            Toast.makeText(SignUp.this, url, Toast.LENGTH_LONG).show();
//
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

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
            seller_email = Seller_email.getText().toString().trim();
            seller_password = Seller_password.getText().toString().trim();

            submit.setEnabled(!fname.isEmpty() && !lname.isEmpty() && !phone.isEmpty() && !address.isEmpty() && !seller_email.isEmpty() && !seller_password.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
