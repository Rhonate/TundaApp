package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginSpecific extends AppCompatActivity {
    Button login;
    TextView signup;
    EditText mail,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_specific);

        if (SharePrefManagerBuyer.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, BuyerHome.class));
        }

        mail=(EditText)findViewById(R.id.emailtxt1);
        password=(EditText)findViewById(R.id.passwordtxt1);

        //signup textview
        signup=(TextView)findViewById(R.id.signuptxt1);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(LoginSpecific.this, SignUpBuyer.class);
                startActivity(signup);

                finish();
            }
        });

        //login button
        login=(Button)findViewById(R.id.loginbtn1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();
            }
        });




    }

    private void userLogin() {

//        Log.e("Call: ", "login started");
        //first getting the values
        final String buyermail = mail.getText().toString();
        final String pass = password.getText().toString();
//        //validating inputs
        if (TextUtils.isEmpty(buyermail)) {
            mail.setError("Please enter your email");
            mail.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(pass)) {
            password.setError("Please enter your password");
            password.requestFocus();
            return;
        }
        else {

            //if everything is fine
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_BUYERLOGIN,
                    new Response.Listener<String>() {
                        @Override

                        public void onResponse(String response) {
                            Log.e("Response: ", response);
                            try {
                                //converting response to json object
                                JSONObject b_obj = new JSONObject(response);
                                //if no error in response
                                if (!b_obj.getBoolean("error")) {
                                    //getting the user from the response
                                    JSONObject userbuyerJson = b_obj.getJSONObject("users");

                                    Log.e("Email: ", buyermail);
                                    //creating a new user object
                                    UserBuyer userbuyer = new UserBuyer(
                                            userbuyerJson.getInt("id"),
                                            userbuyerJson.getString("name"),
                                            userbuyerJson.getString("phone"),
                                            userbuyerJson.getString("email"),
                                            ""
                                    );

                                    //storing the user in shared preferences
//                                    SharePrefManagerBuyer.getInstance(getApplicationContext()).userBuyerLogin(userbuyer);

                                    SharePrefManagerBuyer b_sp = new SharePrefManagerBuyer(LoginSpecific.this);
                                    b_sp.userBuyerLogin(userbuyer);
                                    if (b_obj.getString("error").contentEquals("false")) {
                                        Intent intent = new Intent(LoginSpecific.this, BuyerHome.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else if (b_obj.getString("error").contentEquals("true") ){
                                        Toast.makeText(getApplicationContext(), "Error: " +b_obj.getString("message"), Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Error: "+b_obj.getString("message"), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error: " + b_obj.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("JSON Exception: ", "Incorrect Email or Password"+e);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Call: ", "error returned");
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", buyermail);
                    params.put("password", pass);
                    return params;
                }
            };

//            Log.e("Call: ", "volley instance");
            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        }

    }

}
