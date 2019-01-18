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

        //Radio group
        buysell=(RadioGroup)findViewById(R.id.radioGroup);

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

}
