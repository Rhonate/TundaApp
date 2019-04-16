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
import android.text.TextUtils;
import android.view.View;
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

public class SignUpBuyer extends AppCompatActivity {

    EditText buyer_name, buyer_tele,  buyer_email, buyer_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_buyer);

        buyer_name=(EditText)findViewById(R.id.buyername);
        buyer_tele=(EditText)findViewById(R.id.buyertele);
        buyer_email=(EditText)findViewById(R.id.buyerEmail);
        buyer_password=(EditText)findViewById(R.id.buyerpassword);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
                //starting the profile activity
                Intent intent = new Intent(SignUpBuyer.this, LoginSpecific.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void registerUser() {


        final String bname = buyer_name.getText().toString().trim();
        final String btele = buyer_tele.getText().toString().trim();
        final String bemail = buyer_email.getText().toString().trim();
        final String bpass = buyer_password.getText().toString().trim();


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(bemail).matches()) {
            buyer_email.setError("Enter a valid email");
            buyer_email.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_BUYERSIGNUP,
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
                                JSONObject userbuyerJson = obj.getJSONObject("userbuyer");

                                //creating a new user object
                                UserBuyer userbuyer = new UserBuyer(
                                        userbuyerJson.getInt("id"),
                                        userbuyerJson.getString("name"),
                                        userbuyerJson.getString("phone"),
                                        userbuyerJson.getString("email"),
                                        userbuyerJson.getString("password")
                                );

                                //storing the user in shared preferences
                                SharePrefManagerBuyer.getInstance(getApplicationContext()).userBuyerLogin(userbuyer);


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
                params.put("name", bname);
                params.put("phone", btele);
                params.put("email", bemail);
                params.put("password", bpass);
                return params;


            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}
