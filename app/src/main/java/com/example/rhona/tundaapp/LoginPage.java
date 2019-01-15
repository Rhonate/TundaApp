package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    TextView signup;
    private Button login;
    private RadioGroup buysell;
    private RadioButton sellbuy;
    //EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        signup=(TextView)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(LoginPage.this, SignUp.class);
                startActivity(signup);

                finish();

            }
        });

        buysell=(RadioGroup)findViewById(R.id.radioGroup);

        //email and password
//        email=(EditText)findViewById(R.id.emailtxt);
//        password=(EditText)findViewById(R.id.passwordtxt);
//
//        email.addTextChangedListener(loginTextWatcher);
//        password.addTextChangedListener(loginTextWatcher);
    }

    public void checkButton(View view){

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){

            case R.id.buyerrdbtn:

                if (checked){

                    login=(Button)findViewById(R.id.loginbtn);
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent login=new Intent(LoginPage.this, ProductsList.class);
                            startActivity(login);

                            //finish();
                        }
                    });
                }

                break;

            case R.id.sellerrdbtn:

                if (checked){

                    login=(Button)findViewById(R.id.loginbtn);
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent login=new Intent(LoginPage.this, SellerHome.class);
                            startActivity(login);

                            //finish();
                        }
                    });
                }

                    break;


        }


    }

//    private TextWatcher loginTextWatcher=new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String emailtext = email.getText().toString().trim();
//            String passwordtext = password.getText().toString().trim();
//
//            login.setEnabled(!emailtext.isEmpty() && !passwordtext.isEmpty());
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };

}
