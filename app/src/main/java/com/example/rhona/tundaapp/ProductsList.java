package com.example.rhona.tundaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductsList extends AppCompatActivity {

    List<Product> lstproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        lstproduct=new ArrayList<>();
        lstproduct.add(new Product("Cabbage","Vegetable","Description",R.drawable.cabbage));
        lstproduct.add(new Product("Chicken","Poultry","Description",R.drawable.chicken));
        lstproduct.add(new Product("Cow","Animal","Description",R.drawable.cow));
        lstproduct.add(new Product("Cucumber","Vegetable","Description",R.drawable.cucumber));
        lstproduct.add(new Product("Oatmeal","Vegetable","Description",R.drawable.oatmeal));
        lstproduct.add(new Product("Okra","Vegetable","Description",R.drawable.okra));
        lstproduct.add(new Product("Cabbage","Vegetable","Description",R.drawable.cabbage));
        lstproduct.add(new Product("Chicken","Poultry","Description",R.drawable.chicken));
        lstproduct.add(new Product("Cow","Animal","Description",R.drawable.cow));
        lstproduct.add(new Product("Cucumber","Vegetable","Description",R.drawable.cucumber));
        lstproduct.add(new Product("Oatmeal","Vegetable","Description",R.drawable.oatmeal));
        lstproduct.add(new Product("Okra","Vegetable","Description",R.drawable.okra));

        RecyclerView rv=(RecyclerView)findViewById(R.id.recyclerview);
        RecyclerViewAdapter myadapter=new RecyclerViewAdapter(this,lstproduct);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        rv.setAdapter(myadapter);
    }
}
