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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


import java.io.IOException;

public class SignUp2 extends AppCompatActivity {

    EditText Seller_email, Seller_password;
    RadioGroup radioGroupUser;
    ProgressBar progressBar;

    private static String seller_email, seller_password;
    Button back,submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);



        //back button
        back=(Button)findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SignUp2.this, SignUp.class);
                startActivity(back);

                finish();
            }
        });

//        Seller_email.addTextChangedListener(submitTextWatcher);
//        Seller_password.addTextChangedListener(submitTextWatcher);

        //
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        //if the user is already logged in we will directly start the MainActivity (profile) activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginSeller.class));
            return;
        }

        //email and password
        Seller_email=(EditText)findViewById(R.id.email);
        Seller_password=(EditText)findViewById(R.id.passwd);
        radioGroupUser = (RadioGroup)findViewById(R.id.radioGroup2);

        //Submit Button
        findViewById(R.id.submitbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
                //starting the profile activity

                Intent intent = new Intent(SignUp2.this, LoginSeller.class);
                startActivity(intent);
                finish();

            }
        });
}

//    private TextWatcher submitTextWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            seller_email = Seller_email.getText().toString().trim();
//            seller_password = Seller_password.getText().toString().trim();
//
//            submit.setEnabled(!seller_email.isEmpty() && !seller_password.isEmpty());
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };

    private void registerUser() {
        Intent intent = getIntent();
        final String fname = intent.getStringExtra("firstname");
        final String lname = intent.getStringExtra("lastname");
        final String phone = intent.getStringExtra("phone");
        final String location = intent.getStringExtra("address");

        final String email = Seller_email.getText().toString().trim();
        final String password = Seller_password.getText().toString().trim();

        final String user = ((RadioButton) findViewById(radioGroupUser.getCheckedRadioButtonId())).getText().toString();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Seller_email.setError("Enter a valid email");
            Seller_email.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("Response: ", response);
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("fname"),
                                        userJson.getString("lname"),
                                        userJson.getString("phone"),
                                        userJson.getString("location"),
                                        userJson.getString("email"),
                                        userJson.getString("user"),
                                        userJson.getString("password")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);


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
                params.put("fname", fname);
                params.put("lname", lname);
                params.put("phone", phone);
                params.put("location", location);
                params.put("email", email);
                params.put("user", user);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}
//https://www.thecrazyprogrammer.com/2016/12/pass-data-one-activity-another-in-android.html