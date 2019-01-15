package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDescription extends AppCompatActivity {

    private TextView tvtitle, tvdesc, tvcat;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        tvtitle=(TextView)findViewById(R.id.title);
        tvdesc=(TextView)findViewById(R.id.desc);
        tvcat=(TextView)findViewById(R.id.category);
        img=(ImageView)findViewById(R.id.productthumbnail);

        //Receive data

        Intent desc=getIntent();
        String title=desc.getExtras().getString("Title");
        String description=desc.getExtras().getString("Description");
        int image= desc.getExtras().getInt("Thumbnail");

        //setting values
        tvtitle.setText(title);
        tvdesc.setText(description);
        img.setImageResource(image);
    }
}
