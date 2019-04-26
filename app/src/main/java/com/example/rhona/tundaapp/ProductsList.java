package com.example.rhona.tundaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class ProductsList extends AppCompatActivity {

    RecyclerView recycler_view;
    RequestQueue mRequestQueue;
    private List<Product> mData = new ArrayList<>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        //setting up volley request queue
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();


        //get products from server using volley and store in arraylist
        getProduct();

        //initialising recyclerview and setting adapter to the recyclerview
        recycler_view = (RecyclerView)findViewById(R.id.recyclerview);
        adapter = new RecyclerViewAdapter(this, mData);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);
    }

        //method to parse json data from url
    public void getProduct(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_PRODUCTLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray array = json.getJSONArray("images");
                            for(int i=0;i<array.length();i++){
                                JSONObject object = array.getJSONObject(i);
                                Product product = new Product();
                                product.setName(object.getString("product_name"));
                                product.setPrice(object.getString("price"));
                                product.setDescription(object.getString("description"));
//                                product.setDescription(object.getString("description"));
                                Log.e("product_name", object.getString("product_name"));
                                product.setThumbnail(object.getString("image"));
                                Log.e("image", object.getString("image"));

                                mData.add(product);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                parseVolleyError(error);
            }
        });


        stringRequest.setShouldCache(false);
        mRequestQueue.add(stringRequest);


    }

    public void parseVolleyError(VolleyError error) {
        if (error.networkResponse != null){
        try {
            String responseBody = new String(error.networkResponse.data, "utf-8");
            JSONObject data = new JSONObject(responseBody);
            JSONArray errors = data.getJSONArray("errors");
            JSONObject jsonMessage = errors.getJSONObject(0);
            String message = jsonMessage.getString("message");
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Please connect to the internet and try again", Toast.LENGTH_LONG).show();
        } catch (UnsupportedEncodingException errorr)
        {
            Toast.makeText(getApplicationContext(), "Please connect to the internet and try again", Toast.LENGTH_LONG).show();
        }
        }else{
            Toast.makeText(getApplicationContext(), "Network is unreachable!! Please connect and try again", Toast.LENGTH_LONG).show();
        }
    }

}


