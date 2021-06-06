package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paxees.tcc.R;
import com.paxees.tcc.models.mFilterDashboard;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private int row_index;
    private ArrayList<mFilterDashboard> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public onItemPlus onItemPlus;
    public onItemMinus onItemMinus;
    private Context context;
    private String branchImage;

    // data is passed into the constructor
    public CartAdapter(Context context, ArrayList<mFilterDashboard> data,onItemPlus onItemPlus,onItemMinus onItemMinus) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.onItemPlus=onItemPlus;
        this.onItemMinus=onItemMinus;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_cart, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mFilterDashboard data = mData.get(position);
        Glide.with(context).load(data.getImg()).into( holder.imageView);
        holder.titleLbl.setText(data.getTxt());
        holder.priceTv.setText(data.getValue());
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemPlus.onItemPlus(v,position);
                quantityPlus(data,holder.idQuantity);
            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemMinus.onItemMinus(v,position);
                quantityPlus(data,holder.idQuantity);
            }
        });
    }

    public void quantityPlus(mFilterDashboard data, TextView idQuantity){
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleLbl, priceTv,btnMinus,btnPlus,idQuantity,txtQuantityCart;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            txtQuantityCart = itemView.findViewById(R.id.txtQuantityCart);
            btnMinus = itemView.findViewById(R.id.idMinus);
            idQuantity = itemView.findViewById(R.id.idQuantity);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            titleLbl = itemView.findViewById(R.id.txtPopluarName);
            priceTv = itemView.findViewById(R.id.priceTv);
            imageView = itemView.findViewById(R.id.imgid);
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

    // parent activity will implement this method to respond to click events
    public interface onItemPlus {
        void onItemPlus(View view, int position);
    }

    // parent activity will implement this method to respond to click events
    public interface onItemMinus {
        void onItemMinus(View view, int position);
    }
}