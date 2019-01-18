package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SellerProducts extends AppCompatActivity {

    List<Product> lstpdt;
    FloatingActionButton addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_products);

        lstpdt=new ArrayList<>();
        lstpdt.add(new Product("Cabbage","Vegetable","Description",R.drawable.cabbage));
        lstpdt.add(new Product("Chicken","Poultry","Description",R.drawable.chicken));
        lstpdt.add(new Product("Cow", "Animal", "Description", R.drawable.cow));
        lstpdt.add(new Product("Cucumber", "Vegetable", "Description", R.drawable.cucumber));

        //Floating button
        addbutton=(FloatingActionButton)findViewById(R.id.addbtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addbtn = new Intent(SellerProducts.this, AddProduct.class);
                startActivity(addbtn);

                finish();
            }
        });

        RecyclerView rv=(RecyclerView)findViewById(R.id.rvseller);
        RecyclerViewAdapterSeller myadapter=new RecyclerViewAdapterSeller(this,lstpdt);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(myadapter);

    }
}
