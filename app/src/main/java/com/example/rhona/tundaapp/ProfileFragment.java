package com.example.rhona.tundaapp;


import android.content.Intent;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    TextView name, email;
    EditText address, phone;
    Button btnLogout, save;

    public ProfileFragment() {
        // Required empty public constructor
    }

    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        if (SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
            name = view.findViewById(R.id.Username);
            email = view.findViewById(R.id.UserEmail);
            address = view.findViewById(R.id.TVaddress);
            phone = view.findViewById(R.id.textViewPhone);
            btnLogout = view.findViewById(R.id.logout);
            save = view.findViewById(R.id.edit);
            user = SharedPrefManager.getInstance(getActivity()).getUser();

            name.setText(user.getFname() + " " + user.getLname());
            email.setText(user.getEmail());
            address.setText(user.getAddress());
            phone.setText(user.getPhone());

            btnLogout.setOnClickListener(this);
        }

        else{

            Intent intent = new Intent(getActivity(), LoginSeller.class);
            startActivity(intent);
            getActivity().finish();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String _phone = phone.getText().toString();
                final String _location = address.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_UPDATEPROFILE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Response: ", response);
                            try {
                                //converting response to json object
                                JSONObject obj = new JSONObject(response);

                                //if no error in response
                                if (!obj.getBoolean("error")) {
                                    //getting the user from the response
                                    String msg= obj.getString("message");
                                    //Updating user preference info
                                    SharedPrefManager sp= new SharedPrefManager(getActivity());
                                    User u=sp.getUser();
                                    int uid=u.getId();
                                    User user = new User(
                                            u.getId(),
                                            u.getFname(),
                                            u.getLname(),
                                            _phone,
                                            _location,
                                            u.getEmail(),
                                            u.getUser()
                                    );
                                    sp.userLogin(user);

                                    if(msg.contentEquals("User updated successfully")){
                                        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getActivity(), SellerHome.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    } else {
                                        Toast.makeText(getContext(), "Error: " + obj.getString("message"), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getContext(), "Error: "+obj.getString("message"), Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("JSON Exception: ", ""+e);
                            }
                        }
                    },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Call: ", "error returned");
                                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("phone", _phone);
                        params.put("location", _location);
//                        GET USER iD
                        SharedPrefManager sp= new SharedPrefManager(getActivity());
                        User u=sp.getUser();
                        int uid=u.getId();
                        params.put("id", ""+uid);
                        return params;
                    }
                };

                Log.e("Call: ", "volley instance");
                VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
            }
        });

//        Log.e("Name: ", user.getFname() + " " + user.getLname());

        return view;

    }


    @Override
    public void onClick(View view){
        if(view.equals(btnLogout)){
            SharedPrefManager.getInstance(getContext().getApplicationContext()).logout();
        }
    }
}
