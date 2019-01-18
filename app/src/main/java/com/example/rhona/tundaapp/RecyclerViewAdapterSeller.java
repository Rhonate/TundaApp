package com.example.rhona.tundaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rhona on 1/18/2019.
 */
public class RecyclerViewAdapterSeller extends RecyclerView.Adapter<RecyclerViewAdapterSeller.MyViewHolder>  {

    private Context mContext;
    private List<Product> mData;

    public RecyclerViewAdapterSeller(Context mContext, List<Product> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_seller_pdt,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.productname.setText(mData.get(position).getName());
        holder.product_thumbnail.setImageResource(mData.get(position).getThumbnail());

        //click listener
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent desc = new Intent(mContext,SellerPdtDescription.class);

                //passing data to Decsription Activity
                desc.putExtra("Title",mData.get(position).getName());
                desc.putExtra("Description",mData.get(position).getDescription());
                desc.putExtra("Thumbnail",mData.get(position).getThumbnail());

                //starting the activity
                mContext.startActivity(desc);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView productname;
        ImageView product_thumbnail;
        CardView cardview;

        public MyViewHolder(View itemView) {
            super(itemView);

            productname=(TextView)itemView.findViewById(R.id.PdtName);
            product_thumbnail=(ImageView)itemView.findViewById(R.id.PdtImage);
            cardview=(CardView)itemView.findViewById(R.id.cardview_seller_pdts);
        }
    }

}
