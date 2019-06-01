package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Order extends AppCompatActivity {

    Button purchase;
    EditText phone, amount, qty;
    public String phonetext;
    public String amounttext;
    public String qtytext;


//    String prodqty="quantity";
//    String prodprice="price";
//    String tele="phone";
//    String prod = "product_name";
//    String pid = "p_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        purchase =(Button)findViewById(R.id.purchase);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orders();

                Toast.makeText(Order.this, "Successfully Submitted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Order.this, BuyerHome.class);
                startActivity(intent);
                finish();

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

    private void orders() {

        Intent desc=getIntent();
        final String  prod_name=desc.getExtras().getString("product_name");
        Log.e("Productname: ", prod_name);
        phonetext = phone.getText().toString().trim();
        amounttext = amount.getText().toString().trim();
        qtytext = qty.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_ORDERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response: ", response);
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject orderJson = obj.getJSONObject("orders");

                                //creating a new user object
                                OrderClass orderClass = new OrderClass(
                                        orderJson.getInt("id"),
                                        orderJson.getString("product_name"),
                                        orderJson.getString("quantity"),
                                        orderJson.getString("price"),
                                        orderJson.getString("phone")
                                );

                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("product_name", prod_name);
                params.put("quantity",qtytext );
                params.put("price", amounttext);
                params.put("phone", phonetext);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private TextWatcher purchaseTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            phonetext = phone.getText().toString().trim();
            amounttext = amount.getText().toString().trim();
            qtytext = qty.getText().toString().trim();

            purchase.setEnabled(!phonetext.isEmpty() && !amounttext.isEmpty() && !qtytext.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
