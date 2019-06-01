package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView name, address,phonenumber;
    Button btnLogout, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    UserBuyer userbuyer;

        if (SharePrefManagerBuyer.getInstance(this).isLoggedIn()) {
            name = (TextView)findViewById(R.id.name);
            address = (TextView)findViewById(R.id.address);
            phonenumber = (TextView)findViewById(R.id.phone);
            btnLogout = (Button)findViewById(R.id.outlog);
//            save = (Button)findViewById(R.id.edit);
            userbuyer = SharePrefManagerBuyer.getInstance(this).getUserBuyer();

            name.setText(userbuyer.getName());
            address.setText(userbuyer.getEmail());
            phonenumber.setText(userbuyer.getPhone());

            btnLogout.setOnClickListener((View.OnClickListener) this);
        }

        else{

            Intent intent = new Intent(this, LoginSpecific.class);
            startActivity(intent);
            finish();
        }


    }

    public void onClick(View view){
        if(view.equals(btnLogout)){
            SharePrefManagerBuyer.getInstance(this.getApplicationContext()).logout();
        }
    }


}
