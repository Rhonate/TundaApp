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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    TextView name, email, address, phone;
    Button btnLogout;

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
