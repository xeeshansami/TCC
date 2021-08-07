package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paxees.tcc.R;
import com.paxees.tcc.models.mFilterDashboard;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PopularByThisWeekResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductSearchResponse;

import androidx.recyclerview.widget.RecyclerView;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ViewHolder> {
    private int row_index;
    private ProductSearchResponse mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public ProductSearchAdapter(Context context, ProductSearchResponse data) {
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
        try {
            ProductSearchResponse data = mData;
            Glide.with(context).load(data.get(position).getImages().get(0).getSrc()).placeholder(R.drawable.logo).into(holder.img);
            holder.txt.setText(data.get(position).getName());
            holder.ratingNumber.setText(data.get(position).getRatingCount() + "");
            holder.ratingNumberAdded.setText(data.get(position).getAverageRating() + "+");
        } catch (Exception e) {
            Log.i("Exception",e.getMessage());
        }
    }

    public void filterList(ProductSearchResponse filteredList) {
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
        TextView txt, ratingNumberAdded, ratingNumber;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgid);
            txt = itemView.findViewById(R.id.txtPopluarName);
            ratingNumber = itemView.findViewById(R.id.ratingNumber);
            ratingNumberAdded = itemView.findViewById(R.id.ratingNumberAdded);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view,  getAdapterPosition(),mData.get(getAdapterPosition()).component1().getAbout());
        }
    }

    // convenience method for getting data at click position
    ProductSearchResponse getItem(int id) {
        return mData;
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