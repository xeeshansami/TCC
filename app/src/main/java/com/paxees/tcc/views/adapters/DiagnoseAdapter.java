package com.paxees.tcc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paxees.tcc.R;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiagnoseListResponseItem;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class DiagnoseAdapter extends RecyclerView.Adapter<DiagnoseAdapter.ViewHolder> {
    private int row_index;
    private ArrayList<DiagnoseListResponseItem> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private String branchImage;

    // data is passed into the constructor
    public DiagnoseAdapter(Context context, ArrayList<DiagnoseListResponseItem> data, ItemClickListener mClickListener) {
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
        DiagnoseListResponseItem data = mData.get(position);
//        Glide.with(context).load(data.get(position).getProduct_Image_Url()).into( holder.imageView);
        holder.titleLbl.setText(data.getYoastHeadJson().getOgTitle());
        holder.imageView.setOnClickListener(v -> {
            mClickListener.onItemClick(v,position,data);
        });
        holder.titleValue.setText("Diagnose ID: "+data.getId());
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
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition(),mData.get(getAdapterPosition()));
        }
    }

    // convenience method for getting data at click position
    ArrayList<DiagnoseListResponseItem> getItem(int id) {
        return mData;
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position,DiagnoseListResponseItem response);
    }
}