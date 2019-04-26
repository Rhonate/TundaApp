package com.example.rhona.tundaapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.bumptech.glide.Glide;

/**
 * Created by Rhona on 1/11/2019.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<Product> mData;


    public RecyclerViewAdapter(Context mContext, List<Product> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView productname, productprice;
        ImageView product_thumbnail;
        CardView cardview;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            productname=(TextView)itemView.findViewById(R.id.ProductName);
            productprice=(TextView)itemView.findViewById(R.id.ProductPriceTV);
            product_thumbnail=(ImageView)itemView.findViewById(R.id.ProductImage);
            cardview=(CardView)itemView.findViewById(R.id.cardview_products);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_book, parent, false);
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        view = mInflater.inflate(R.layout.cardview_item_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Product product = mData.get(position);
        holder.productname.setText(product.getName());
        holder.productprice.setText(product.getPrice());
        Glide.with(mContext).load(product.getThumbnail()).into(holder.product_thumbnail);

//click listener
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent desc = new Intent(mContext,ProductDescription.class);

                //passing data to Decsription Activity
                desc.putExtra("product_name",mData.get(position).getName());
                desc.putExtra("price",mData.get(position).getPrice());
                desc.putExtra("description",mData.get(position).getDescription());
//                desc.putExtra("description",mData.get(position).getDescription());
                desc.putExtra("image",mData.get(position).getThumbnail());


                //starting the activity
                mContext.startActivity(desc);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



}
