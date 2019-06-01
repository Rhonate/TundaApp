
package com.example.rhona.tundaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by Rhona on 4/4/2019.
 */
public class SharePrefManagerBuyer {

    private static final String SHARE_PREF_NAME = "volleyregisterloginbuyer";
    public static final String KEY_ID = "keyid";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_PHONE = "keyphone";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_PASSWORD = "keypassword";
    private static SharePrefManagerBuyer mInstance;
    private static Context ctx;

    public SharePrefManagerBuyer(Context context) {
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
        SharedPreferences preferences = ctx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_ID, userbuyer.getId());
        editor.putString(KEY_NAME, userbuyer.getName());
        editor.putString(KEY_PHONE, userbuyer.getPhone());
        editor.putString(KEY_EMAIL, userbuyer.getEmail());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_EMAIL, null) != null){
             return true;
        } else {
            return false;
        }
    }

    //this method will give the logged in user
    public UserBuyer getUserBuyer() {
        SharedPreferences preferences = ctx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return new UserBuyer(
                preferences.getInt(KEY_ID, -1),
                preferences.getString(KEY_NAME, null),
                preferences.getString(KEY_PHONE, null),
                preferences.getString(KEY_EMAIL, null),
                preferences.getString(KEY_PASSWORD, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences preferences = ctx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginSpecific.class));
    }


}
