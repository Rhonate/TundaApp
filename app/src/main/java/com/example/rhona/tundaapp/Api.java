package com.example.rhona.tundaapp;

import android.widget.EditText;

import retrofit2.Call;
import  okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Url;

/**
 * Created by Rhona on 2/14/2019.
 */
public interface Api {

    @FormUrlEncoded
    @POST("seller")
    Call<ResponseBody> addSeller(
//            @Url String url,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("seller_email") String seller_email,
            @Field("seller_password") String seller_password
    );

}