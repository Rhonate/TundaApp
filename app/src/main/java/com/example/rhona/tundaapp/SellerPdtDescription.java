package com.example.rhona.tundaapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static java.lang.System.load;

public class SellerPdtDescription extends AppCompatActivity {

    private TextView tvtitle, tvdesc, tvcat;
    private ImageView img;
    Button save;
    EditText priceEditText,descEditext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_pdt_description);

        //finding views
        tvtitle=(TextView)findViewById(R.id.title);
//        tvdesc=(TextView)findViewById(R.id.descET);
//        tvcat=(TextView)findViewById(R.id.category);
        img=(ImageView)findViewById(R.id.productthumbnail);
        priceEditText=(EditText)findViewById(R.id.priceET);
        descEditext=(EditText)findViewById(R.id.descET);

        //Receive data
        Intent desc=getIntent();
        String title=desc.getExtras().getString("product_name");
        String description=desc.getExtras().getString("description");
        String image= desc.getExtras().getString("image");
        String price=desc.getExtras().getString("price");

        //setting values
        tvtitle.setText(title);
//        tvdesc.setText(description);
        Glide.with(SellerPdtDescription.this).load(image).into(img);
        priceEditText.setText(price);
        descEditext.setText(description);

//        save=(Button)findViewById(R.id.savechanges);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent save= new Intent(SellerPdtDescription.this, SellerPdt.class);
//                startActivity(save);
//            }
//        });

    }
}
