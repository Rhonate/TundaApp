package com.example.rhona.tundaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class SellerHome extends AppCompatActivity {


    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private Fragment mFragment;

    Button profile, products, transaction;
//    TextView id, userName, userEmail, gender;
//    Button btnLogout;
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

        //profile button
//        profile =(Button)findViewById(R.id.profilebtn);
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent profile = new Intent(SellerHome.this, Profile.class);
//                startActivity(profile);
//
//            }
//        });
//
//        //products button
//        products=(Button)findViewById(R.id.products);
//        products.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pdts = new Intent(SellerHome.this, SellerProducts.class);
//                startActivity(pdts);
//            }
//        });
//
//        //transactions button
//        transaction=(Button)findViewById(R.id.transaction);
//        transaction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent trans = new Intent(SellerHome.this, Transactions.class);
//                startActivity(trans);
//            }
//        });
//
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
////            id = findViewById(R.id.textViewId);
////            userName = findViewById(R.id.textViewUsername);
////            userEmail = findViewById(R.id.textViewEmail);
////            gender = findViewById(R.id.textViewGender);
//            btnLogout = (Button)findViewById(R.id.buttonLogout);
//            User user = SharedPrefManager.getInstance(this).getUser();
//
////            id.setText(String.valueOf(user.getId()));
////            userEmail.setText(user.getEmail());
////            gender.setText(user.getGender());
////            userName.setText(user.getName());
//
//            btnLogout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//        }
//
//        else{
//            Intent  intent = new Intent(SellerHome.this,LoginSeller.class);
//            startActivity(intent);
//            finish();
//        }

    }


//    public void onClick(View view){
//        if(view.equals(btnLogout)){
//            SharedPrefManager.getInstance(getApplicationContext()).logout();
//        }
//    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }
}
