package com.paxees.tcc.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paxees.tcc.R;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DataXXX;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class GetExistingConsumerAdapter extends RecyclerView.Adapter<GetExistingConsumerAdapter.ViewHolder> {
    private int row_index;
    private ArrayList<DataXXX> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private String branchImage;
    private int checkedPosition = 0;
    // data is passed into the constructor
    public GetExistingConsumerAdapter(Context context, ArrayList<DataXXX> data,ItemClickListener mClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.mClickListener=mClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_payment_method, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataXXX dataXX=mData.get(position);
        holder.titleLbl.setText(dataXX.getEmail());
        holder.imageView.setVisibility(View.GONE);
        holder.titleValue.setVisibility(View.GONE);
        holder.paymentMethodDelete.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,position,dataXX);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // total number of rows
/*    @Override
    public int getItemCount() {
        return mData.size();
    }*/


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleLbl,paymentMethodDelete, titleValue;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            paymentMethodDelete = itemView.findViewById(R.id.paymentMethodDelete);
            titleLbl = itemView.findViewById(R.id.txtCardNum);
            titleValue = itemView.findViewById(R.id.visaCardTv);
            imageView = itemView.findViewById(R.id.payment_method_done);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, this.getAdapterPosition(), mData.get(this.getAdapterPosition()));
        }
    }

    // convenience method for getting data at click position
    ArrayList<DataXXX> getItem(int id) {
        return mData;
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position, DataXXX dataXX);
    }
}