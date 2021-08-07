package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paxees.tcc.R;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetAddToCartResponse;

import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private int row_index;
    private GetAddToCartResponse mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public onItemPlus onItemPlus;
    public onItemMinus onItemMinus;
    public onItemRemove removeProd;
    private Context context;
    private String branchImage;

    // data is passed into the constructor
    public CartAdapter(Context context, GetAddToCartResponse data, onItemPlus onItemPlus, onItemMinus onItemMinus,onItemRemove removeProd) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.onItemPlus=onItemPlus;
        this.onItemMinus=onItemMinus;
        this.removeProd=removeProd;
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
        GetAddToCartResponse data = mData;
        Glide.with(context).load(data.get(position).getProductImage()).placeholder(R.drawable.logo).into( holder.imgid);
        holder.titleLbl.setText(data.get(position).getProductName());
        holder.priceTv.setText(data.get(position).getProductPrice()+"");
        holder.idQuantity.setText(data.get(position).getQuantity()+"");
        holder.txtQuantityCart.setText(data.get(position).getQuantity()+"");
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemPlus.onItemPlus(data,v,position,holder.idQuantity.getText().toString().trim());
                quantityPlus(holder);
            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemMinus.onItemMinus(data,v,position,holder.idQuantity.getText().toString().trim());
                quantityMinus(holder);
            }
        });
        holder.removeProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProd.onItemRemove(data,v,position,holder.idQuantity.getText().toString().trim());
                notifyDataSetChanged();
            }
        });
    }

    private void quantityMinus(ViewHolder holder) {
        int count=Integer.parseInt(holder.idQuantity.getText().toString().trim());
        if(count>0)
        count--;
        holder.idQuantity.setText(count+"");
        holder.txtQuantityCart.setText(count+"");
    }

    public void quantityPlus(ViewHolder holder){
        int count=Integer.parseInt(holder.idQuantity.getText().toString().trim());
        count++;
        holder.idQuantity.setText(count+"");
        holder.txtQuantityCart.setText(count+"");
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleLbl, priceTv,btnMinus,btnPlus,idQuantity,txtQuantityCart;
        ImageView imgid,removeProd;

        ViewHolder(View itemView) {
            super(itemView);
            txtQuantityCart = itemView.findViewById(R.id.txtQuantityCart);
            btnMinus = itemView.findViewById(R.id.idMinus);
            idQuantity = itemView.findViewById(R.id.idQuantity);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            titleLbl = itemView.findViewById(R.id.txtPopluarName);
            priceTv = itemView.findViewById(R.id.priceTv);
            imgid = itemView.findViewById(R.id.imgid);
            removeProd = itemView.findViewById(R.id.removeProd);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    GetAddToCartResponse getItem(int id) {
        return mData;
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
        void onItemPlus(GetAddToCartResponse data, View view, int position, String value);
    }

    // parent activity will implement this method to respond to click events
    public interface onItemMinus {
        void onItemMinus(GetAddToCartResponse data, View view, int position, String value);
    }

    // parent activity will implement this method to respond to click events
    public interface onItemRemove {
        void onItemRemove(GetAddToCartResponse data, View view, int position, String value);
    }
}