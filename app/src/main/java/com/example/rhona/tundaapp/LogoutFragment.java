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

public class LogoutFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

    Button yes;

    UserBuyer userBuyer;

    public LogoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logout, container, false);
        if (SharePrefManagerBuyer.getInstance(getActivity()).isLoggedIn()) {
            yes = view.findViewById(R.id.yes);
            userBuyer = SharePrefManagerBuyer.getInstance(getActivity()).getUserBuyer();

            yes.setOnClickListener(this);
        } else {

            Intent intent = new Intent(getActivity(), LoginSpecific.class);
            startActivity(intent);
            getActivity().finish();
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(yes)){
            SharePrefManagerBuyer.getInstance(getContext().getApplicationContext()).logout();
            getActivity().finish();
        }
    }
}
