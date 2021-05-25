package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paxees.tcc.R;
import com.paxees.tcc.network.networkmodels.response.models.Branch;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.ViewHolder> {
    private int row_index;
    private ArrayList<Branch> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private String branchImage;

    // data is passed into the constructor
    public BranchAdapter(Context context, ArrayList<Branch> data, String brandBanner) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.branchImage=brandBanner;
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
        Glide.with(context).load(branchImage).into(  holder.img);
        holder.txt.setText("Name: "+data.getBranchName());
        holder.txtRating.setText("Address: "+data.getBranchLocality());
        holder.txtTime.setText("ID: "+data.getBranchId());
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
        ImageView img;
        TextView txt,txtRating,txtTime;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgid);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtTime = itemView.findViewById(R.id.txtTime);
            txt = itemView.findViewById(R.id.tvId);
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