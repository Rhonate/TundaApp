package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    Button next;
    EditText name, tele, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //name tele loaction edittext
        name=(EditText)findViewById(R.id.nametxt);
        tele=(EditText)findViewById(R.id.teletxt);
        location=(EditText)findViewById(R.id.locationtxt);

        name.addTextChangedListener(nextTextWatcher);
        tele.addTextChangedListener(nextTextWatcher);
        location.addTextChangedListener(nextTextWatcher);


        //next button
        next=(Button)findViewById(R.id.signupnextbtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next=new Intent(SignUp.this, SignUp2.class);
                startActivity(next);

                finish();
            }
        });



    }
    private TextWatcher nextTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nametext = name.getText().toString().trim();
            String teletext = tele.getText().toString().trim();
            String locationtext = location.getText().toString().trim();

            next.setEnabled(!nametext.isEmpty() && !teletext.isEmpty() && !locationtext.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
