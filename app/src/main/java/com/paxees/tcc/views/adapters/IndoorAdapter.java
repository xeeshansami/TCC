package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paxees.tcc.R;
import com.paxees.tcc.models.mFilterDashboard;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductSearchResponse;

import androidx.recyclerview.widget.RecyclerView;

public class IndoorAdapter extends RecyclerView.Adapter<IndoorAdapter.ViewHolder> {
    private int row_index;
    private ProductSearchResponse mData;
    private LayoutInflater mInflater;
    private ItemClickListener mItemClickListener;
    private Context context;

    // data is passed into the constructor
    public IndoorAdapter(Context context, ProductSearchResponse data, ItemClickListener mItemClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.mItemClickListener=mItemClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_discovery, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            ProductSearchResponse data = mData;
            Glide.with(context).load(data.get(position).getImages().get(0).getSrc()).placeholder(R.drawable.logo).into(holder.imgid);
            holder.productName.setText(data.get(position).getName());
            holder.productNameDesc.setText(data.get(position).getSlug());
            holder.addtoCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v,position,data);
                }
            });
        } catch (Exception e) {
            Log.i("Exception",e.getMessage());
        }
    }

    public void filterList(ProductSearchResponse filteredList) {
        mData = filteredList;
        notifyDataSetChanged();
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgid;
        TextView productName, productNameDesc;
        Button addtoCart;
        ImageView baskitBtn;

        ViewHolder(View itemView) {
            super(itemView);
            imgid = itemView.findViewById(R.id.imgid);
            productName = itemView.findViewById(R.id.productName);
            productNameDesc = itemView.findViewById(R.id.productNameDesc);
            addtoCart = itemView.findViewById(R.id.addtoCart);
            baskitBtn = itemView.findViewById(R.id.baskitBtn);
        }


    }

    // convenience method for getting data at click position
    ProductSearchResponse getItem(int id) {
        return mData;
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position, ProductSearchResponse response);
    }
}