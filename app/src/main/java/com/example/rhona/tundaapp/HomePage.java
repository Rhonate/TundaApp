package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    CardView buy,sell,products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //buy cardview
       buy=(CardView)findViewById(R.id.card_view);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buy = new Intent(HomePage.this, LoginSpecific.class);
                startActivity(buy);

            }
        });

//        //login cardview
//        login=(CardView)findViewById(R.id.card_view3);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent login= new Intent(HomePage.this, LoginPage.class);
//                startActivity(login);
//            }
//        });

        //sell cardview
        sell=(CardView)findViewById(R.id.card_view2);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sell=new Intent(HomePage.this, LoginSeller.class);
                startActivity(sell);
            }
        });

        //products cardview
        products=(CardView)findViewById(R.id.card_view4);
        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent products = new Intent(HomePage.this, ProductsList.class);
                startActivity(products);
            }
        });
    }
}
