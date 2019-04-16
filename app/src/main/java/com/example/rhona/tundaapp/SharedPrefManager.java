package com.example.rhona.tundaapp;

/**
 * Created by Rhona on 3/27/2019.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_FNAME = "keyfname";
    private static final String KEY_LNAME = "keylname";
    private static final String KEY_PHONE = "keyphone";
    private static final String KEY_ADDRESS = "keyaddress";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_USER = "keyuser";
    public static final String KEY_ID = "keyid";
    private static final String KEY_PASSWORD = "keypassword";
    private static SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_FNAME, user.getFname());
        editor.putString(KEY_LNAME, user.getLname());
        editor.putString(KEY_PHONE, user.getPhone());
        editor.putString(KEY_ADDRESS, user.getAddress());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_USER, user.getUser());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_FNAME, null),
                sharedPreferences.getString(KEY_LNAME, null),
                sharedPreferences.getString(KEY_PHONE, null),
                sharedPreferences.getString(KEY_ADDRESS, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_USER, null),
                sharedPreferences.getString(KEY_PASSWORD, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginSeller.class));
    }

}
