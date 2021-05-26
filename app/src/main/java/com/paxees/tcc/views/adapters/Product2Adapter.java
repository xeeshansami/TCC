package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paxees.tcc.R;
import com.paxees.tcc.network.networkmodels.response.models.Branch;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class Product2Adapter extends RecyclerView.Adapter<Product2Adapter.ViewHolder> {
    private int row_index;
    private ArrayList<Branch> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private String branchImage;

    // data is passed into the constructor
    public Product2Adapter(Context context, ArrayList<Branch> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_brands_coupons, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Branch data = mData.get(position);
        if (position == 0) {
            holder.titleLbl.setText("GENETICS");
        } else if (position == 1) {
            holder.titleLbl.setText("PARENTS");
        } else if (position == 2) {
            holder.titleLbl.setText("THC");
        } else if (position == 3) {
            holder.titleLbl.setText("CBD");
        } else if (position == 4) {
            holder.titleLbl.setText("SMELL & FLAVOUR");
        } else if (position == 5) {
            holder.titleLbl.setText("EFFECT");
        }
        holder.titleValue.setText(data.getBranchName());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                row_index = position;
//                notifyDataSetChanged();
//            }
//        });
//        if (row_index == position) {
//            holder.img.setBackground(this.context.getResources().getDrawable(R.drawable.button_border));
//        } else {
//            holder.img.setBackground(this.context.getResources().getDrawable(android.R.color.transparent));
//        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleLbl, titleValue;

        ViewHolder(View itemView) {
            super(itemView);
            titleLbl = itemView.findViewById(R.id.titleLbl);
            titleValue = itemView.findViewById(R.id.titleValue);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Branch getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}