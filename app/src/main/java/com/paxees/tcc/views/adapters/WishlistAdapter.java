package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paxees.tcc.R;
import com.paxees.tcc.models.mFilterDashboard;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetWishlistResponse;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {
    private int row_index;
    private GetWishlistResponse mData;
    private LayoutInflater mInflater;
    public ItemClickListener mClickListener;
    public ItemClickListener mClickListener2;
    private Context context;
    private String branchImage;

    // data is passed into the constructor
    public WishlistAdapter(Context context, GetWishlistResponse data,ItemClickListener mClickListener,ItemClickListener mClickListener2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.mClickListener=mClickListener;
        this.mClickListener2=mClickListener2;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_wishlist, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GetWishlistResponse data = mData;
        Glide.with(context).load("data.getImg()").placeholder(R.drawable.logo).into( holder.imageView);
        holder.titleLbl.setText(data.get(position).getProductId()+"");
        holder.priceTv.setText("$"+data.get(position).getPrice());
        holder.addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mClickListener.onItemClick(data,v,position);
            }
        });
        holder.removeProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener2.onItemClick(data,v,position);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleLbl, priceTv;
        ImageView imageView,removeProd;
        LinearLayout addtoCart;

        ViewHolder(View itemView) {
            super(itemView);
            titleLbl = itemView.findViewById(R.id.txtPopluarName);
            imageView = itemView.findViewById(R.id.imgid);
            priceTv = itemView.findViewById(R.id.priceTv);
            removeProd = itemView.findViewById(R.id.removeProd);
            addtoCart = itemView.findViewById(R.id.addtoCart);
        }


    }

    // convenience method for getting data at click position
    GetWishlistResponse getItem(int id) {
        return mData;
    }



    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(GetWishlistResponse data,View view, int position);
    }
}