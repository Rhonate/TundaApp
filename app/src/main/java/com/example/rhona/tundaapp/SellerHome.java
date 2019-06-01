package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.v4.app.FragmentTransaction;

public class SellerHome extends AppCompatActivity {


    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);


        mMainFrame=(FrameLayout)findViewById(R.id.main_frame);
        mMainNav=(BottomNavigationView)findViewById(R.id.main_nav);

        mFragment = new ProductsFragment();
        setFragment(mFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_products:

                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        mFragment = new ProductsFragment();
                        setFragment(mFragment);
                        return true;

                    case R.id.nav_profile:
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        mFragment = new ProfileFragment();
                        setFragment(mFragment);;
                        return true;

                    case R.id.nav_transactions:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        mFragment = new TransactionFragment();
                        setFragment(mFragment);
                        return true;

                    default:
                        return false;


                }
            }
        });



    }


    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }
}
