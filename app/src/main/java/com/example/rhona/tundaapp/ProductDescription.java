package com.example.rhona.tundaapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductDescription extends AppCompatActivity {

    private TextView tvname, tvprice, tvdesc, tvcontact;
    private ImageView img;
    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

//        //Receive data
//        Intent desc=getIntent();
//        String title=desc.getExtras().getString("product_name");
//        String description=desc.getExtras().getString("description");
//        String image= desc.getExtras().getString("image");
//        String price=desc.getExtras().getString("price");
//
//        //setting values
//        tvtitle.setText(title);
//        tvdesc.setText(description);
//        Glide.with(SellerPdtDescription.this).load(image).into(img);
//        priceEditText.setText(price);
//        descEditext.setText(description);

        //finding the views
        tvname=(TextView)findViewById(R.id.title);
        tvprice=(TextView)findViewById(R.id.descprice);
        tvdesc=(TextView)findViewById(R.id.descdesc);
        tvcontact=(TextView)findViewById(R.id.contact);
        img=(ImageView)findViewById(R.id.productthumbnail);

        //Receive data
        Intent desc=getIntent();
        String title=desc.getExtras().getString("product_name");
        String price=desc.getExtras().getString("price");
        String description=desc.getExtras().getString("description");
        String image= desc.getExtras().getString("image");

        //setting values
        tvname.setText(title);
        tvprice.setText(price);
        tvdesc.setText(description);
        Glide.with(ProductDescription.this).load(image).into(img);

        buy=(Button)findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buy= new Intent(ProductDescription.this, Order.class);
                startActivity(buy);
            }
        });
    }
}
