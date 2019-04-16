package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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


public class LoginSeller extends AppCompatActivity {

    EditText email, etPassword;
    ProgressBar progressBar;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_seller);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, SellerHome.class));
        }
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        email = (EditText)findViewById(R.id.emailtxt);
        etPassword = (EditText)findViewById(R.id.passwordtxt);



        //calling the method userLogin() for login the user
        login = (Button)findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userLogin();
                //starting the profile activity

            }
        });

        //if user presses on textview not register calling RegisterActivity
        findViewById(R.id.signuptxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });
    }

    private void userLogin() {
        Log.e("Call: ", "login started");
        //first getting the values
        final String _email = email.getText().toString();
        final String password = etPassword.getText().toString();
//        //validating inputs
        if (TextUtils.isEmpty(_email)) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }
        else {
            Intent intent = new Intent(LoginSeller.this, SellerHome.class);
            startActivity(intent);

            finish();
        }
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response: ", response);
                        progressBar.setVisibility(View.GONE);
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
                                        userJson.getString("address"),
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
                        Log.e("Call: ", "error returned");
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", _email);
                params.put("password", password);
                return params;
            }
        };

        Log.e("Call: ", "volley instance");
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
