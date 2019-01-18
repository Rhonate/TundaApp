package com.example.rhona.tundaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Order extends AppCompatActivity {

    Button purchase;
    EditText phone, amount, qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        purchase =(Button)findViewById(R.id.purchase);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Order.this, "Successfully Submitted", Toast.LENGTH_LONG).show();

            }
        });

        //phone, amount, qty
        phone=(EditText)findViewById(R.id.phonenumber);
        amount=(EditText)findViewById(R.id.amount);
        qty=(EditText)findViewById(R.id.quantity);

        phone.addTextChangedListener(purchaseTextWatcher);
        amount.addTextChangedListener(purchaseTextWatcher);
        qty.addTextChangedListener(purchaseTextWatcher);

    }

    private TextWatcher purchaseTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String phonetext = phone.getText().toString().trim();
            String amounttext = amount.getText().toString().trim();
            String qtytext = qty.getText().toString().trim();

            purchase.setEnabled(!phonetext.isEmpty() && !amounttext.isEmpty() && !qtytext.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
