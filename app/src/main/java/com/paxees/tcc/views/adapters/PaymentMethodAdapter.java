package com.paxees.tcc.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paxees.tcc.R;
import com.paxees.tcc.models.mFilterDashboard;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DataXX;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetPaymentMethodListOfConsumerResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PaymentMethodListResponse;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> {
    private int row_index;
    private GetPaymentMethodListOfConsumerResponse mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private String branchImage;
    private int checkedPosition = 0;
    // data is passed into the constructor
    public PaymentMethodAdapter(Context context, GetPaymentMethodListOfConsumerResponse data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
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
        GetPaymentMethodListOfConsumerResponse data = mData;
        DataXX dataXX=data.getData().get(position);
        String number = "grou353fp23g3qprwog"+dataXX.getLast4();
        String mask = number.replaceAll("\\w(?=\\w{4})", "*");
        holder.titleLbl.setText(mask);
        holder.titleValue.setText(dataXX.getBrand()+" "+dataXX.getExpMonth()+"/"+dataXX.getExpYear());
        if (checkedPosition == -1) {
            holder.imageView.setVisibility(View.INVISIBLE);
        } else {
            if (checkedPosition == position) {
                holder.imageView.setVisibility(View.VISIBLE);
            } else {
                holder.imageView.setVisibility(View.INVISIBLE);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageView.setVisibility(View.VISIBLE);
                if (checkedPosition != position) {
                    notifyItemChanged(checkedPosition);
                    checkedPosition = position;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.getData().size();
    }

    // total number of rows
/*    @Override
    public int getItemCount() {
        return mData.size();
    }*/


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleLbl, titleValue;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            titleLbl = itemView.findViewById(R.id.txtCardNum);
            titleValue = itemView.findViewById(R.id.visaCardTv);
            imageView = itemView.findViewById(R.id.payment_method_done);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, this.getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    GetPaymentMethodListOfConsumerResponse getItem(int id) {
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
}