package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp2 extends AppCompatActivity {

    Button back,submit;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

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
                Toast.makeText(SignUp2.this, "Successfully Submitted", Toast.LENGTH_LONG).show(); //a toast

                Intent back = new Intent(SignUp2.this, LoginPage.class);
                startActivity(back);

                finish();
            }
        });

        //email and password
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.passwd);

        email.addTextChangedListener(submitTextWatcher);
        password.addTextChangedListener(submitTextWatcher);
    }

    private TextWatcher submitTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String emailtxt = email.getText().toString().trim();
            String passwd = password.getText().toString().trim();

            submit.setEnabled(!emailtxt.isEmpty() && !passwd.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
