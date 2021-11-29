package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paxees.tcc.R;
import com.paxees.tcc.network.networkmodels.response.models.Branch;
import com.paxees.tcc.network.networkmodels.response.models.MetaDataX;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class GrowthtAdapter extends RecyclerView.Adapter<GrowthtAdapter.ViewHolder> {
    private int row_index;
    private ArrayList<MetaDataX> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private String branchImage;

    // data is passed into the constructor
    public GrowthtAdapter(Context context, ArrayList<MetaDataX> data) {
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
        MetaDataX data = mData.get(position);
        if (data.getKey().equalsIgnoreCase("grow_difficulty")) {
            holder.titleLbl.setText("Grow Difficulty");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("flowering_type")) {
            holder.titleLbl.setText("Flowering Type");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("flowering_time")) {
            holder.titleLbl.setText("Flowering Time");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("harvest_time")) {
            holder.titleLbl.setText("Harvest Time");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("yield")) {
            holder.titleLbl.setText("Yield");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("genetics")) {
            holder.titleLbl.setText("Genetics");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("parents")) {
            holder.titleLbl.setText("Parent");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("thc")) {
            holder.titleLbl.setText("THC");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("cbd")) {
            holder.titleLbl.setText("CBD");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("smell_flavour")) {
            holder.titleLbl.setText("Small Flavour");
            holder.titleValue.setText(data.getValue() + "");
        } else if (data.getKey().equalsIgnoreCase("effect")) {
            holder.titleLbl.setText("Effect");
            holder.titleValue.setText(data.getValue() + "");
        }

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
    MetaDataX getItem(int id) {
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