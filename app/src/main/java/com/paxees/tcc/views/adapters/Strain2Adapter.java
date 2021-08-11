package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paxees.tcc.R;
import com.paxees.tcc.network.networkmodels.response.baseResponses.Product;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class Strain2Adapter extends RecyclerView.Adapter<Strain2Adapter.ViewHolder> {
    private int row_index;
    private List<Product> mData;
    private LayoutInflater mInflater;
    private StrainAdapter.ItemClickListener mClickListener;
    private Context context;
    private String branchImage;

    // data is passed into the constructor
    public Strain2Adapter(Context context, List<Product> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public Strain2Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_strain_2, parent, false);
        return new Strain2Adapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(Strain2Adapter.ViewHolder holder, int position) {
        Product data = mData.get(position);
        Glide.with(context).load(data.getProductImageUrl()).into(holder.imageView);
        holder.titleLbl.setText(data.getProductName());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleLbl, titleValue;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            titleLbl = itemView.findViewById(R.id.txtPopluarName);
            titleValue = itemView.findViewById(R.id.txtQuantity);
            imageView = itemView.findViewById(R.id.imgid);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    List<Product> getItem(int id) {
        return mData;
    }

    // allows clicks events to be caught
    public void setClickListener(StrainAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}