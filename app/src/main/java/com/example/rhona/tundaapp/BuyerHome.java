package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuyerHome extends AppCompatActivity {

    Button profile, buypdts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);

        //profile button
        profile=(Button)findViewById(R.id.profilebuyer);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile=new Intent(BuyerHome.this, Profile.class);
                startActivity(profile);

                finish();
            }
        });

        //buy button
        buypdts=(Button)findViewById(R.id.buypdts) ;
        buypdts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buypdts = new Intent(BuyerHome.this, ProductsList.class);
                startActivity(buypdts);

                finish();
            }
        });
    }
}
