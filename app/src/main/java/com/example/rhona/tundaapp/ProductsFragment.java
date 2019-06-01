package com.example.rhona.tundaapp;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {
    //    private List<Product> lstpdt;
    FloatingActionButton addbutton;


    private static final String TAG = ProductsFragment.class.getSimpleName();

    RecyclerView recyclerView;
    RequestQueue mRequestQueue;
    private List<Product> productList = new ArrayList<>();
    RecyclerViewAdapterSeller mAdapter;
    private File cacheDir;

    public ProductsFragment() {
        // Required empty public constructor
    }

    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        //Floating button
        addbutton=(FloatingActionButton) view.findViewById(R.id.addbtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addbtn = new Intent(getActivity(), AddProduct.class);
                startActivity(addbtn);
                getActivity().finish();

            }
        });


        //setting up volley request queue
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();


        fetchProductItems();

        recyclerView = view.findViewById(R.id.rvseller);
        mAdapter = new RecyclerViewAdapterSeller(getActivity(), productList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
//        recyclerView.setNestedScrollingEnabled(false);


        return view;

    }

    private void fetchProductItems() {



        SharedPrefManager sp= new SharedPrefManager(getContext());
        User u=sp.getUser();
        int uid=u.getId();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_SELLERPRODUCTS+"&&seller_id="+uid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
//                            Log.e("Loadin: ", "162");
                            JSONObject json = new JSONObject(response);
                            JSONArray array = json.getJSONArray("images");
                            for(int i=0;i<array.length();i++){
                                JSONObject object = array.getJSONObject(i);
                                Product product = new Product();
                                product.setName(object.getString("product_name"));
//                                Log.e("Product: ", object.getString("product_name"));
                                product.setPrice(object.getString("price"));
                                product.setDescription(object.getString("description"));
//                            Log.e("product_name", object.getString("product_name"));
                                product.setThumbnail(object.getString("image"));
//                            Log.e("image", object.getString("image"));

                                productList.add(product);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Log.e("Adapter: ", "Update");
                        mAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e("Error: ", "186-"+error);
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
            }catch (JSONException e) {
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
