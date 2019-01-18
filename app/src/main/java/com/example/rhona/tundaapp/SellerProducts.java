package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SellerProducts extends AppCompatActivity {

    FloatingActionButton addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_products);

        addbutton=(FloatingActionButton)findViewById(R.id.addbtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addbtn = new Intent(SellerProducts.this, AddProduct.class);
                startActivity(addbtn);

                finish();
            }
        });

    }
}
