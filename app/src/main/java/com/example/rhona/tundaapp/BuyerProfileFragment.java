package com.example.rhona.tundaapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BuyerProfileFragment extends android.support.v4.app.Fragment {

    TextView name, address,phonenumber;

    public BuyerProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buyer_profile, container, false);

        UserBuyer userbuyer;

        if (SharePrefManagerBuyer.getInstance(getActivity()).isLoggedIn()) {
            name = view.findViewById(R.id.name);
            address = view.findViewById(R.id.address);
            phonenumber = view.findViewById(R.id.phone);
//            save = (Button)findViewById(R.id.edit);
            userbuyer = SharePrefManagerBuyer.getInstance(getActivity()).getUserBuyer();

            name.setText(userbuyer.getName());
            address.setText(userbuyer.getEmail());
            phonenumber.setText(userbuyer.getPhone());

        }


    return view;
    }


}
