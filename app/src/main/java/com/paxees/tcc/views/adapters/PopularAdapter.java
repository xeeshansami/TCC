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
import com.paxees.tcc.network.networkmodels.response.models.Brand;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private int row_index;
    private ArrayList<mFilterDashboard> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public PopularAdapter(Context context, ArrayList<mFilterDashboard> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_views_popular, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mFilterDashboard data = mData.get(position);
        Glide.with(context).load(data.getImg()).into( holder.img);
        holder.txt.setText(data.getTxt());
    }
    public void filterList(ArrayList<mFilterDashboard> filteredList) {
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
        TextView txt,txtRating,txtTime;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgid);
            txt = itemView.findViewById(R.id.txtPopluarName);
//            txtRating = itemView.findViewById(R.id.txtRating);
//            txtTime = itemView.findViewById(R.id.txtTime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view,  getAdapterPosition(),mData.get(getAdapterPosition()));
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
        void onItemClick(View view, int position, mFilterDashboard brand);
    }
}