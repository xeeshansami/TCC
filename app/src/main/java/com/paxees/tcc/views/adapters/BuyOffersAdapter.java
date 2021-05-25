package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paxees.tcc.R;
import com.paxees.tcc.models.mFilterDashboard;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class BuyOffersAdapter extends RecyclerView.Adapter<BuyOffersAdapter.ViewHolder> {
    private int row_index;
    private ArrayList<mFilterDashboard> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public BuyOffersAdapter(Context context, ArrayList<mFilterDashboard> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_buy_offers, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mFilterDashboard animal = mData.get(position);
        holder.tvBuyOffer.setText(animal.getTxt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if (row_index == position) {
            holder.itemView.setBackground(this.context.getResources().getDrawable(R.drawable.button_border));
        } else {
            holder.itemView.setBackground(this.context.getResources().getDrawable(R.drawable.buyoffers_border));
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvBuyOffer;

        ViewHolder(View itemView) {
            super(itemView);
            tvBuyOffer = itemView.findViewById(R.id.tvBuyOffer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    mFilterDashboard getItem(int id) {
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