package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SellerPdtDescription extends AppCompatActivity {

    private TextView tvtitle, tvdesc, tvcat;
    private ImageView img;
    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_pdt_description);

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

        buy=(Button)findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buy= new Intent(SellerPdtDescription.this, SellerPdt.class);
                startActivity(buy);
            }
        });

    }
}
