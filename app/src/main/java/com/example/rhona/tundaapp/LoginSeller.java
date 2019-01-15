package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginSeller extends AppCompatActivity {

    Button login;
    TextView signup;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_specific);

        //signup textview
        signup=(TextView)findViewById(R.id.signuptxt);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(LoginSeller.this, SignUp.class);
                startActivity(signup);

                finish();
            }
        });

        //login button
        login=(Button)findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signup = new Intent(LoginSeller.this, SellerHome.class);
                startActivity(signup);

                finish();
            }
        });

        //email and password
        email=(EditText)findViewById(R.id.emailtxt);
        password=(EditText)findViewById(R.id.passwordtxt);

        email.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
    }

    private TextWatcher loginTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String emailtext = email.getText().toString().trim();
            String passwordtext = password.getText().toString().trim();

            login.setEnabled(!emailtext.isEmpty() && !passwordtext.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
