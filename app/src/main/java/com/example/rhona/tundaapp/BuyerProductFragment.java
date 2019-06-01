package com.example.rhona.tundaapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BuyerProductFragment extends android.support.v4.app.Fragment {

    RecyclerView recycler_view;
    RequestQueue mRequestQueue;
    private List<Product> mData = new ArrayList<>();
    RecyclerViewAdapter adapter;

    public BuyerProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buyer_product, container, false);

        //setting up volley request queue
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();


        //get products from server using volley and store in arraylist
        getProduct();

        //initialising recyclerview and setting adapter to the recyclerview
        recycler_view = view.findViewById(R.id.rvbuyer);
        adapter = new RecyclerViewAdapter(getActivity(), mData);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);


        return  view;
    }

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
//                                Log.e("product_name", object.getString("product_name"));
                                product.setThumbnail(object.getString("image"));
//                                Log.e("image", object.getString("image"));
                                product.setPhone(object.getString("phone"));

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
                Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                Toast.makeText(getActivity().getApplicationContext(), "Please connect to the internet and try again", Toast.LENGTH_LONG).show();
            } catch (UnsupportedEncodingException errorr)
            {
                Toast.makeText(getActivity().getApplicationContext(), "Please connect to the internet and try again", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "Network is unreachable!! Please connect and try again", Toast.LENGTH_LONG).show();
        }
    }



}
