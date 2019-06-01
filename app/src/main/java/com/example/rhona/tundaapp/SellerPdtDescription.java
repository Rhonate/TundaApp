package com.example.rhona.tundaapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.load;

public class SellerPdtDescription extends AppCompatActivity {

    private TextView tvtitle, tvcont;
    private ImageView img;
    Button save;
    EditText priceEditText,descEditext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_pdt_description);

        //finding views
        tvtitle=(TextView)findViewById(R.id.title);
        tvcont=(TextView)findViewById(R.id.sellcontact);
        img=(ImageView)findViewById(R.id.productthumbnail);
        priceEditText=(EditText)findViewById(R.id.priceET);
        descEditext=(EditText)findViewById(R.id.descET);
        save = (Button)findViewById(R.id.savechanges);

        //Receive data
        Intent desc=getIntent();
        String title=desc.getExtras().getString("product_name");
        String description=desc.getExtras().getString("description");
        String image= desc.getExtras().getString("image");
        String price=desc.getExtras().getString("price");
//        String contact=desc.getExtras().getString("phone");

        //setting values
        tvtitle.setText(title);
        Glide.with(SellerPdtDescription.this).load(image).into(img);
        priceEditText.setText(price);
        descEditext.setText(description);
//        tvcont.setText(contact);

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String title = tvtitle.getText().toString();
//                final String price = priceEditText.getText().toString();
//                final String desc = descEditext.getText().toString();
//                StringRequest stringRequest = new StringRequest(Request.Method.PUT, URLs.URL_UPDATEPRODUCT,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Log.e("Response: ", response);
//                                try {
//                                    //converting response to json object
//                                    JSONObject obj = new JSONObject(response);
//
//                                    //if no error in response
//                                    if (!obj.getBoolean("error")) {
//                                        //getting the user from the response
//                                        String msg = obj.getString("message");
//                                        //Updating user preference info
////                                        SharedPrefManager sp = new SharedPrefManager(SellerPdtDescription.this);
////                                        User u = sp.getUser();
////                                        int uid = u.getId();
////                                        User user = new User(
////                                                u.getId(),
////                                                u.getFname(),
////                                                u.getLname(),
////                                                _phone,
////                                                _location,
////                                                u.getEmail(),
////                                                u.getUser()
////                                        );
////                                        sp.userLogin(user);
//
//                                        if (msg.contentEquals("Product updated successfully")) {
//                                            Toast.makeText(SellerPdtDescription.this, msg, Toast.LENGTH_LONG).show();
//                                            Intent intent = new Intent(SellerPdtDescription.this, SellerHome.class);
//                                            startActivity(intent);
//                                            finish();
//                                        } else {
//                                            Toast.makeText(SellerPdtDescription.this, "Error: " + obj.getString("message"), Toast.LENGTH_LONG).show();
//                                        }
//                                    } else {
//                                        Toast.makeText(SellerPdtDescription.this, "Error: " + obj.getString("message"), Toast.LENGTH_LONG).show();
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                    Log.e("JSON Exception: ", "" + e);
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e("Call: ", "error returned");
//                                Toast.makeText(SellerPdtDescription.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//                        }) {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> params = new HashMap<>();
//                        params.put("product_name", title);
//                        params.put("price", price);
//                        params.put("description", desc);
////                        GET USER iD
//                        SharedPrefManager sp = new SharedPrefManager(SellerPdtDescription.this);
//                        User u = sp.getUser();
//                        int uid = u.getId();
//                        params.put("id", "" + uid);
//                        return params;
//                    }
//                };
//
//                Log.e("Call: ", "volley instance");
//                VolleySingleton.getInstance(SellerPdtDescription.this).addToRequestQueue(stringRequest);
//            }
//        });

    }
}
