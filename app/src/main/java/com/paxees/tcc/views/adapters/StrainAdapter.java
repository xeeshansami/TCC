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
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.models.Branch;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class StrainAdapter extends RecyclerView.Adapter<StrainAdapter.ViewHolder> {
    private int row_index;
    private StrainResponse mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private String branchImage;

    // data is passed into the constructor
    public StrainAdapter(Context context, StrainResponse data,ItemClickListener mClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.mClickListener=mClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_strain, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StrainResponse data = mData;
        Glide.with(context).load(data.get(position).getProduct_Image_Url()).into( holder.imageView);
        holder.titleLbl.setText(data.get(position).getProduct_Name());
        holder.imageView.setOnClickListener(v -> {
            mClickListener.onItemClick(v,position);
        });
//        holder.titleValue.setText(data.get(position).getProduct_id());
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
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    StrainResponse getItem(int id) {
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