
package com.example.rhona.tundaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by Rhona on 4/4/2019.
 */
public class SharePrefManagerBuyer {

    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_ID = "keyid";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_PHONE = "keyphone";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_PASSWORD = "keypassword";
    private static SharePrefManagerBuyer mInstance;
    private static Context ctx;

    private SharePrefManagerBuyer(Context context) {
        ctx = context;
    }
    public static synchronized SharePrefManagerBuyer getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharePrefManagerBuyer(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userBuyerLogin(UserBuyer userbuyer) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, userbuyer.getId());
        editor.putString(KEY_NAME, userbuyer.getName());
        editor.putString(KEY_PHONE, userbuyer.getPhone());
        editor.putString(KEY_EMAIL, userbuyer.getEmail());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    //this method will give the logged in user
    public UserBuyer getUserBuyer() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserBuyer(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_PHONE, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginSpecific.class));
    }


}
