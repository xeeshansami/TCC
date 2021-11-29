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
import com.paxees.tcc.network.networkmodels.response.baseResponses.PlantsByTypeResponse;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class PlantTypeAdapter extends RecyclerView.Adapter<PlantTypeAdapter.ViewHolder> {
    private int row_index;
    private PlantsByTypeResponse mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public PlantTypeAdapter(Context context, PlantsByTypeResponse data,ItemClickListener mClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.mClickListener=mClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_plants_type, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlantsByTypeResponse data = mData;
        Glide.with(context).load(data.get(position).getCategoryImageUrl()).placeholder(R.drawable.logo).into( holder.img);
        holder.txt.setText(data.get(position).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,position);
            }
        });
    }
    public void filterList(PlantsByTypeResponse filteredList) {
        mData = filteredList;
        notifyDataSetChanged();
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView txt,txtqunatity;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgid);
            txt = itemView.findViewById(R.id.txtPopluarName);
            txtqunatity = itemView.findViewById(R.id.txtQuantity);
//            txtRating = itemView.findViewById(R.id.txtRating);
//            txtTime = itemView.findViewById(R.id.txtTime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view,  getAdapterPosition()?);
        }
    }

    // convenience method for getting data at click position
    PlantsByTypeResponse getItem(int id) {
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