package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

public class BuyerHome extends AppCompatActivity {

    Button profile, buypdts;
    private BottomNavigationView mMainNav;
    private FrameLayout mMFrame;
    private Fragment mFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);



            mMFrame=(FrameLayout)findViewById(R.id.main_frame1);
            mMainNav=(BottomNavigationView)findViewById(R.id.main_nav1);

            mFrag = new BuyerProfileFragment();
            setFragment(mFrag);

            mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_profilebuyer:

                            mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                            mFrag = new BuyerProfileFragment();
                            setFragment(mFrag);
                            return true;

                        case R.id.nav_productsbuyer:
                            mMainNav.setItemBackgroundResource(R.color.colorAccent);
                            mFrag = new BuyerProductFragment();
                            setFragment(mFrag);
                            return true;

                        case R.id.nav_logoutbuyer:
                            mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                            mFrag = new LogoutFragment();
                            setFragment(mFrag);
                            return true;

                        default:
                            return false;


                    }
                }
            });

    }

    public void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame1, fragment);
        fragmentTransaction.commit();
    }
}
